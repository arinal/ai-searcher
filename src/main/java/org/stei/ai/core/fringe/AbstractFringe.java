package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

import java.util.HashSet;

public abstract class AbstractFringe implements Fringe {
    private boolean rejectDuplicate;
    private boolean rejectCyclic;

    private HashSet<Object> addedNode = new HashSet<>();

    public AbstractFringe(boolean rejectDuplicate, boolean rejectCyclic) {
        this.rejectDuplicate = rejectDuplicate;
        this.rejectCyclic = rejectCyclic;
    }

    @Override
    public void add(State state) {
        if (contains(state) &&
                (rejectDuplicate || state.hasCyclicPath() && rejectCyclic)) return;
        addNode(state);
        addState(state);
    }

    protected void addNode(State state) {
        addedNode.add(state.nodeHashCode());
    }

    protected boolean contains(State state) {
        return addedNode.contains(state.nodeHashCode());
    }

    protected abstract void addState(State state);

}
