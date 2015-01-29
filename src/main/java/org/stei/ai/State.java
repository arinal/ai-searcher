package org.stei.ai;

import java.util.List;

public interface State {
	Object getStatus();
	boolean statusEquals(State other);
    boolean isValid();

	boolean isRoot();
    void makeRoot();
    State getParentState();
    void setParentState(State currentState);
	int depth();
			
	Iterable<State> getChildStates();
	List<State> getPath();
}
