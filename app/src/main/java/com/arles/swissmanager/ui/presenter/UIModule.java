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

package com.arles.swissmanager.ui.presenter;

/**
 * Dagger module created to provide UI dependencies like presenters.
 * <p/>
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
