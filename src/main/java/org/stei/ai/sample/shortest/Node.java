package org.stei.ai.sample.shortest;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String tag;
    private Map<Node, Integer> edges;

    public String getTag() {
        return tag;
    }

    public Node(String tag) {
        this.tag = tag;
        edges = new HashMap<>();
    }

    public void addEdge(Node node, int distance) {
        edges.put(node, distance);
    }

    public void addEdge2Side(Node node, int distance) {
        addEdge(node, distance);
        node.addEdge(this, distance);
    }

    public int getDistance(Node node) {
        return edges.get(node);
    }

    public Iterable<Node> getNeighbours() {
        return edges.keySet();
    }

    @Override
    public String toString() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        if (tag != null ? !tag.equals(node.tag) : node.tag != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }
}

