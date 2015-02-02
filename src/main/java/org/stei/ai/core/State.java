package org.stei.ai.core;

import java.util.List;

public interface State<TStatus, TState extends State> {
	TStatus getNode();
    boolean isValid();

	boolean isNotRoot();
    TState getParentState();
    TState getRoot();
	Iterable<TState> getChildStates();

    int pathLength();
    List<TState> getPath();
    boolean hasCyclicPath();
    String getPathString(String delimiter);
    boolean pathEquals(TState to);

    int pathHashCode();
    boolean nodeEquals(TState to);
    int nodeHashCode();
}
