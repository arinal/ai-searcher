package org.stei.ai.sample.eightpuzzle;

import org.stei.ai.core.AbstractState;

import java.util.ArrayList;
import java.util.Arrays;

public class EightPuzzleState extends AbstractState<int[], EightPuzzleState> {

    public EightPuzzleState(EightPuzzleState parent, int... sequence) {
        super(parent, sequence.clone());
        if (!isValid())
            throw new IllegalArgumentException("sequence is not valid!");
    }

    public EightPuzzleState(int... sequence) {
        this(null, sequence);
    }

    @Override
    public Iterable<EightPuzzleState> getChildStates() {
        ArrayList<EightPuzzleState> nextStates = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            EightPuzzleState state = nextState(dir);
            if (state != null)
                nextStates.add(state);
        }
        return nextStates;
    }

    public boolean isValid() {
        int counts[] = new int[9];
        return isSolvable() && status.length == 9 && !Arrays.stream(status).anyMatch(n -> n < 0 || n > 8 || ++counts[n] != 1);
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
        int dir = direction.getValue();
        newSeq[index] = newSeq[index + dir];
        newSeq[index + dir] = 0;
        return new EightPuzzleState(this, newSeq);
    }

    @Override
    public boolean statusEquals(EightPuzzleState to) {
        return statusHashCode() == to.statusHashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < status.length; i++) {
            builder.append((status[i] == 0? " " : status[i]));
            builder.append((((i + 1) % 3 == 0) ? "\n" : " "));
        }
        return builder.toString();
    }

    @Override
    public int statusHashCode() {
        return Arrays.stream(status).reduce(0, (n1, n2) -> n1 * 10 + n2);
    }

    @Override
    public int hashCode() {
        return statusHashCode();
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
        return !(dest < 0 || dest > 8 || index == 3 && dest == 2 ||
                index == 6 && dest == 5 || index == 2 && dest == 3 ||
                index == 5 && dest == 6);
    }
}
