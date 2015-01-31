package org.stei.ai.core;

public interface Evaluator<TState> {
	public double evaluate(TState state);
}
