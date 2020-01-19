package me.jansv.challenge.ui.screens.repos;

import java.util.ArrayList;
import java.util.List;

import me.jansv.challenge.model.Repos;
import me.jansv.challenge.model.User;
import me.jansv.challenge.ui.screens.BasePresenter;
import me.jansv.challenge.ui.screens.BaseView;

public interface ReposContract {

    interface View extends BaseView {
        void showNoNetworkMessage();

        void showNetworkErrorMessage();

        void showReposList(List<Repos> repos);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchReposList();
    }
}
