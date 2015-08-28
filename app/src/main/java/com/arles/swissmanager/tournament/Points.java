package com.arles.swissmanager.tournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the number of points per game result.
 * Created by Admin on 30.06.2015.
 */
public enum Points {
    Win(2),
    Draw(1),
    Bye(4),
    Lose(0);

    private final int value;
    Points(int point) {
        value = point;
    }

    public int get() {
        return value;
    }

    public static List<Points> getPointsNames() {
        List<Points> list = new ArrayList<>();
        list.add(Points.Lose);
        list.add(Points.Win);
        list.add(Points.Draw);
        return list;
    }
}
