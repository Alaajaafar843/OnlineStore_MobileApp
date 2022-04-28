package com.hell.buildingsupplies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class CatInfo extends AppCompatActivity {

    EditText cid , categoryName;
    Button delete , update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_info);

        Intent i = getIntent();
        int position = i.getIntExtra("position" , 0);

        cid = findViewById(R.id.cid5);
        categoryName = findViewById(R.id.Categoryname1);
        delete = findViewById(R.id.deleteCat);
        update = findViewById(R.id.updateCat);

        cid.setText(String.valueOf(Data.categories.get(position).getCid()));
        categoryName.setText(Data.categories.get(position).getName());


        final RequestQueue queue = Volley.newRequestQueue(this);

        delete.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/deleteCategory.php";

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(CatInfo.this , response , Toast.LENGTH_SHORT).show();
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(CatInfo.this , error.toString() , Toast.LENGTH_SHORT).show();

                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("cid" , cid.getText().toString());
                    return params;
                }
            };

            queue.add(request);

        });



        update.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/updateCategory.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(CatInfo.this , response , Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(CatInfo.this , error.toString() , Toast.LENGTH_SHORT).show();


                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("cid" , cid.getText().toString());
                    params.put("catName" , categoryName.getText().toString());
                    params.put("key" , "ADGJLKHFSwrtuYRQ");
                    return params;
                }
            };
            queue.add(stringRequest);

        });


    }
}