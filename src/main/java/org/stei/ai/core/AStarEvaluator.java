package org.stei.ai.core;

public class AStarEvaluator implements Evaluator {

    private Evaluator greedyEvaluator;

    public AStarEvaluator(Evaluator greedyEvaluator) {
        this.greedyEvaluator = greedyEvaluator;
    }

    @Override
    public double evaluate(State state) {
        return greedyEvaluator.evaluate(state) + getActual(state);
    }

    protected int getActual(State state) {
        return state.depth();
    }
}
