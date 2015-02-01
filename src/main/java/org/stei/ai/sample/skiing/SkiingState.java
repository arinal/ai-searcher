package org.stei.ai.sample.skiing;

import org.stei.ai.core.AbstractState;

import java.util.stream.Collectors;

public class SkiingState extends AbstractState<ElevationNode, SkiingState> {

    public SkiingState(SkiingState parentState, ElevationNode status) {
        super(parentState, status);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Iterable<SkiingState> getChildStates() {
        return  node.getDownWards().stream().map(n -> new SkiingState(this, n)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
