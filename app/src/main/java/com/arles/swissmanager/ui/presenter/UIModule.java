package com.arles.swissmanager.ui.presenter;

/**
 * Dagger module created to provide TvShows UI dependencies like RendererBuilders or presenters.
 *
 * Created by Admin on 15.05.2015.
 */

import com.arles.swissmanager.ui.activity.NewPlayerActivity;
import com.arles.swissmanager.ui.activity.PlayerDataActivity;
import com.arles.swissmanager.ui.activity.RoundActivity;
import com.arles.swissmanager.ui.activity.TourneyActivity;
import com.arles.swissmanager.ui.fragment.PlayerTabFragment;
import com.arles.swissmanager.ui.fragment.RoundTabFragment;

import dagger.Module;

@Module(complete = false,
        injects = {
                NewPlayerActivity.class, TourneyActivity.class, PlayerTabFragment.class, RoundTabFragment.class, RoundActivity.class, PlayerDataActivity.class
        })
public final class UIModule {
}
