package org.stei.ui.konsole;

import org.stei.ai.Searcher;
import org.stei.ai.core.AStarEvaluator;
import org.stei.ai.core.State;
import org.stei.ai.core.fringe.BestSearchFringe;
import org.stei.ai.core.fringe.BreadthSearchFringe;
import org.stei.ai.core.fringe.DepthSearchFringe;
import org.stei.ai.sample.eightpuzzle.EightPuzzleState;
import org.stei.ai.sample.eightpuzzle.ManhattanEvaluator;
import org.stei.ai.sample.shortest.Node;
import org.stei.ai.sample.shortest.PathEvaluator;
import org.stei.ai.sample.shortest.ShortestState;
import org.stei.ai.sample.skiing.ElevationContour;
import org.stei.ai.sample.skiing.ElevationNode;
import org.stei.ai.sample.skiing.SkiLengthEvaluator;
import org.stei.ai.sample.skiing.SkiingState;

import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Program {
    public static void main(String[] args) throws InterruptedException, IOException {
//        shortest();

//        System.out.println();
//        eightPuzzle();

        System.out.println();
        skiing();

    }

    public static void shortest() throws InterruptedException {
        // Let's create graph as in http://en.wikipedia.org/wiki/Shortest_path_problem on upper right
        // The answer should be: A-C-E-D-F as described in the site
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        a.addEdge2Side(b, 4);
        a.addEdge2Side(c, 2);
        b.addEdge2Side(c, 5);
        b.addEdge2Side(d, 10);
        c.addEdge2Side(e, 3);
        e.addEdge2Side(d, 4);
        d.addEdge2Side(f, 11);

        ShortestState from = new ShortestState(a);
        ShortestState to = new ShortestState(f);
        State steps = new Searcher(
//                new DepthSearchFringe())
//                new BreadthSearchFringe())
                new BestSearchFringe(new PathEvaluator()))
//                new BestSearchFringe(new AStarEvaluator(new PathEvaluator()), false, true))
                        .search(from, to);
        System.out.println(steps.getPathString("->"));
    }

    private static void eightPuzzle() {
        EightPuzzleState dest = new EightPuzzleState(1, 2, 3, 4, 5, 6, 7, 8, 0);
        EightPuzzleState from = new EightPuzzleState(4, 8, 7, 2, 5, 6, 3, 0, 1);

        State steps = new Searcher(
//				new DepthSearchFringe())
				new BreadthSearchFringe(true, true))
//				new BestSearchFringe(new MisplacedBlocksEvaluator()))
//				new BestSearchFringe(new AStarEvaluator(new MisplacedBlocksEvaluator())))
//				new BestSearchFringe(new ManhattanEvaluator()))
//                new BestSearchFringe(new AStarEvaluator(new ManhattanEvaluator()), true, true))
                        .search(from, dest);

        System.out.println(steps.getPathString("\n"));
        System.out.println(steps.getPath().size() + " steps");
    }

    private static void skiing() throws InterruptedException, FileNotFoundException {
        File file = new File("src/main/resources/map.txt");
        Scanner in = new Scanner(file);

        int width = in.nextInt();
        int length = in.nextInt();
        int[] values = IntStream.range(0, width * length).map(n -> in.nextInt()).toArray();
        in.close();

        ElevationContour contour = new ElevationContour(
                width, length, values
//                4, 4,
//                5, 5, 6, 7,
//                2, 5, 9, 8,
//                6, 3, 2, 5,
//                4, 4, 1, 6

//                4, 4, new int[] {
//                4, 8, 7, 3,
//                2, 5, 9, 3,
//                6, 3, 2, 5,
//                4, 4, 1, 6}
        );

        System.out.println("calculating...");
        SkiLengthEvaluator evaluator = new SkiLengthEvaluator();
        Searcher searcher = new Searcher(
                new BreadthSearchFringe());
//            new BestSearchFringe(evaluator, false, true));
        SkiingState longest = new SkiingState(null, new ElevationNode(0, 0, 0));
        // I know.. my solution is not Top-Coder-ish (no memoization, Dynamic Programming), but hey.. its works!
        for (int y = 0; y < contour.getLength(); y++) {
            for (int x = 0; x < contour.getWidth(); x++) {
                SkiingState from = new SkiingState(null, contour.get(x, y));
                SkiingState step = (SkiingState)searcher.search(from);
                if (evaluator.evaluate(step) > evaluator.evaluate(longest)) {
                    longest = step;
                    System.out.println(longest.pathLength() + " " + longest.getOriginToBottomDelta() + " " + step.getPathString("-"));
                }
            }
        }
    }
}