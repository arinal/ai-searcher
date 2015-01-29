package org.stei.ui.konsole;

import java.util.List;

import org.stei.ai.core.AStarEvaluator;
import org.stei.ai.core.Searcher;
import org.stei.ai.core.State;
import org.stei.ai.sample.eightpuzzle.EightPuzzleState;
import org.stei.ai.sample.eightpuzzle.ManhattanEvaluator;
import org.stei.ai.core.fringe.BestSearchFringe;
import org.stei.ai.sample.shortest.Node;
import org.stei.ai.sample.shortest.PathEvaluator;
import org.stei.ai.sample.shortest.PathState;

public class Program {
    public static void main(String[] args) {
//        shortest();
        eightPuzzle();
    }

    private static void eightPuzzle() {
        EightPuzzleState dest = new EightPuzzleState(1, 2, 3, 4, 5, 6, 7, 8, 0);
        EightPuzzleState from = new EightPuzzleState(4, 8, 7, 2, 5, 6, 3, 0, 1);

        List<State> steps = Searcher.search(from, dest,
//				new DepthSearchFringe());
//				new BreadthSearchFringe());
//				new BestSearchFringe(new MisplacedBlocksEvaluator()));
//				new BestSearchFringe(new AStarEvaluator(new MisplacedBlocksEvaluator())));
//				new BestSearchFringe(new ManhattanEvaluator()));
                new BestSearchFringe(new AStarEvaluator(new ManhattanEvaluator())));

        steps.stream().forEach(System.out::println);
        System.out.println(steps.size() + " steps");
    }

    public static void shortest() {
        Node na = new Node("a");
        Node nb = new Node("b");
        Node nc = new Node("c");
        Node nd = new Node("d");

        na.addEdge2Side(nb, 2);
        nb.addEdge2Side(nc, 4);
        nc.addEdge2Side(nd, 1);
        na.addEdge2Side(nd, 10);

        PathState from = new PathState(na);
        PathState dest = new PathState(nd);
        List<State> steps = Searcher.search(from, dest,
//                new DepthSearchFringe());
                new BestSearchFringe(new PathEvaluator()));
        steps.stream().forEach(System.out::println);
    }
}
