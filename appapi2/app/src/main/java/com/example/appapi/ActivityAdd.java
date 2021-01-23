package com.example.appapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActivityAdd extends Activity {

    Button btnAccept;
    TextView txtSinger,txtTitle;
    String singer,title;
    Track track;
    TrackInterface trackInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnAccept = findViewById(R.id.btnAccept);
        txtSinger = findViewById(R.id.txtSingerAdd);
        txtTitle = findViewById(R.id.txtTitleAdd);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singer = txtSinger.getText().toString();
                title = txtTitle.getText().toString();
                Track trackToCreate = new Track();
                //
                trackToCreate.setSinger(singer);
                trackToCreate.setTitle(title);
                try {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(TrackInterface.ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    trackInterface = retrofit.create(TrackInterface.class);
                    Call<Track> trackCall = trackInterface.addTrack(trackToCreate);
                    /* Android Doesn't allow synchronous execution of Http Request and so we must put it in queue*/
                    trackCall.enqueue(new Callback<Track>() {

                        @Override
                        public void onResponse(Call<Track> call, Response<Track> response) {
                            Toast.makeText(ActivityAdd.this,"it arrives here ",Toast.LENGTH_LONG);
                            if (response.code() == 201) {
                                NotifyUser("Successful");
                                ActivityAdd.this.track = response.body();
                                NotifyUser("Track" + track);
                                Intent i= new Intent(ActivityAdd.this,MainActivity.class);
                                startActivity(i);
                                //objetosList = usuario.objetosList;
                                //NotifyUser("objetos" + objetosList);
                                //Log.d("MYAPP", "La lista de objetos es"+objetosList);
                    /*    mAdapter = new MyAdapter(objetosList);
                        recyclerView.setAdapter(mAdapter);*/
                                //buildRecyclerView();
                                //!!!!!!!!!!!!Lanzar una nueva actividad con otra pantalla
                            }
                            if (response.code() == 409) {NotifyUser("User Duplicado , Inserta de nuevo");}
                        }

                        private void NotifyUser(String MSG) {
                            Toast toast = Toast.makeText(ActivityAdd.this,MSG,Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        @Override
                        public void onFailure(Call<Track> call, Throwable t) {
                            NotifyUser("Error Server");
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(ActivityAdd.this,"Exception: ",Toast.LENGTH_LONG);
                }
            }
        });
    }
}
