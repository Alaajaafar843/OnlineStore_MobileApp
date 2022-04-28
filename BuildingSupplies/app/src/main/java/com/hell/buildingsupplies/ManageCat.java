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

public class ManageCat extends AppCompatActivity {

    ListView list;
    Button addCat , refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cat);

        list = findViewById(R.id.listCat);
        addCat = findViewById(R.id.addCat);
        refresh = findViewById(R.id.refreshCat);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.categories.clear();
                recreate();
            }
        });

        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageCat.this , AddCat.class);
                startActivity(i);
            }
        });


        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this , android.R.layout.simple_list_item_1 , Data.categories);
        list.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://alaajsite.000webhostapp.com/getCategories.php";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0 ; i<response.length() ; i++){

                    try{

                        JSONObject row = response.getJSONObject(i);
                        int cid = row.getInt("cid");
                        String name = row.getString("name");
                        Data.categories.add(new Category(cid , name));

                    }catch (Exception e){
                        Toast.makeText(ManageCat.this , "couldn't load Categories" , Toast.LENGTH_SHORT).show();
                    }

                }
                adapter.notifyDataSetChanged();

            }

        } , null);

        queue.add(request);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageCat.this , CatInfo.class);
                i.putExtra("position" , position);
                startActivity(i);
            }
        });
    }
}