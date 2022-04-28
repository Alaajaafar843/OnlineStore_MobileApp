package com.hell.buildingsupplies;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class showRoom extends AppCompatActivity {

    ListView list;
    Button refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_room);

        Intent i = getIntent();
        int pid = i.getIntExtra("id" , 0);
        String name = i.getStringExtra("username");
        String email = i.getStringExtra("email");

        list = findViewById(R.id.listProducts);
        refresh = findViewById(R.id.buttonRefresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.products.clear();
                recreate();
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        MyListAdapter ad = new MyListAdapter(this, queue);
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
                        Toast.makeText(showRoom.this , "couldn't load products" , Toast.LENGTH_SHORT).show();
                    }

                }
                ad.notifyDataSetChanged();

            }

        } , null);

        queue.add(request);
        list.setAdapter(ad);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(showRoom.this , OrderActivity.class);
                i.putExtra("pos" , position);
                i.putExtra("id" , pid);
                startActivity(i);
            }
        });

    }
}