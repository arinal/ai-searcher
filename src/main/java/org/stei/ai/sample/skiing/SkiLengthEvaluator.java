package org.stei.ai.sample.skiing;

import org.stei.ai.core.Evaluator;

public class SkiLengthEvaluator implements Evaluator<SkiingState> {
    @Override
    public double evaluate(SkiingState skiingState) {
        double result = skiingState.pathLength() * 2000 + -skiingState.getOriginToBottomDelta();
//        System.out.print(skiingState.getPathString("-") + "/" + skiingState.getOriginToBottomDelta() + " ");
        return result;
    }
}
