package org.stei.ai.fringe;

import org.stei.ai.State;

public interface Fringe {
	int getExploredState();
	int count();
	
	void clear();
	State pickCandidate();
	void add(State state);
	void addAll(Iterable<State> states);
}
