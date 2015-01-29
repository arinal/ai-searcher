package org.stei.ai.fringe;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.stei.ai.State;

public class BreadthSearchFringe extends AbstractFringe {
	private Queue<State> candidates = new LinkedList<>();
	private HashSet<State> addedState = new HashSet<>();
	
	public void add(State state) {
		if (addedState.contains(state))
			return;
		candidates.add(state);
	}	

	public State pickCandidate() {
        return candidates.remove();
	}

	@Override
	public void clear() {
		addedState.clear();
		candidates.clear();
	}

}
