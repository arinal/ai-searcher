package org.stei.ai.sample.eightpuzzle;

import org.stei.ai.core.Evaluator;

public class MisplacedBlocksEvaluator implements Evaluator<EightPuzzleState> {
	@Override
	public double evaluate(EightPuzzleState state) {
		return getHeuristic(state);
	}

	private double getHeuristic(EightPuzzleState state) {
		double result = 8;
		int[] sequence = state.getNode();
		for (int i = 0; i < sequence.length; i++) 
			result -= (sequence[i] == i + 1)? 1 : 0;
		return result;
	}
}
