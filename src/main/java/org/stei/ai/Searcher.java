package org.stei.ai;

import org.stei.ai.core.State;
import org.stei.ai.core.fringe.Fringe;

public class Searcher {
    private final Fringe fringe;

    public Searcher(Fringe fringe) {
        this.fringe = fringe;
    }

    public State search(State from, State to) {
        State current = from;
        fringe.add(current);
        do {
            current = fringe.pickCandidate();
            current.getChildStates().forEach(s -> fringe.add((State) s));
        } while (!current.nodeEquals(to));
        return current;
    }

    public State search(State from) {
        State current = from;
        fringe.add(current);
        do {
            current = fringe.pickCandidate();
            current.getChildStates().forEach(s -> fringe.add((State) s));
        } while (fringe.hasCandidates());
        return current;
    }
}

//    public State searchWithLog(State from, State to) {
//        State current = from;
//        fringe.add(current);
//        do {
//            System.out.print(fringe + ": ");
//            current = fringe.pickCandidate();
//            current.getChildStates().forEach(s -> fringe.add((State) s));
//            System.out.println("-" + current.getPathString("-"));
//        } while (!current.nodeEquals(to));
//        return current;
//    }