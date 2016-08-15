package org.stei.sample.eightpuzzle;

import org.stei.ai.core.AbstractState;

import java.util.ArrayList;
import java.util.Arrays;

public class EightPuzzleState extends AbstractState<int[], EightPuzzleState> {

    public EightPuzzleState(EightPuzzleState parent, int... sequence) {
        super(parent, sequence.clone());
        if (!isValid()) throw new IllegalArgumentException("sequence is not valid!");
    }

    public EightPuzzleState(int... sequence) {
        this(null, sequence);
    }

    @Override
    public Iterable<EightPuzzleState> getChildStates() {
        ArrayList<EightPuzzleState> nextStates = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            EightPuzzleState state = nextState(dir);
            if (state != null) nextStates.add(state);
        }
        return nextStates;
    }

    public boolean isValid() {
        int counts[] = new int[9];
        return isSolvable() && node.length == 9 &&
            !Arrays.stream(node).anyMatch(n -> n < 0 || n > 8 || ++counts[n] != 1);
    }

    @Override
    public boolean nodeEquals(EightPuzzleState to) {
        return nodeHashCode() == to.nodeHashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < node.length; i++) {
            builder.append((node[i] == 0? " " : node[i]));
            builder.append((((i + 1) % 3 == 0) ? "\n" : " "));
        }
        return builder.toString();
    }

    @Override
    public int nodeHashCode() {
        return Arrays.hashCode(node);
    }

    @Override
    public int hashCode() {
        return nodeHashCode();
    }

    private boolean isSolvable() {
        return (getInversionTotal() & 1) == 0;
    }

    private int getInversionTotal() {
        int total = 0;
        for (int i = 0; i < node.length; ++i) total += getInversions(i);
        return total;
    }

    private EightPuzzleState nextState(Direction direction) {
        int index = findHollowIndex();
        if (!isPossible(direction, index)) return null;
        int[] newSeq = node.clone();
        int dir = direction.getValue();
        newSeq[index] = newSeq[index + dir];
        newSeq[index + dir] = 0;
        return new EightPuzzleState(this, newSeq);
    }

    private int getInversions(int index) {
        int result = 0;
        int n = node[index];
        for (int i = index + 1; n != 0 && i < node.length; ++i)
            result += (node[i] > n) ? 1 : 0;
        return result;
    }

    private int findHollowIndex() {
        for (int i = 0; i < node.length; i++) if (node[i] == 0) return i;
        return -1;
    }

    private boolean isPossible(Direction direction, int index) {
        int dest = index + direction.getValue();
        return !(dest < 0 || dest > 8 || index == 3 && dest == 2 ||
                 index == 6 && dest == 5 || index == 2 && dest == 3 ||
                 index == 5 && dest == 6);
    }
}
