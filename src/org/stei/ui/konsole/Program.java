package org.stei.ui.konsole;
import java.util.List;

import org.stei.ai.AStarEvaluator;
import org.stei.ai.Searcher;
import org.stei.ai.State;
import org.stei.ai.eightpuzzle.EightPuzzleState;
import org.stei.ai.eightpuzzle.ManhattanEvaluator;
import org.stei.ai.fringe.BestSearchFringe;

public class Program {
	public static void main(String[] args) {
		EightPuzzleState dest = new EightPuzzleState(1, 2, 3, 4, 5, 6, 7, 8, 0);
		EightPuzzleState from =
//			new EightPuzzleState(5, 8, 7, 0, 1, 6, 3, 2, 4)
			new EightPuzzleState(4, 8, 7, 2, 5, 6, 3, 0, 1)
//		dest.nextState(Direction.Up)
//			.nextState(Direction.Left)
//			.nextState(Direction.Left)
//			.nextState(Direction.Up)
//			.nextState(Direction.Right)
//			.nextState(Direction.Right)
//			.nextState(Direction.Down)
//			.nextState(Direction.Left).nextState(Direction.Left)
//			.nextState(Direction.Down)
//			.nextState(Direction.Right).nextState(Direction.Right)
//			.nextState(Direction.Up)
//			.nextState(Direction.Left).nextState(Direction.Left)
//			.nextState(Direction.Up)
//			.nextState(Direction.Right).nextState(Direction.Right)
			;
		
		Searcher searcher = new Searcher();
		List<State> steps = searcher.search(from, dest, 
//				new DepthSearchFringe());
//				new BreadthSearchFringe());
//				new BestSearchFringe(new MisplacedBlocksEvaluator()));
//				new BestSearchFringe(new AStarEvaluator(new MisplacedBlocksEvaluator())));
//				new BestSearchFringe(new ManhattanEvaluator()));
				new BestSearchFringe(new AStarEvaluator(new ManhattanEvaluator())));
		
		steps.stream().forEach(System.out::println);
		System.out.println(steps.size());
	}
}
