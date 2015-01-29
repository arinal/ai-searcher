package org.stei.ai.core;

import java.util.List;

import org.stei.ai.core.fringe.Fringe;

public class Searcher {	
	public static List<State> search(State from, State to, Fringe fringe) {
		fringe.clear();
		from.makeRoot();
		State current = from;
		fringe.add(current);
		do {
			current = fringe.pickCandidate();
			fringe.addAll(current.getChildStates());
		} while (!current.statusEquals(to));
		return current.getPath();
	}
}
