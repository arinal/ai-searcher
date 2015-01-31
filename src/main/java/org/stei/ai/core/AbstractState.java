package org.stei.ai.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public abstract class AbstractState<TStatus, TState extends State> implements State<TStatus, TState> {
    private final TState parentState;
    protected final TStatus status;
    private final int hash;

    protected AbstractState(TState parentState, TStatus status) {
        this.status = status;
        this.parentState = parentState;
        hash = hashCode();
    }

    @Override
    public TStatus getStatus() {
        return status;
    }

    @Override
    public TState getParentState() {
        return parentState;
    }

    @Override
    public boolean isNotRoot() {
        return getParentState() != null;
    }

    @Override
    public int depth() {
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
    public String getPathString(String delimiter) {
        return getPath().stream().map(Object::toString).collect(joining(delimiter));
    }

    @Override
    public boolean pathEquals(TState other) {
        return pathHashCode() == other.pathHashCode();
    }

    @Override
    public int pathHashCode() {
        return hash == 0? Objects.hash(isNotRoot() ? parentState.hashCode() : 0, statusHashCode()) : hash;
    }

    @Override
    public boolean statusEquals(TState to) {
        return status.equals(to.getStatus());
    }

    @Override
    public String toString() {
        return status.toString();
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
    public int statusHashCode() {
        return status.hashCode();
    }
}
