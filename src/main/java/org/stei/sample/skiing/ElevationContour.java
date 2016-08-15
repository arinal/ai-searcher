package org.stei.sample.skiing;

import java.util.Arrays;

public class ElevationContour {

    private final ElevationNode[][] contours;

    public int getLength() {
        return contours.length;
    }

    public int getWidth() {
        return contours[0].length;
    }

    public ElevationContour(int width, int height, int... elevations) {
        if (elevations.length != width * height)
            throw new IllegalArgumentException("elevations");
        contours = new ElevationNode[height][width];
        int i = 0;
        for (int e : elevations) {
            int x = i % width;
            int y = i++ / width;
            contours[y][x] = new ElevationNode(x, y, e);
        }
        Arrays.stream(contours).forEach(ns -> Arrays.stream(ns).forEach(n -> setupLinks(n)));
    }

    public ElevationNode get(int x, int y) {
        if (x >= 0 && x < contours[0].length && y >= 0 && y < contours.length)
            return contours[y][x];
        return null;
    }

    private void setupLinks(ElevationNode node) {
        Arrays.stream(Direction.values()).forEach(dir -> {
            int x = node.getX() + dir.deltaX();
            int y = node.getY() + dir.deltaY();
            ElevationNode neighbour = get(x, y);
            if (neighbour != null)
                node.setNode(neighbour, dir);
        });
    }
}
