package org.stei.ai.eightpuzzle;

import org.stei.ai.Evaluator;
import org.stei.ai.State;

public class ManhattanEvaluator implements Evaluator {
	@Override
	public double evaluate(State state) { 
		EightPuzzleState theState = (EightPuzzleState) state;
		double result = 0;
		int[] status = theState.getStatus();
		for (int i = 0; i < status.length; i++)
			result += getManhattanDistance(status[i], i);
		return result;
	}

	public double getManhattanDistance(int value, int index) {
		value = value == 0? 9 : value;
		return Math.abs((value - 1) % 3 - index % 3) + Math.abs((value - 1)/3  - index / 3);
	} 	
}
