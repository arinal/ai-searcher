package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

public abstract class AbstractFringe implements Fringe {	
	@Override
	public void addAll(Iterable<State> states) {
		for (State state : states)
			add(state);
	}
}
