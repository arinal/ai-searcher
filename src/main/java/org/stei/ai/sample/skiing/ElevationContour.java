package org.stei.ai.sample.skiing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ElevationContour {

    private HashMap<Integer, ElevationNode> contours = new HashMap<>();

    public ElevationContour(int width, int height, int... elevations) {
        if (elevations.length != width * height)
            throw new IllegalArgumentException("elevations");
        int i = 0;
        for (int e : elevations)
            put(i % width, i++ / width, e);
        contours.values().forEach(this::setupLinks);
    }

    private void setupLinks(ElevationNode node) {
        Arrays.stream(Direction.values()).forEach(dir -> {
            int x = node.getX() + dir.deltaX();
            int y = node.getY() + dir.deltaY();
            node.setNode(get(x, y), dir);
        });
    }

    private void put(int x, int y, int elevation) {
        contours.put(Objects.hash(x, y), new ElevationNode(x, y, elevation));
    }

    public ElevationNode get(int x, int y) {
        return contours.get(Objects.hash(x, y));
    }
}
