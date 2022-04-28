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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    EditText username , password;
    Button login;
    ProgressBar prog;
    Boolean error;
    TextView sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        prog = findViewById(R.id.prog);
        login = findViewById(R.id.login);
        sign = findViewById(R.id.sign);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this , SignUp.class);
                startActivity(i);
            }
        });


        final RequestQueue queue = Volley.newRequestQueue(LogIn.this);


        login.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/login.php";

            prog.setVisibility(View.VISIBLE);
            login.setEnabled(false);


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONObject user = jsonObject.getJSONObject("user");


                        if(success.equals("logged in")){
                            int id = user.getInt("id");
                            String name = user.getString("username");
                            String email = user.getString("email");
                            String ROLE = user.getString("ROLE");

                            if(ROLE.equals("ADMIN") || ROLE.equals("admin")){
                                Intent i = new Intent(LogIn.this , Admin.class);
                                i.putExtra("id" , id);
                                i.putExtra("username" , name);
                                i.putExtra("email" , email);
                                startActivity(i);
                            }
                            else if(ROLE.equals("")){
                                Intent i = new Intent(LogIn.this , showRoom.class);
                                i.putExtra("id" , id);
                                i.putExtra("username" , name);
                                i.putExtra("email" , email);
                                startActivity(i);
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    prog.setVisibility(View.INVISIBLE);
                    login.setEnabled(true);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(LogIn.this , error.toString() , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    login.setEnabled(true);

                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String , String> params = new HashMap<>();
                    params.put("username" , username.getText().toString());
                    params.put("password" , password.getText().toString());
                    params.put("key" , "ADGJLKHFSwrtuYRQ");
                    return params;
                }
            };

            queue.add(request);

        });



    }
}