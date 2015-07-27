package com.arles.swissmanager.ui.fragment;

/**
 * Interface handles resume operation when we switch between fragments more than one time.
 * Created by Admin on 27.07.2015.
 */
public interface FragmentSwitchListener {

    /**
     * Called when fragment became visible when onPageSelected invokes.
     */
    void onVisible();
}
