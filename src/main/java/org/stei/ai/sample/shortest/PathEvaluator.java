package org.stei.ai.sample.shortest;

import org.stei.ai.core.Evaluator;
import org.stei.ai.core.State;

public class PathEvaluator implements Evaluator {
    @Override
    public double evaluate(State state) {
        PathState pathState = (PathState)state;
        double result = 0;
        do {
            PathState next = (PathState) pathState.getParentState();
            result += pathState.getDistanceToParentNode();
            pathState = next;
        } while (!pathState.isRoot());
        return result;
    }
}
