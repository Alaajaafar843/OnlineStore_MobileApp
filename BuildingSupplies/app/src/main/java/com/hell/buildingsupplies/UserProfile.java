package com.hell.buildingsupplies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity {

    EditText name , id , email;
    Button delete , update;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.name);
        id = findViewById(R.id.ID);
        email = findViewById(R.id.email2);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);

        Intent i = getIntent();
        pos = i.getIntExtra("position" , 0);

        name.setText(Data.users.get(pos).getUsername());
        id.setText(String.valueOf(Data.users.get(pos).getId()));
        email.setText(Data.users.get(pos).getEmail());

        final RequestQueue requestQueue = Volley.newRequestQueue(UserProfile.this);

        delete.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/deleteUser.php";

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                       Toast.makeText(UserProfile.this , response , Toast.LENGTH_SHORT).show();
                       finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UserProfile.this , error.toString() , Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("username" , name.getText().toString());
                    params.put("email" , email.getText().toString());
                    params.put("id" , id.getText().toString());
                    return params;
                }
            };

            requestQueue.add(request);

        });


        update.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/updateUser.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(UserProfile.this , response , Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(UserProfile.this , error.toString() , Toast.LENGTH_SHORT).show();


                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("username" , name.getText().toString());
                    params.put("email" , email.getText().toString());
                    params.put("id" , id.getText().toString());
                    params.put("key" , "ADGJLKHFSwrtuYRQ");
                    return params;
                }
            };
            requestQueue.add(stringRequest);

        });




    }
}