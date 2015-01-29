package org.stei.ui.konsole;
import java.util.List;

import org.stei.ai.AStarEvaluator;
import org.stei.ai.Searcher;
import org.stei.ai.State;
import org.stei.ai.fringe.BreadthSearchFringe;
import org.stei.ai.fringe.DepthSearchFringe;
import org.stei.eightpuzzle.EightPuzzleState;
import org.stei.eightpuzzle.ManhattanEvaluator;
import org.stei.ai.fringe.BestSearchFringe;
import org.stei.shortest.Node;
import org.stei.shortest.PathEvaluator;
import org.stei.shortest.PathState;

public class Program {
    public static void main(String[] args) {
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

    public static void mainShortest(String[] args) {
        Node na = new Node("a");
        Node nb = new Node("b");
        Node nc = new Node("c");
        Node nd = new Node("d");

        na.addEdge2Side(nb, 2);
        nb.addEdge2Side(nc, 4);
        nc.addEdge2Side(nd, 1);
        na.addEdge2Side(nd, 10);

        PathState from = new PathState(na);
        PathState to = new PathState(nd);
        List<State> steps = Searcher.search(from, to, new BestSearchFringe(new PathEvaluator()));
        steps.stream().forEach(System.out::println);
    }
}
