package com.arles.swissmanager.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the number of points per game result.
 * Created by Admin on 30.06.2015.
 */
public enum Points {
    WIN(2),
    DRAW(1),
    BYE(4),
    LOSE(0);

    private final int value;
    Points(int point) {
        value = point;
    }

    public int get() {
        return value;
    }

    public static List<Points> getPointsNames() {
        List<Points> list = new ArrayList<>();
        list.add(Points.LOSE);
        list.add(Points.WIN);
        list.add(Points.DRAW);
        return list;
    }
}
