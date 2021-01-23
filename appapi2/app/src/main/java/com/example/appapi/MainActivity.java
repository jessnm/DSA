//package com.vogella.android.recyclerview;
package com.example.appapi;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnAddTrack, btnUpdateTrack;

    TrackInterface trackInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        btnAddTrack = findViewById(R.id.btnAddTrack);
        btnUpdateTrack = findViewById(R.id.btnUpdateTrack);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(TrackInterface.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        trackInterface = retrofit.create(TrackInterface.class);

        Call<List<Track>> input = trackInterface.getTracks();
        input.enqueue(new Callback<List<Track>>() {

            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "ERROR " + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Track> tracks = response.body();

                mAdapter = new MyAdapter(tracks);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Tracks not found.", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"Hola",Toast.LENGTH_LONG).show();
                Intent i= new Intent(MainActivity.this,ActivityAdd.class);
                startActivity(i);
            }
        });

        btnUpdateTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"Hola",Toast.LENGTH_LONG).show();
                Intent i= new Intent(MainActivity.this,ActivityUpdate.class);
                startActivity(i);
            }
        });
    }



    private void createTracksAPI() {
/*        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(Track.class, new TrackDeserializer())
                .create();*/


    }
}