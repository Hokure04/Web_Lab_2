package model;

import java.util.ArrayList;

public class Model {

    public static ArrayList<Point> points = new ArrayList<>();
    public static final int MAX_POINTS = 8;

    public void setPoint(Point point){
        points.add(point);

        if(points.size()>MAX_POINTS){
            points.remove(0);
        }
    }
}