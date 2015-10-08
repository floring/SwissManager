/**
 * Copyright (C) 2015 Arles
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
 * Abstract presenter to work as base for every presenter created in the application.
 * This presenter declares some methods to attach the fragment/activity lifecycle.
 *
 * Created by Admin on 07.05.2015.
 */
public abstract class Presenter {

    /**
     * Called when the presenter is initialized, this method represents the start of the presenter
     * lifecycle.
     */
    //public abstract void initialize();

    /**
     * Called when the presenter is resumed. After the initialization and when the presenter comes
     * from a pause state.
     */
    //public abstract void resume();

    /**
     * Called when the presenter is paused.
     */
    //public abstract void pause();

    /**
     * Called when activity/fragment (view) must be initialized.
     */
    public abstract void initializeViewComponent();
}
