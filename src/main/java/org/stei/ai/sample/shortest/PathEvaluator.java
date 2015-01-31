package org.stei.ai.sample.shortest;

import org.stei.ai.core.Evaluator;

public class PathEvaluator implements Evaluator<ShortestState> {
    @Override
    public double evaluate(ShortestState state) {
        return state.getPath().stream()
                .map(ShortestState::getDistanceToParentNode)
                .reduce(0, (a, b) -> a + b);
    }
}
