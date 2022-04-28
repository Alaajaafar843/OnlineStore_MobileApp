package com.hell.buildingsupplies;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyListAdapter extends ArrayAdapter<Product> {

    private Context context;
    private RequestQueue queue;
    private final static String URL = "https://alaajsite.000webhostapp.com/getImage2.php";
    private static String[] titles = {"Loading image" , "Loading image" , "Loading image" , "Loading image" , "Loading image"};
    String url = "https://alaajsite.000webhostapp.com/getProducts.php";
    private static ArrayList<Product> products = Data.products;
    private static ArrayList<Bitmap> images = new ArrayList<>();


    public MyListAdapter(@NonNull Context context, @NonNull RequestQueue queue) {
        super(context, R.layout.mylist, products);
        this.context = context;
        this.queue = queue;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.mylist, null,true);

        TextView tv = rowView.findViewById(R.id.tvSmall);
        ImageView iv = rowView.findViewById(R.id.ivSmall);

        tv.setText(Data.products.get(position).getProductName());

        ImageRequest request = new ImageRequest(URL + "?id=" + (Data.products.get(position).getPid()), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
                images.add(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("can't get image");
                    }
                });
        queue.add(request);

        return rowView;
    }

    public static String getTitle(int pos) {
        return Data.products.get(pos).getProductName();
    }

    public static Bitmap getImage(int pos) {
        return images.get(pos);
    }


}
