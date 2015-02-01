package org.stei.ai.sample.skiing;

import org.stei.ai.core.Evaluator;

public class SkiLengthEvaluator implements Evaluator<SkiingState> {
    @Override
    public double evaluate(SkiingState skiingState) {
        return skiingState.pathLength();
    }
}
