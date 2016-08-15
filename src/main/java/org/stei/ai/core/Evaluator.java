package org.stei.ai.core;

public interface Evaluator<TState> {
	double evaluate(TState state);
}
