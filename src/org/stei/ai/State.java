package org.stei.ai;

import java.util.List;

public interface State {
	Object getStatus();
	boolean statusEquals(State other);
	
	boolean isValid();
	
	boolean isRoot();
	State getParentState();
	void setParentState(State currentState);
	void makeRoot();
	
	int depth();
			
	Iterable<State> getChildStates();
	List<State> getPath();
}
