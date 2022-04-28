package com.hell.buildingsupplies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ManageProd extends AppCompatActivity {

    ListView list;
    Button refresh , addProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_prod);

        list = findViewById(R.id.listProd);
        refresh = findViewById(R.id.refresh1);
        addProd = findViewById(R.id.addProd);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.products.clear();
                recreate();
            }
        });

        addProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageProd.this , AddProduct.class);
                startActivity(i);
            }
        });


        ArrayAdapter<Product> adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , Data.products);
        list.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://alaajsite.000webhostapp.com/getProducts.php";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0 ; i<response.length() ; i++){

                    try{

                        JSONObject row = response.getJSONObject(i);
                        int id = row.getInt("pid");
                        String catName = row.getString("category");
                        String Prodname = row.getString("name");
                        int quantity = row.getInt("quantity");
                        double price = row.getDouble("price");
                        Data.products.add(new Product(id , Prodname , quantity , price , catName));

                    }catch (Exception e){
                        Toast.makeText(ManageProd.this , "couldn't load products" , Toast.LENGTH_SHORT).show();
                    }

                }
                adapter.notifyDataSetChanged();

            }

        } , null);

        queue.add(request);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageProd.this , ProdctInfo.class);
                i.putExtra("pos" , position);
                startActivity(i);
            }
        });


    }
}