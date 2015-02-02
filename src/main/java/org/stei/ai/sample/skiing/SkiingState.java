package org.stei.ai.sample.skiing;

import org.stei.ai.core.AbstractState;

import java.util.List;
import java.util.stream.Collectors;

public class SkiingState extends AbstractState<ElevationNode, SkiingState> {

    public SkiingState(SkiingState parentState, ElevationNode node) {
        super(parentState, node);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Iterable<SkiingState> getChildStates() {
        return node.getDownWards().stream().map(n -> new SkiingState(this, n)).collect(Collectors.toList());
    }

    public int getOriginToBottomDelta() {
        return node.compareTo(getRoot().node);
    }
}