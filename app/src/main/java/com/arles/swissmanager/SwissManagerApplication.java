package com.arles.swissmanager;

import android.app.Application;
import android.os.Bundle;

import com.arles.swissmanager.di.RootModule;

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

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
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
}
