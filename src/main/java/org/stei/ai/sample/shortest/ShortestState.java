package org.stei.ai.sample.shortest;

import org.stei.ai.core.AbstractState;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ShortestState extends AbstractState<Node, ShortestState> {

    public ShortestState(ShortestState parentState, Node status) {
        super(parentState, status);
    }

    public ShortestState(Node node) {
        this(null, node);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Iterable<ShortestState> getChildStates() {
        Iterable<Node> nodes = status.getNeighbours();
        return StreamSupport.stream(nodes.spliterator(), true)
                .map(n -> new ShortestState(this, n))
                .collect(Collectors.toList());
    }

    public int getDistanceToParentNode() {
        return isNotRoot()? status.getDistance(getParentState().status) : 0;
    }
}

