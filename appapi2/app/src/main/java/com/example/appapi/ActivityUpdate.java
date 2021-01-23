package com.example.appapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityUpdate extends AppCompatActivity {

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

                track.setSinger(singer);
                track.setTitle(title);
                try {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(TrackInterface.ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    trackInterface = retrofit.create(TrackInterface.class);
                    Call<Track> trackCall = trackInterface.updateTrack(track);
                    /* Android Doesn't allow synchronous execution of Http Request and so we must put it in queue*/
                    trackCall.enqueue(new Callback<Track>() {

                        @Override
                        public void onResponse(Call<Track> call, Response<Track> response) {
                            if (response.code() == 201) {
                                NotifyUser("Successful");
                                ActivityUpdate.this.track = response.body();
                                NotifyUser("Track" + track);
                                Intent i= new Intent(ActivityUpdate.this,MainActivity.class);
                                startActivity(i);
                            }
                            if (response.code() == 409) {NotifyUser("User Duplicado , Inserta de nuevo");}
                        }

                        private void NotifyUser(String MSG) {
                            Toast toast = Toast.makeText(ActivityUpdate.this,MSG,Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        @Override
                        public void onFailure(Call<Track> call, Throwable t) {
                            NotifyUser("Error Server");
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(ActivityUpdate.this,"Exception: ",Toast.LENGTH_LONG);
                }
            }
        });
    }
}