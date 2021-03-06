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

package com.arles.swissmanager;

import android.app.Application;

import com.arles.swissmanager.di.RootModule;
import com.arles.swissmanager.tournament.Match;
import com.arles.swissmanager.tournament.Player;
import com.arles.swissmanager.tournament.Test;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Android Application extension created to get the control of the application lifecycle.
 * <p/>
 * This project is using Dependency Injection based on Dagger as dependency injector. The
 * ObjectGraph field used in this class is the dependency container that is going to provide every
 * dependency declared in Dagger modules. Take a look to BaseActivity to see how the Activity scope
 * injection works using the plus method implemented here.
 * Created by Admin on 10.04.2015.
 */
public class SwissManagerApplication extends Application {

    private ObjectGraph objectGraph;

    private static List<Player> mDataList;
    //   private static final String[] NAMES_LIST = new String[] {"John", "Alex", "David", "Martin", "Brian", "Harry"};
    private static final String[] NAMES_LIST = new String[]{"John", "Alex", "David",};

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();

        setTestData();
        Test.main();
    }

    /**
     * Extend the dependency container graph will new dependencies provided by the modules passed as
     * arguments.
     *
     * @param modules used to populate the dependency container.
     */
    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }

    /**
     * Inject every dependency declared in the object with the @Inject annotation if the dependency
     * has been already declared in a module and already initialized by Dagger.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    private void initializeDependencyInjector() {
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    private void setTestData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < NAMES_LIST.length; ++i) {
            mDataList.add(new Player(NAMES_LIST[i]));
        }
    }

    public static List<Player> getTestPlayersData() {
        return new ArrayList<>(mDataList);
    }

    public static List<Match> getTestMatchData() {
        List<Match> list = new ArrayList<>();
        list.add(new Match(new Player("John"), new Player("Alex")));
        list.add(new Match(new Player("Alex"), new Player("John")));
        list.add(new Match(new Player("David"), new Player("Martin")));
        list.add(new Match(new Player("Martin"), new Player("David")));
        list.add(new Match(new Player("Brian"), new Player("Harry")));
        list.add(new Match(new Player("Harry"), new Player("Brian")));
        return list;
    }
}
