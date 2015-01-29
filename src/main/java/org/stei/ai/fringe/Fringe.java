package org.stei.ai.fringe;

import org.stei.ai.State;

public interface Fringe {
    void clear();
    void add(State state);
    void addAll(Iterable<State> states);
    State pickCandidate();
}
