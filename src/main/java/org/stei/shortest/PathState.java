package org.stei.shortest;

import org.stei.ai.AbstractState;
import org.stei.ai.State;

import java.util.ArrayList;
import java.util.List;

public class PathState extends AbstractState {
    private final Node currentNode;

    public Node getCurrentNode() {
        return currentNode;
    }

    public PathState(State parentState, Object status) {
        super(parentState, status);
        this.currentNode = (Node)status;
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
        Iterable<Node> nodes = currentNode.getNeighbours();
        List<State> result = new ArrayList<>();
        for (Node n : nodes) result.add(new PathState(this, n));
        return result;
    }

    @Override
    public String toString() {
        return currentNode.toString();
    }

    public int getDistanceToParentNode() {
        return isRoot()? 0 : getCurrentNode().getDistance(((PathState)getParentState()).getCurrentNode());
    }
}

