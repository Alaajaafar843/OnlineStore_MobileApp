package com.hell.buildingsupplies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    Button signup;
    EditText username , password , email;
    ProgressBar prog;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = findViewById(R.id.login);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.email);
        prog = findViewById(R.id.prog2);
        log = findViewById(R.id.log);


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this , LogIn.class);
                startActivity(i);
            }
        });

        final RequestQueue queue = Volley.newRequestQueue(this);

        signup.setOnClickListener((v) -> {
            String url = "https://alaajsite.000webhostapp.com/addUser.php";

            prog.setVisibility(View.VISIBLE);
            signup.setEnabled(false);


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(SignUp.this , response , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    signup.setEnabled(true);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(SignUp.this , error.toString() , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    signup.setEnabled(true);

                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String ,String> params = new HashMap<>();
                    params.put("username" , username.getText().toString());
                    params.put("password" , password.getText().toString());
                    params.put("email" , email.getText().toString());
                    params.put("key" , "ADGJLKHFSwrtuYRQ");
                    return params;
                }
            };

            queue.add(request);

        });


    }
}