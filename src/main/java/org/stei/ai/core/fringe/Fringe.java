package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

public interface Fringe {
    void add(State state);
    boolean hasCandidates();
    State pickCandidate();
}
