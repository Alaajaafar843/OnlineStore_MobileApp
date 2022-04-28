package com.hell.buildingsupplies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class AddUser extends AppCompatActivity {

    EditText username , email , password , role;
    Button add;
    ProgressBar prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = findViewById(R.id.usern);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.pass);
        role = findViewById(R.id.role);
        add = findViewById(R.id.add);
        prog = findViewById(R.id.progress2);

        final RequestQueue queue = Volley.newRequestQueue(this);

        add.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/addUser2.php";
            prog.setVisibility(View.VISIBLE);
            add.setEnabled(false);

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    Toast.makeText(AddUser.this , response , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    add.setEnabled(true);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AddUser.this , error.toString() , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    add.setEnabled(true);

                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String , String> params = new HashMap<>();
                            params.put("username" , username.getText().toString());
                            params.put("email" , email.getText().toString());
                            params.put("password" , password.getText().toString());
                            params.put("role" , role.getText().toString());
                            params.put("key" , "ADGJLKHFSwrtuYRQ");

                            return params;
                }
            };

            queue.add(request);



        });
    }
}