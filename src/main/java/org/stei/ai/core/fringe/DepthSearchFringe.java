package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

import java.util.Stack;

public class DepthSearchFringe extends AbstractFringe {
	private Stack<State> candidates = new Stack<>();

    public DepthSearchFringe() {
        super(false, true);
    }

    @Override
    protected void addState(State state) {
        candidates.add(state);
    }

    @Override
    public boolean hasCandidates() {
        return !candidates.isEmpty();
    }

    public State pickCandidate() {
		return candidates.pop();
	}
}
