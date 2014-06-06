package org.stei.ai.fringe;

import org.stei.ai.State;

public abstract class AbstractFringe implements Fringe {	
	@Override
	public void addAll(Iterable<State> states) {
		for (State state : states)
			add(state);
	}
}
