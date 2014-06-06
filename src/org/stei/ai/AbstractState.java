
package org.stei.ai;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractState<T> implements State {
	protected T status;
	private State parentState;
	
	public AbstractState(T status) {
		this(null, status);
	}
	
	public AbstractState(State parentState, T status) {
		this.status = status;
		this.parentState = parentState;
	}
	
	@Override
	public T getStatus() {
		return status;
	}
	
	@Override
	public State getParentState() {
		return parentState;
	}
	
	@Override
	public void makeRoot() {
		setParentState(null);
	}
	
	@Override
	public boolean isRoot() {
		return getParentState() == null;
	}
	
	public boolean statusEquals(State other) {
		return status.equals(other.getStatus());
	}

	public void setParentState(State parent) {
		this.parentState = parent;
	}	
	
	@Override
	public int depth() {
		int result = 0;
		State state = this;
		while (!state.isRoot()) {
			state = state.getParentState();
			result++;
		}
		return result;
	}
	
	@Override
	public List<State> getPath() {
		State currentState = this;
		LinkedList<State> path = new LinkedList<State>();
		while (!currentState.isRoot()) {
			path.addFirst(currentState);
			currentState = currentState.getParentState();
		}
		path.addFirst(currentState);
		return path;
	}
	
	@Override
	public String toString() {
		return status.toString();
	}
}
