package org.stei.ai.core.fringe;

import java.util.HashSet;
import java.util.Stack;

import org.stei.ai.core.State;

public class DepthSearchFringe extends AbstractFringe {
	private Stack<State> candidates = new Stack<>();
	private HashSet<State> addedState = new HashSet<>();
	
	public void add(State state) {
		if (addedState.contains(state))
            return;
        candidates.add(state);
        addedState.add(state);
	}

	public State pickCandidate() {
		return candidates.pop();
	}
}
