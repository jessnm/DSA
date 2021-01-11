package com.example.minim2jessicanm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    GitHubAPI gitHubAPI, gitHubAPI2;

    Retrofit retrofit;
    TextView userGit,Followers,Following,Repositories;
    ImageView ImageUser;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userGit = (TextView) findViewById(R.id.userGit);
        Followers = (TextView) findViewById(R.id.Followers);
        Following = (TextView) findViewById(R.id.Following);
        Repositories = (TextView) findViewById(R.id.Repositories);
        ImageUser = (ImageView) findViewById(R.id.ImageUser);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Intent i = getIntent();
        String username  = (String) i.getSerializableExtra("username");

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic("jessnm", "0c9cb11fab7e48b3ed710e6a02ee51abc31a577a"));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubAPI.ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubAPI = retrofit.create(GitHubAPI.class);

        Call<User> input = gitHubAPI.getUserDetails(username);
        input.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "ERROR " + response.code(), Toast.LENGTH_SHORT).show();
                }

                User user = response.body();

                userGit.setText(user.getLogin());
                Picasso.get().load(user.getAvatarUrl()).into(ImageUser);
                Followers.setText("Followers: " + user.getFollowers());
                Following.setText("Following: " + user.getFollowing());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Tracks not found.", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Repos>> input2 = gitHubAPI.getRepoDetails(username);
        input2.enqueue(new Callback<List<Repos>>() {

            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "ERROR " + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Repos> repo = response.body();

                mAdapter = new MyAdapter(repo);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Tracks not found.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}