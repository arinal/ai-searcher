package org.stei.ai.sample.shortest;

import org.stei.ai.core.AbstractState;
import org.stei.ai.core.State;

import java.util.ArrayList;
import java.util.List;

public class PathState extends AbstractState<Node> {

    public PathState(State parentState, Node status) {
        super(parentState, status);
    }

    public PathState(Node node) {
        this(null, node);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Iterable<State> getChildStates() {
        Iterable<Node> nodes = status.getNeighbours();
        List<State> result = new ArrayList<>();
        for (Node n : nodes) result.add(new PathState(this, n));
        return result;
    }

    @Override
    public String toString() {
        return status.toString();
    }

    public int getDistanceToParentNode() {
        return isRoot()? 0 : status.getDistance(((PathState)getParentState()).status);
    }
}

