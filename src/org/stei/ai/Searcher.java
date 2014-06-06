package org.stei.ai;

import java.util.List;

import org.stei.ai.fringe.Fringe;

public class Searcher {	
	public List<State> search(State from, State to, Fringe fringe) {
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

//System.out.println(fringe.getExploredState() + " " + fringe.count());