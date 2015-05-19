package com.arles.swissmanager.ui.presenter;

        import android.util.Log;
        import android.view.View;
        import android.widget.Toast;

        import com.arles.swissmanager.ui.activity.MainActivity;
        import com.arles.swissmanager.ui.activity.Navigator;

        import javax.inject.Inject;
        import javax.inject.Singleton;

/**
 * MainPresenter created to show MainActivity.
 *
 * Created by Admin on 18.05.2015.
 */
@Singleton
public class MainPresenter extends Presenter {

    private IView view;
    private Navigator mNavigator;

    @Inject
    public MainPresenter(Navigator navigator) {
        mNavigator = navigator;
    }

    public void setView(IView view) {
        this.view = view;
    }

    public void createNew() {
        mNavigator.openNewPlayerActivity();
    }

    /**
     * View interface created to abstract the view implementation used in this presenter.
     */
    public interface IView {

    }

}
