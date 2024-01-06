package model;

import java.util.ArrayList;

public class Model {
    private ArrayList<Point> points;
    private final int MAX_POINTS = 8;

    public Model() {
        points = new ArrayList<>();
    }

    public void setPoint(Point point) {
        points.add(point);

        if (points.size() > MAX_POINTS) {
            points.remove(0);
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}