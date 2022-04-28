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

public class ProdctInfo extends AppCompatActivity {

    EditText productName , productID , catName , Quantity , Price;
    Button delete , update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodct_info);

        Intent i = getIntent();
        int position = i.getIntExtra("pos" , 0);


        productName = findViewById(R.id.productName1);
        productID = findViewById(R.id.pid1);
        catName = findViewById(R.id.cid1);
        Quantity = findViewById(R.id.quantity1);
        Price = findViewById(R.id.price1);
        delete = findViewById(R.id.delete1);
        update = findViewById(R.id.update1);


        productName.setText(Data.products.get(position).getProductName());
        productID.setText(String.valueOf(Data.products.get(position).getPid()));
        catName.setText(Data.products.get(position).getCategoryName());
        Quantity.setText(String.valueOf(Data.products.get(position).getQuantity()));
        Price.setText(String.valueOf(Data.products.get(position).getPrice()));


        final RequestQueue queue = Volley.newRequestQueue(this);

        delete.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/DeleteProduct.php";

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(ProdctInfo.this , response , Toast.LENGTH_SHORT).show();
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(ProdctInfo.this , error.toString() , Toast.LENGTH_SHORT).show();

                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("pid" , productID.getText().toString());
                    return params;
                }
            };

            queue.add(request);

        });



        update.setOnClickListener(v -> {

            String url = "https://alaajsite.000webhostapp.com/updateProduct.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(ProdctInfo.this , response , Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(ProdctInfo.this , error.toString() , Toast.LENGTH_SHORT).show();


                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> params = new HashMap<>();
                    params.put("productName" , productName.getText().toString());
                    params.put("prodID" , productID.getText().toString());
                    params.put("catName" , catName.getText().toString());
                    params.put("quantity" , Quantity.getText().toString());
                    params.put("price" , Price.getText().toString());
                    params.put("key" , "ADGJLKHFSwrtuYRQ");
                    return params;
                }
            };
            queue.add(stringRequest);

        });



    }
}