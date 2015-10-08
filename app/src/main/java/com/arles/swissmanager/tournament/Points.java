/*
 * Copyright (C) 2015 Arles. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    private int value;
    Points(int point) {
        value = point;
    }

    public int get() {
        return value;
    }

    public void set(int point) {
        value = point;
    }

    public static List<Points> getPointsNames() {
        List<Points> list = new ArrayList<>();
        list.add(Points.Lose);
        list.add(Points.Win);
        list.add(Points.Draw);
        return list;
    }
}
