package com.example.minim2jessicanm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    private ProgressBar loginbar;
    public TextView editTextName;
    public String nameUser;
    private EditText name;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        //Inicializar componentes
        name = (EditText) findViewById(R.id.usernameLogin);
        btnLogin = (Button) findViewById(R.id.btnLoggin);
        editTextName = this.findViewById(R.id.usernameLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameUser = editTextName.getText().toString();
                Intent i= new Intent(com.example.minim2jessicanm.Login_Activity.this,MainActivity.class);
                i.putExtra("username", nameUser);
                startActivity(i);
            }
        });
    }

}