package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

public interface Fringe {
    State search(State from, State to);
}
