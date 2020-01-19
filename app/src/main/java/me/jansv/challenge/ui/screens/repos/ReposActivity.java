package me.jansv.challenge.ui.screens.repos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import me.jansv.challenge.R;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.Repos;
import me.jansv.challenge.model.User;

public class ReposActivity extends AppCompatActivity {

    @Inject
    GithubService api;
    ListView lv;

    public static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        String UserClicked = getIntent().getStringExtra("username");
        lv = findViewById(R.id.listView);

        ReposPresenter reposPresenter = new ReposPresenter(api);
        reposPresenter.setUserClicked(UserClicked);
        reposPresenter.fetchReposList();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reposPresenter.getListRepos());
        lv.setAdapter(adapter);

    }
}
