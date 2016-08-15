package org.stei.sample.eightpuzzle;

import org.stei.ai.core.Evaluator;

public class ManhattanEvaluator implements Evaluator<EightPuzzleState> {
    @Override
    public double evaluate(EightPuzzleState state) {
        double result = 0;
        int[] status = state.getNode();
        for (int i = 0; i < status.length; i++)
            result += getManhattanDistance(status[i], i);
        return result;
    }

    public double getManhattanDistance(int value, int index) {
        value = value == 0? 9 : value;
        return Math.abs((value - 1) % 3 - index % 3) + Math.abs((value - 1)/3  - index / 3);
    }
}
