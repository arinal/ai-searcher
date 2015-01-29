package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

public interface Fringe {
    void clear();
    void add(State state);
    void addAll(Iterable<State> states);
    State pickCandidate();
}
