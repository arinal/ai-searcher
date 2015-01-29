package org.stei.eightpuzzle;

import java.util.ArrayList;

import org.stei.ai.AbstractState;
import org.stei.ai.State;

public class EightPuzzleState extends AbstractState<int[]> {

    public EightPuzzleState(State parent, int... sequence) {
        super(parent, sequence.clone());
        if (!isValid())
            throw new IllegalArgumentException("sequence is not valid!");
    }

    public EightPuzzleState(int... sequence) {
        this(null, sequence);
    }

    @Override
    public Iterable<State> getChildStates() {
        ArrayList<State> nextStates = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            State state = nextState(dir);
            if (state != null)
                nextStates.add(state);
        }
        return nextStates;
    }

    public boolean isValid() {
        int counts[] = new int[9];
        for (int n : status)
            if (n < 0 || n > 8 || ++counts[n] != 1)
                return false;
        return true;
    }

    @Override
    public int[] getStatus() {
        return status.clone();
    }

    public boolean isSolvable() {
        return (getInversionTotal() & 1) == 0;
    }

    public int getInversionTotal() {
        int total = 0;
        for (int i = 0; i < status.length; ++i)
            total += getInversions(i);
        return total;
    }

    public EightPuzzleState nextState(Direction direction) {
        int index = findHollowIndex();
        if (!isPossible(direction, index))
            return null;
        int[] newSeq = status.clone();
        newSeq[index] = newSeq[index + direction.getValue()];
        newSeq[index + direction.getValue()] = 0;
        return new EightPuzzleState(this, newSeq);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < status.length; i++)
            builder.append(status[i] + (((i + 1) % 3 == 0) ? "\n" : " "));
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return statusEquals((EightPuzzleState) other);
    }

    @Override
    public int hashCode() {
        return getHash();
    }

    public boolean statusEquals(State other) {
        return hashCode() == other.hashCode();
    }

    public int getInversions(int index) {
        int result = 0;
        int n = status[index];
        for (int i = index + 1; n != 0 && i < status.length; ++i)
            result += (status[i] > n) ? 1 : 0;
        return result;
    }

    private int findHollowIndex() {
        for (int i = 0; i < status.length; i++)
            if (status[i] == 0)
                return i;
        return -1;
    }

    private boolean isPossible(Direction direction, int index) {
        int dest = index + direction.getValue();
        return !(dest < 0 || dest > 8 || index == 3 && dest == 2 || index == 6
                && dest == 5 || index == 2 && dest == 3 || index == 5
                && dest == 6);
    }

    private int getHash() {
        int hash = 0;
        for (int n : status)
            hash = hash * 10 + n;
        return hash;
    }
}
