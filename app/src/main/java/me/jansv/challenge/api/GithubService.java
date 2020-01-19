package me.jansv.challenge.api;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.jansv.challenge.R2;
import me.jansv.challenge.model.Repos;
import me.jansv.challenge.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("/search/users")
    Call<UserList> getUserList(@Query("q") @Nullable String filter);

    @GET("users/{username}/repos")
    Call<ArrayList<Repos>> getRepos(@Path("username") String username);
}
