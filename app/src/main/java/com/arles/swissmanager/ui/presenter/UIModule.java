package com.arles.swissmanager.ui.presenter;

/**
 * Dagger module created to provide TvShows UI dependencies like RendererBuilders or presenters.
 *
 * Created by Admin on 15.05.2015.
 */

import com.arles.swissmanager.ui.activity.MainActivity;
import com.arles.swissmanager.ui.activity.NewPlayerActivity;
import com.arles.swissmanager.ui.fragment.NavigationDrawerFragment;

import dagger.Module;

@Module(complete = false,
        injects = {
                MainActivity.class, NewPlayerActivity.class, NavigationDrawerFragment.class
        })
public final class UIModule {
}
