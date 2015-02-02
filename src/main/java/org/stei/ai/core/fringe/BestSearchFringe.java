package org.stei.ai.core.fringe;

import org.stei.ai.core.Evaluator;
import org.stei.ai.core.State;

import java.util.PriorityQueue;

import static java.util.stream.Collectors.joining;

public class BestSearchFringe extends AbstractFringe {
    private Evaluator evaluator;

    private PriorityQueue<State> candidates = new PriorityQueue<>(1, (s1, s2) -> {
        Double value1 = evaluator.evaluate(s1);
        Double value2 = evaluator.evaluate(s2);
        return value1.compareTo(value2);
    });

    public BestSearchFringe(Evaluator evaluator, boolean rejectDuplicate, boolean rejectCyclic) {
        super(rejectDuplicate, rejectCyclic);
        this.evaluator = evaluator;
    }

    public BestSearchFringe(Evaluator evaluator) {
        this(evaluator, false, true);
    }

    @Override
    public State pickCandidate() {
        return candidates.remove();
    }

    @Override
    protected void addState(State state) {
//        System.out.print("+" + state.getPathString("-") + " ");
        candidates.add(state);
    }

    @Override
    public boolean hasCandidates() {
        return !candidates.isEmpty();
    }

    @Override
    public String toString() {
        return candidates.stream().map(s -> s.getPathString("-")).collect(joining(" "));
    }
}