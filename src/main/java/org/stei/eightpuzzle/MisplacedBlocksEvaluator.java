package org.stei.eightpuzzle;

import org.stei.ai.Evaluator;
import org.stei.ai.State;

public class MisplacedBlocksEvaluator implements Evaluator {
	@Override
	public double evaluate(State state) {
		return getHeuristic((EightPuzzleState) state);
	}

	private double getHeuristic(EightPuzzleState state) {
		double result = 8;
		int[] sequence = state.getStatus();
		for (int i = 0; i < sequence.length; i++) 
			result -= (sequence[i] == i + 1)? 1 : 0;
		return result;
	}
}
