package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

public abstract class AbstractFringe implements Fringe {

    @Override
    public State search(State from, State to) {
        State current = from;
        add(current);
        do {
            current = pickCandidate();
            current.getChildStates().forEach(s -> add((State)s));
        } while (!current.statusEquals(to));
        return current;
    }

    abstract void add(State state);
    abstract State pickCandidate();
}
