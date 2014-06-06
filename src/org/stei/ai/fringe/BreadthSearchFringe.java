package org.stei.ai.fringe;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.stei.ai.State;

public class BreadthSearchFringe extends AbstractFringe {
	private Queue<State> candidates = new LinkedList<State>();
	private HashSet<State> addedState = new HashSet<State>();
	
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
	
	@Override
	public int getExploredState() {
		return addedState.size(); 
	}

	@Override
	public int count() {
		return candidates.size();
	}
}
