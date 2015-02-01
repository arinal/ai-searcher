package org.stei.ai.core.fringe;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.stei.ai.core.State;

public class BreadthSearchFringe extends AbstractFringe {
	private Queue<State> candidates = new LinkedList<>();
	private HashSet<Object> addedNode = new HashSet<>();

    public BreadthSearchFringe() {
        super(false, true);
    }

    public BreadthSearchFringe(boolean rejectDuplicate, boolean rejectCyclic) {
        super(rejectDuplicate, rejectCyclic);
    }

    @Override
    protected void addState(State state) {
        candidates.add(state);
        addedNode.add(state.nodeHashCode());
    }

    @Override
    protected boolean contains(State state) {
        return addedNode.contains(state.nodeHashCode());
    }

    @Override
    public boolean hasCandidates() {
        return !candidates.isEmpty();
    }

    public State pickCandidate() {
        return candidates.remove();
	}
}
