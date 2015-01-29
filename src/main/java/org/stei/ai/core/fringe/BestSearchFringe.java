package org.stei.ai.core.fringe;

import java.util.HashSet;
import java.util.PriorityQueue;

import org.stei.ai.core.Evaluator;
import org.stei.ai.core.State;

public class BestSearchFringe extends AbstractFringe {
    private Evaluator evaluator;
    private HashSet<State> addedState = new HashSet<>();

    private PriorityQueue<State> candidates = new PriorityQueue<>(1, (s1, s2) -> {
        Double value1 = evaluator.evaluate(s1);
        Double value2 = evaluator.evaluate(s2);
        return value1.compareTo(value2);
    });

    public BestSearchFringe(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public State pickCandidate() {
        return candidates.remove();
    }

    @Override
    public void add(State state) {
        if (addedState.contains(state))
            return;
        addedState.add(state);
        candidates.add(state);
    }

    @Override
    public void clear() {
        candidates.clear();
        addedState.clear();
    }

}