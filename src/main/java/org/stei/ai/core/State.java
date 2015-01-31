package org.stei.ai.core;

import java.util.List;

public interface State<TStatus, TState extends State> {
	TStatus getStatus();
    boolean isValid();

	boolean isNotRoot();
    TState getParentState();
	int depth();
	Iterable<TState> getChildStates();

    List<TState> getPath();
    String getPathString(String delimiter);
    boolean pathEquals(TState to);
    int pathHashCode();

    boolean statusEquals(TState to);
    int statusHashCode();
}
