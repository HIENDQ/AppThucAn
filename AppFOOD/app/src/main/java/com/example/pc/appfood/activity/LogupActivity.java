package com.example.pc.appfood.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.appfood.R;

import java.util.HashMap;
import java.util.Map;

public class LogupActivity extends AppCompatActivity {
    EditText edtname, edtemail, edtpass, edtsdt, edtdiachi;
    TextView txtdangnhap;
    Button btndangky;
    ImageView imback;

    //String urlInsert ="http://192.168.1.6/ginyong/insert.php";
     String urlInsert ="http://192.168.43.177/ginyong/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        anhxa();

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  name = edtname.getText().toString().trim();
                String  email = edtemail.getText().toString().trim();
                String  pass = edtpass.getText().toString().trim();
                String  sdt = edtsdt.getText().toString().trim();
                String  dia_chi = edtdiachi.getText().toString().trim();

                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(sdt)||TextUtils.isEmpty(dia_chi)){
                    Toast.makeText(LogupActivity.this, "Vui lòng nhập đày đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
                if (pass.length()<6){
                    Toast.makeText(LogupActivity.this, "Vui lòng nhập mật khẩu nhiều hơn 6 ký tự.", Toast.LENGTH_SHORT).show();
                }
                else {
                    addnd(urlInsert);
                }
            }
        });

        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogupActivity.this,MainActivity.class));
            }
        });
        txtdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogupActivity.this,LoginActivity.class));
            }
        });

    }

    private  void addnd(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(LogupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogupActivity.this,LoginActivity.class));
                        }else {
                            Toast.makeText(LogupActivity.this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogupActivity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nameND",edtname.getText().toString().trim());
                params.put("emailND", edtemail.getText().toString().trim());
                params.put("passND",edtpass.getText().toString().trim());
                params.put("sdtND",edtsdt.getText().toString().trim());
                params.put("diachiND",edtdiachi.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private   void anhxa(){


        imback=(ImageView)findViewById(R.id.tvhd);
        edtname =(EditText) findViewById(R.id.name);
        edtemail=(EditText) findViewById(R.id.email);
        edtpass =(EditText) findViewById(R.id.pass);
        edtsdt= (EditText) findViewById(R.id.sdt);
        edtdiachi =(EditText) findViewById(R.id.diachi);
        txtdangnhap= (TextView) findViewById(R.id.dangnhap);
        btndangky = (Button)findViewById(R.id.dangky);
    }
}
