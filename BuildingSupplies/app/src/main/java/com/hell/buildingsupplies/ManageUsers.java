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

import java.util.ArrayList;

public class ManageUsers extends AppCompatActivity {

    ListView list;
    Button addUser , refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        list = findViewById(R.id.list);
        addUser = findViewById(R.id.addUser);
        refresh = findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.users.clear();
                recreate();
            }
        });


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageUsers.this , AddUser.class);
                startActivity(i);
            }
        });

        //ArrayList<User> users = new ArrayList<User>();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this , android.R.layout.simple_list_item_1 , Data.users);
        list.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://alaajsite.000webhostapp.com/getUsers.php";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0 ; i<response.length() ; i++){

                    try{

                        JSONObject row = response.getJSONObject(i);
                        int id = row.getInt("id");
                        String username = row.getString("username");
                        String email = row.getString("email");
                        Data.users.add(new User(id , username , email));

                    }catch (Exception e){
                        Toast.makeText(ManageUsers.this , "couldn't load users" , Toast.LENGTH_SHORT).show();
                    }

                }
                adapter.notifyDataSetChanged();

            }

        } , null);

        queue.add(request);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageUsers.this , UserProfile.class);
                i.putExtra("position" , position);
                startActivity(i);
            }
        });

    }
}