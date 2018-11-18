package com.example.pc.appfood.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.appfood.R;
import com.example.pc.appfood.model.Food;
import com.example.pc.appfood.model.Res;
import com.example.pc.appfood.adapter.ResAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResActivity extends AppCompatActivity {
    String url ="http://192.168.1.5/ginyong/getdatennhahang.php";
    String urltd="";
    ListView lvnhang;
    ArrayList<Res> arrayRes;
    ResAdapter adapter;
    ImageView imtrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        imtrove= (ImageView)findViewById(R.id.trove);
        lvnhang =(ListView)findViewById(R.id.listviewnhahang);

        final Res name;
        arrayRes = new ArrayList<>();

        adapter = new ResAdapter(ResActivity.this, R.layout.dong_res,arrayRes);
        lvnhang.setAdapter(adapter);


        getdata(url);

        batsukien();

        imtrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResActivity.this,MainActivity.class));
            }
        });
    }


    private void batsukien() {
        lvnhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent = new Intent(ResActivity.this,MenuActivity.class);
              intent.putExtra("nhahang",arrayRes.get(i));
              startActivity(intent);


            }
        });
    }

    private  void  getdata(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i =0 ; i<response.length(); i++){
                            try {
                                JSONObject object =response.getJSONObject(i);
                                arrayRes.add(new Res(
                                        object.getInt("id"),
                                        object.getString("diachi"),
                                        object.getString("tg"),
                                        object.getString("hinhanh"),
                                        object.getString("ten")
                                )
                                );
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();

                        //  Toast.makeText(DSnha_hang.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ResActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }
}
