package org.stei.ai.sample.skiing;

import org.stei.ai.core.AbstractState;
import org.stei.ai.core.State;

// TODO: implement longest ski tract
public class SkiingState extends AbstractState {

    public SkiingState(State parentState, Object status) {
        super(parentState, status);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Iterable<State> getChildStates() {
        return null;
    }
}
