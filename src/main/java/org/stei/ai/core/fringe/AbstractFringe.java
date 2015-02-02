package org.stei.ai.core.fringe;

import org.stei.ai.core.State;

import java.util.HashSet;

public abstract class AbstractFringe implements Fringe {
    private boolean isHandleDuplicate;
    private boolean rejectDuplicate;
    private boolean rejectCyclic;

    private HashSet<Object> addedNode = new HashSet<>();

    public AbstractFringe(boolean rejectDuplicate, boolean rejectCyclic, boolean handleDuplicate) {
        this.isHandleDuplicate = handleDuplicate;
        this.rejectDuplicate = rejectDuplicate;
        this.rejectCyclic = rejectCyclic;
    }

    public AbstractFringe(boolean rejectDuplicate, boolean rejectCyclic) {
        this(rejectDuplicate, rejectCyclic, false);
    }

    @Override
    public void add(State state) {
        boolean exit = false;
        if (contains(state)) {
            if (rejectDuplicate || state.hasCyclicPath() && rejectCyclic) return;
            if (isHandleDuplicate) exit = handleDuplicate(state);
        }
        if (!exit) {
            addNode(state);
            addState(state);
        }
    }

    protected boolean handleDuplicate(State state) {
        throw new UnsupportedOperationException();
    }

    protected void addNode(State state) {
        addedNode.add(state.nodeHashCode());
    }

    protected boolean contains(State state) {
        return addedNode.contains(state.nodeHashCode());
    }

    protected abstract void addState(State state);
}
