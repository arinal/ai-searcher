package org.stei.ai.fringe;

import java.util.HashSet;
import java.util.Stack;

import org.stei.ai.State;

public class DepthSearchFringe extends AbstractFringe {
	private Stack<State> candidates = new Stack<State>();
	private HashSet<State> addedState = new HashSet<State>();
	
	public void add(State state) {
		if (addedState.contains(state))
			return;
		candidates.add(state);
	}	

	public State pickCandidate() {
		return candidates.pop();
	}

	@Override
	public void clear() {
		addedState.clear();
		candidates.clear();
	}

}
