package com.hell.buildingsupplies;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MyImage {

    private Context context;
    private ImageView iv;
    private ProgressBar prog;
    private final static String UPLAODURL = "https://alaajsite.000webhostapp.com/upload.php";
    private final static String DOWNLAODFOLDER = "https://alaajsite.000webhostapp.com/add.php";


    public MyImage(Context context, ImageView iv, ProgressBar prog) {
        this.context = context;
        this.iv = iv;
        this.prog = prog;
    }

    public ImageView getIv() {
        return iv;
    }

    public ImageRequest downloadImage(String id) {
        String url = DOWNLAODFOLDER + id + ".jpg";
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
                prog.setVisibility(View.INVISIBLE);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                prog.setVisibility(View.INVISIBLE);
                iv.setImageBitmap(null);
                Toast.makeText(context, "can't get image", Toast.LENGTH_SHORT).show();
            }
        });

        return request;
    }

    public StringRequest uplaodImage(String id) {
        prog.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(1, UPLAODURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                prog.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                prog.setVisibility(View.INVISIBLE);
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
                Bitmap bmap = drawable.getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                byte[] bb = bos.toByteArray();
                String image = Base64.encodeToString(bb, Base64.DEFAULT);

                Map<String, String> params = new HashMap<>();
                params.put("name", id);
                params.put("file", image);
                return params;
            }
        };

        return request;


    }
}
