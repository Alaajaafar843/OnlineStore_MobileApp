package com.hell.buildingsupplies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class OrderActivity extends AppCompatActivity {

    TextView name , quantity , price;
    EditText address;
    ImageView iv;
    Button inc , sub , order;
    ProgressBar prog;
    double Price , initialPrice;
    int orderQuantity;
    int stockQuantity;
    int pid , uid;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Intent i = getIntent();
        int pos = i.getIntExtra("pos" , 0);

        Price = Data.products.get(pos).getPrice();
        initialPrice = Data.products.get(pos).getPrice();
        orderQuantity = 1;
        stockQuantity = Data.products.get(pos).getQuantity();
        pid = Data.products.get(pos).getPid();
        uid = i.getIntExtra("id" , 0);



        name = findViewById(R.id.Pname);
        quantity = findViewById(R.id.Pquantity);
        price = findViewById(R.id.Pprice);
        iv = findViewById(R.id.ProductImage);
        inc = findViewById(R.id.inc);
        sub = findViewById(R.id.sub);
        order = findViewById(R.id.placeOrder);
        address = findViewById(R.id.address);
        prog = findViewById(R.id.progressOrder);

        iv.setImageBitmap(MyListAdapter.getImage(pos));


        name.setText(Data.products.get(pos).getProductName());
        price.setText(String.valueOf(Price)+" LL.");
        quantity.setText(String.valueOf(orderQuantity));

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderQuantity==stockQuantity){
                    Toast.makeText(OrderActivity.this , "your order reached stock limit" , Toast.LENGTH_SHORT).show();
                }
                else if(orderQuantity>stockQuantity){
                    Toast.makeText(OrderActivity.this , "Sorry this item is not available" , Toast.LENGTH_SHORT).show();
                }
                else{
                    orderQuantity++;
                    Price=initialPrice*orderQuantity;
                }
                price.setText(String.valueOf(Price)+" LL.");
                quantity.setText(String.valueOf(orderQuantity));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderQuantity<2){
                    Toast.makeText(OrderActivity.this , "Error" , Toast.LENGTH_SHORT).show();
                }
                else{
                    orderQuantity--;
                    Price=orderQuantity*initialPrice;
                }
                price.setText(String.valueOf(Price)+" LL.");
                quantity.setText(String.valueOf(orderQuantity));
            }
        });


     final RequestQueue queue = Volley.newRequestQueue(this);

     order.setOnClickListener(v -> {

         prog.setVisibility(View.VISIBLE);
         order.setEnabled(false);

         String url = "https://alaajsite.000webhostapp.com/order.php";

         StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {

                 prog.setVisibility(View.INVISIBLE);
                 order.setEnabled(true);
                 Toast.makeText(OrderActivity.this , response , Toast.LENGTH_SHORT).show();

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

                 prog.setVisibility(View.INVISIBLE);
                 order.setEnabled(true);
                 Toast.makeText(OrderActivity.this , error.toString() , Toast.LENGTH_SHORT).show();
             }
         })
         {
             @Nullable
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String , String> params = new HashMap<>();
                 params.put("pid" , String.valueOf(pid));
                 params.put("uid" , String.valueOf(uid));
                 params.put("Address" ,address.getText().toString());
                 params.put("quantity" , quantity.getText().toString());
                 params.put("CheckPrice" , String.valueOf(Price));
                 return params;
             }
         };

         queue.add(request);
     });

        




    }
}