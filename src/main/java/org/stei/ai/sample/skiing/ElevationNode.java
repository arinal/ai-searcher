package org.stei.ai.sample.skiing;

import java.util.*;

public class ElevationNode implements Comparable<ElevationNode> {
    private final int x;
    private final int y;
    private final int elevation;
    private final HashMap<Direction, ElevationNode> neighbours = new HashMap<>();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ElevationNode(int x, int y, int elevation) {
        this.x = x;
        this.y = y;
        this.elevation = elevation;
    }

    public ElevationNode getNode(Direction direction) {
        return neighbours.get(direction);
    }

    public int getDelta(Direction direction) {
        ElevationNode node = getNode(direction);
        return node == null? 0 : compareTo(node);
    }

    public ElevationNode create(Direction direction, int elevation) {
        return new ElevationNode(x + direction.deltaX(), y + direction.deltaY(), elevation);
    }

    public ElevationNode createNode(Direction direction) {
        return create(direction, elevation);
    }

    public void setNode(ElevationNode node, Direction direction) {
        if (node == null)
            return;
        if (!createNode(direction).coordinateEquals(node))
            throw new IllegalArgumentException("node");
        neighbours.put(direction, node);
    }

    public List<ElevationNode> getDownWards() {
        List<ElevationNode> result = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            ElevationNode node = getNode(dir);
            if (node != null && node.elevation < elevation)
                result.add(node);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, elevation);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass())
            return false;
        ElevationNode otherNode = (ElevationNode)other;
        return coordinateEquals(otherNode) && elevation == otherNode.elevation;
    }

    public boolean coordinateEquals(ElevationNode other) {
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return elevation + "";
    }

    @Override
    public int compareTo(ElevationNode node) {
        return elevation - node.elevation;
    }
}

