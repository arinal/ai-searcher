package org.stei.ai.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public abstract class AbstractState<TNode, TState extends State> implements State<TNode, TState> {
    final private TState parentState;
    protected final TNode node;
    private final int hash;

    protected AbstractState(TState parentState, TNode node) {
        this.node = node;
        this.parentState = parentState;
        hash = hashCode();
    }

    @Override
    public TNode getNode() {
        return node;
    }

    @Override
    public TState getParentState() {
        return parentState;
    }

    public void setParentState(TState parentState) {
        //this.parentState = parentState;
    }

    @Override
    public boolean isNotRoot() {
        return getParentState() != null;
    }

    @Override
    public int pathLength() {
        return getPath().size();
    }

    @Override
    public List<TState> getPath() {
        TState currentState = (TState) this;
        LinkedList<TState> path = new LinkedList<>();
        while (currentState.isNotRoot()) {
            path.addFirst(currentState);
            currentState = (TState)currentState.getParentState();
        }
        path.addFirst(currentState);
        return path;
    }

    @Override
    public boolean hasCyclicPath() {
        List<TState> path = getPath();
        return path.stream().map(s -> s.nodeHashCode()).distinct().count() != path.size();
    }

    @Override
    public String getPathString(String delimiter) {
        return getPath().stream().map(Object::toString).collect(joining(delimiter));
    }

    @Override
    public boolean pathEquals(TState other) {
        return pathHashCode() == other.pathHashCode();
    }

    @Override
    public int pathHashCode() {
        return hash == 0? Objects.hash(isNotRoot() ? parentState.nodeHashCode() : 0, nodeHashCode()) : hash;
    }

    @Override
    public boolean nodeEquals(TState to) {
        return node.equals(to.getNode());
    }

    @Override
    public String toString() {
        return node.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == getClass() && pathEquals((TState)other);
    }

    @Override
    public int hashCode() {
        return pathHashCode();
    }

    @Override
    public int nodeHashCode() {
        return node.hashCode();
    }
}
