package com.arles.swissmanager.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.arles.swissmanager.R;
import com.arles.swissmanager.SwissManagerApplication;
import com.arles.swissmanager.di.ActivityModule;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Base activity created to be extended by every activity class.
 * This class provides dependency injection configuration, ButterKnife Android library configuration and some methods
 * common to every activity.
 * Created by Admin on 10.04.2015.
 */
public abstract class BaseActivity extends ActionBarActivity {

    private ObjectGraph objectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();



    }

    /**
     * Method used to resolve dependencies provided by Dagger modules. Inject an object to provide
     * every @Inject annotation contained.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Get a list of Dagger modules with Activity scope needed to this Activity.
     *
     * @return modules with new dependencies to provide.
     */
    protected abstract List<Object> getModules();

    /**
     * Create a new Dagger ObjectGraph to add new dependencies using a plus operation and inject the
     * declared one in the activity. This new graph will be destroyed once the activity lifecycle
     * finish.
     * <p/>
     * This is the key of how to use Activity scope dependency injection.
     */
    private void injectDependencies() {
        SwissManagerApplication swissApp = (SwissManagerApplication) getApplication();
        List<Object> modulesList = getModules();
        modulesList.add(new ActivityModule(this));
        objectGraph = swissApp.plus(modulesList);
        inject(this);
    }

    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     */
    private void injectViews() {
        //ButterKnife.inject(this);
    }


}
