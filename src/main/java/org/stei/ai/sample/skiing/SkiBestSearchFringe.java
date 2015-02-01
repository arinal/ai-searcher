package org.stei.ai.sample.skiing;

import org.stei.ai.core.Evaluator;
import org.stei.ai.core.State;
import org.stei.ai.core.fringe.BestSearchFringe;

public class SkiBestSearchFringe extends BestSearchFringe {

    public SkiBestSearchFringe(Evaluator evaluator) {
        super(evaluator, false, true);
    }

    //@Override
    protected boolean onClash(State state, State origin) {
//        State original = added
        return true;
    }
}
