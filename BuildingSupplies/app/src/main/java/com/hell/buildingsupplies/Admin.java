package com.hell.buildingsupplies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Admin extends AppCompatActivity {

    TextView mngUsers , mngCat , mngProd , adminInfo;
    private int id;
    private String username , email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mngUsers = findViewById(R.id.mngUsers);
        mngCat = findViewById(R.id.mngCat);
        mngProd = findViewById(R.id.mngProd);
        adminInfo = findViewById(R.id.adminInfo);

        Intent i = getIntent();
        id = i.getIntExtra("id", 0);
        username = i.getStringExtra("username");
        email = i.getStringExtra("email");

        adminInfo.setText(" user: "+username+" email: "+email);


        mngUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this , ManageUsers.class);
                startActivity(i);
            }
        });
        mngCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this , ManageCat.class);
                startActivity(i);
            }
        });
        mngProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this , ManageProd.class);
                startActivity(i);
            }
        });


    }
}