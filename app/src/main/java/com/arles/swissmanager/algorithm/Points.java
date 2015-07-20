package com.arles.swissmanager.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the number of points per game result.
 * Created by Admin on 30.06.2015.
 *//*
public class Points {
    public static final int WIN = 2;
    public static final int DRAW = 1;
    public static final int BYE = 4;
    public static final int LOSE = 0;

    public static List<Points> getPointsNames() {
        List<Points> l = new ArrayList<>();
        l.add(Points.WIN);

        return  l;
    }
}*/

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

    public static List<Points> list() {
        List<Points> l = new ArrayList<>();
        l.add(Points.LOSE);
        l.add(Points.WIN);
        l.add(Points.DRAW);
        return l;
    }
}
