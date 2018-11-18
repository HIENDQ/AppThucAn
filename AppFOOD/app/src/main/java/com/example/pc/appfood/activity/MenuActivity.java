package com.example.pc.appfood.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.appfood.model.Food;
import com.example.pc.appfood.adapter.MenuAdapter;
import com.example.pc.appfood.R;
import com.example.pc.appfood.model.Res;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    TextView txtname;
    int idnh;
    ImageView imageView;
    ListView lvfood;
    ArrayList<Food> arrayFood;
    MenuAdapter adapterfood;
    String urltd ="http://192.168.1.5/ginyong/getthucdon.php?page=1";

    int page= 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        anhxa();


//lấy dữ liệu từ res
        Intent intent = getIntent();
        Res res = (Res) intent.getSerializableExtra("nhahang");
        idnh= res.getId();
        txtname.setText(res.getResname());


        getdat( urltd);

     

lvfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MenuActivity.this, "hello", Toast.LENGTH_SHORT).show();
    }
});

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,ResActivity.class));
            }
        });




    }

    private void getdat( String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
       StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
               new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               int idmon;
               String Ten;
               int gia;
               String anh;
               int idnhahang;
               if (response != null) {
                   try {
                       JSONArray jsonArray = new JSONArray(response);
                       for (int i = 0; i < jsonArray.length(); i++) {
                           JSONObject jsonObject = jsonArray.getJSONObject(i);
                           idmon = jsonObject.getInt("ID");
                           Ten = jsonObject.getString("Ten");
                           gia = jsonObject.getInt("Gia");
                           anh = jsonObject.getString("Hinhanh");
                           idnhahang = jsonObject.getInt("IdNH");
                           arrayFood.add(new Food(Ten, gia, idmon, idnhahang, anh));
                           adapterfood.notifyDataSetChanged();
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           }
           },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }
               ){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               HashMap<String,String> param= new HashMap<>();
               param.put("idnha",String.valueOf(idnh));
               return param;
           }
       };
       requestQueue.add(stringRequest);
    }






    private void anhxa(){
        imageView=(ImageView)findViewById(R.id.trove) ;
        txtname = (TextView)findViewById(R.id.tennhahang);
        lvfood =(ListView)findViewById(R.id.listviewthucdon);
        arrayFood= new ArrayList<>();
        adapterfood = new MenuAdapter(arrayFood,getApplicationContext());
        lvfood.setAdapter(adapterfood);
    }



}
