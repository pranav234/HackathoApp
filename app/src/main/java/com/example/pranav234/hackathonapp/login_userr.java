package com.example.pranav234.hackathonapp;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login_userr extends AppCompatActivity {
Button login;
TextView user_name,user_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_userr);
        login = findViewById(R.id.login_as_user);
        user_name=findViewById(R.id.user_name);
        user_password=findViewById(R.id.user_pswrd);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a =(user_name.getText().toString());
                String b = (user_password.getText().toString());
                Log.i("aaaaaaa",a);
                Log.i("bbbbbbb",b);
                if(a.equals("Xavier") && b.equals("pswrd")){
                Intent intent = new Intent(login_userr.this,tender_recycler.class);
                startActivity(intent);
                finish();}
                else {
                    Toast.makeText(v.getContext(),"Wrong login Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
