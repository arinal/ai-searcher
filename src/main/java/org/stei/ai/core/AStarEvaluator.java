package org.stei.ai.core;

public class AStarEvaluator<TState extends State> implements Evaluator<TState> {
    private Evaluator greedyEvaluator;

    public AStarEvaluator(Evaluator greedyEvaluator) {
        this.greedyEvaluator = greedyEvaluator;
    }

    @Override
    public double evaluate(TState state) {
        return greedyEvaluator.evaluate(state) + getActual(state);
    }

    protected int getActual(TState state) {
        return state.pathLength();
    }
}
