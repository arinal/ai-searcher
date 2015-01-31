package ai.sample.shortest;


import org.junit.Before;
import org.junit.Test;
import org.stei.ai.sample.shortest.Node;
import org.stei.ai.sample.shortest.ShortestState;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ShortestStateTest {
    @Before
    public void given() {
        a = new Node("A");
        stateA = new ShortestState(a);
        a1 = new Node("A");
        stateA1 = new ShortestState(a1);

        b = new Node("B");
        stateB = new ShortestState(stateA, b);
        stateB1 = new ShortestState(stateA, b);
    }

    @Test
    public void state_A_is_equals_state_A1() {
        assertThat(stateA.hashCode(), equalTo(stateA1.hashCode()));
        assertTrue(stateA.equals(stateA1));
    }

    @Test
    public void state_A_not_equals_state_B() {
        assertThat(stateA.hashCode(), not(equalTo(stateB.hashCode())));
        assertFalse(stateA.equals(stateB));
    }

    @Test
    public void state_B_equals_state_B1() {
        assertThat(stateB.hashCode(), equalTo(stateB1.hashCode()));
        assertTrue(stateB.equals(stateB1));
    }

    private Node a;
    private Node a1;
    private ShortestState stateA;
    private ShortestState stateA1;

    private Node b;
    private ShortestState stateB;
    private ShortestState stateB1;

}
