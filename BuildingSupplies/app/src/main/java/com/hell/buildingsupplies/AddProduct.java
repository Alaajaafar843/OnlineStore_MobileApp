package com.hell.buildingsupplies;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {

    EditText productName , cid , quantity , price , pid;
    Button add , select , upload;
    ImageView iv;
    ProgressBar prog;

    private MyImage myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = findViewById(R.id.Prodname);
        cid = findViewById(R.id.cid);
        pid = findViewById(R.id.prodID);
        quantity = findViewById(R.id.quantity);
        price = findViewById(R.id.price);
        add = findViewById(R.id.addProduct);
        prog = findViewById(R.id.progressBaraddprod);
        select = findViewById(R.id.SelectImage);
        iv = findViewById(R.id.productImg);
        myImage = new MyImage(this, iv, prog);

        final RequestQueue queue = Volley.newRequestQueue(this);

        add.setOnClickListener(v -> {

            if(iv == null){
                add.setEnabled(false);
            }

            String url = "https://alaajsite.000webhostapp.com/add.php";
            prog.setVisibility(View.VISIBLE);
            add.setEnabled(false);

            StringRequest request1 = myImage.uplaodImage(pid.getText().toString());
            queue.add(request1);

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    Toast.makeText(AddProduct.this , response , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    add.setEnabled(true);
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AddProduct.this , error.toString() , Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.INVISIBLE);
                    add.setEnabled(true);

                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String , String> params = new HashMap<>();
                    params.put("cid" , cid.getText().toString());
                    params.put("pid" , pid.getText().toString());
                    params.put("name" , productName.getText().toString());
                    params.put("quantity" , quantity.getText().toString());
                    params.put("price" , price.getText().toString());
                    params.put("key" , "ADGJLKHFSwrtuYRQ");

                    return params;
                }
            };

            queue.add(request);

        });

        select.setOnClickListener(view -> {
            mGetContent.launch("image/*");
        });

    }

    // get selected image and update ImageView
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    myImage.getIv().setImageURI(uri);
                }
            });
    }