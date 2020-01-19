package me.jansv.challenge.ui.screens.repos;

import android.util.Log;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.Repos;
import me.jansv.challenge.model.UserList;
import me.jansv.challenge.ui.screens.users.UsersContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposPresenter implements ReposContract.Presenter {

    private ReposContract.View mView;

    private List<String> ListRepos= new ArrayList<>();

    private GithubService api;

    public ReposPresenter(GithubService api) {

        this.api = api;

    }

    public void setUserClicked(String userClicked) {
        UserClicked = userClicked;
    }

    private String UserClicked="";

    public List<String> getListRepos() {
        return ListRepos;
    }

    public void setListRepos(List<String> listRepos) {
        ListRepos = listRepos;
    }

    @Override
    public void bind(ReposContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }


    @Override
    public void fetchReposList() {
        api.getRepos(UserClicked).enqueue(new Callback<ArrayList<Repos>>() {
            @Override
            public void onResponse(Call<ArrayList<Repos>> call, Response<ArrayList<Repos>> response) {
                if (response.isSuccessful()) {
                    ListRepos.clear();
                    for(Repos repos : response.body()) {
                        String name = repos.getName();
                        ListRepos.add(UpFirstChar(name));
                    }

                    ReposActivity.adapter.notifyDataSetChanged();

                } else {
                    Log.d("OnResponse", "API Reponse Failed");
                }
            }


            @Override
            public void onFailure(Call<ArrayList<Repos>>call, Throwable t) {

            }
        });
    }

    protected String UpFirstChar(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        } else {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }

}
