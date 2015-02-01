package ai.sample.skiing;

import org.junit.Before;
import org.junit.Test;
import org.stei.ai.sample.skiing.Direction;
import org.stei.ai.sample.skiing.ElevationNode;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ElevationNodeTest {
    @Before
    public void given() {
        centerNode = new ElevationNode(1, 1, 5);
        northNode = new ElevationNode(1, 0, 7);
        southNode = new ElevationNode(1, 2, 4);
        westNode = new ElevationNode(0, 1, 4);
        eastNode = new ElevationNode(2, 1, 7);
    }

    @Test
    public void can_create_node_by_direction() {
        ElevationNode node = centerNode.createNode(Direction.North);
        assertTrue(node.coordinateEquals(northNode));
    }

    @Test
    public void center_can_set_northNode_with_north_direction() {
        centerNode.setNode(northNode, Direction.North);
        ElevationNode node = centerNode.getNode(Direction.North);
        assertThat(node, equalTo(northNode));
    }

    @Test(expected = IllegalArgumentException.class)
    public void center_cannot_set_southNode_with_north_direction() {
        centerNode.setNode(southNode, Direction.North);
    }

    @Test
    public void center_can_set_westNode_with_west_direction() {
        centerNode.setNode(westNode, Direction.West);
        ElevationNode node = centerNode.getNode(Direction.West);
        assertThat(node, equalTo(westNode));
    }

    @Test(expected = IllegalArgumentException.class)
    public void center_cannot_set_east_with_west_direction() {
        centerNode.setNode(eastNode, Direction.West);
    }

    @Test
    public void node_can_get_list_of_downwards_neighbours() {
        centerNode.setNode(northNode, Direction.North);
        centerNode.setNode(southNode, Direction.South);
        centerNode.setNode(westNode, Direction.West);
        centerNode.setNode(eastNode, Direction.East);

        List<ElevationNode> nodes = centerNode.getDownWards();

        assertTrue(nodes.stream().anyMatch(n -> n.equals(southNode)));
        assertTrue(nodes.stream().anyMatch(n -> n.equals(westNode)));
        assertTrue(nodes.stream().noneMatch(n -> n.equals(northNode)));
        assertTrue(nodes.stream().noneMatch(n -> n.equals(eastNode)));
    }

    private ElevationNode northNode;
    private ElevationNode centerNode;
    private ElevationNode southNode;
    private ElevationNode westNode;
    private ElevationNode eastNode;
}
