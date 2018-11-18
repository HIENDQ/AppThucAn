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

public class LoginActivity extends AppCompatActivity {
    TextView txtchuyendangky;
    EditText edtemail,edtpass;
    Button bntdangnhap;
    ImageView imback;

    public static final String Email = "email";
    public static final String Pass = "pass";



    String urllg="http://192.168.1.6/ginyong/login.php";
    //String urllg="http://192.168.43.177/ginyong/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        bntdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString();
                String pass =edtemail.getText().toString();
                if (TextUtils.isEmpty(pass)||TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    dangnhap(urllg);
                }
            }
        });

        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
        txtchuyendangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,LogupActivity.class));
            }
        });
    }
    private  void dangnhap(final String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("thatbai")){
                            Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            byExtras();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, "Xãy ra lỗi"+error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n"+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("emailnd",edtemail.getText().toString().trim());
                params.put("passnd",edtpass.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void byExtras(){
        Intent intent = new Intent(LoginActivity.this, AccountAcivity.class);
        intent.putExtra(Email, edtemail.getText().toString());
        intent.putExtra(Pass, edtpass.getText().toString());

        startActivity(intent);

    }

    private  void anhxa()
    {
        imback=(ImageView)findViewById(R.id.toreturn);
        txtchuyendangky = (TextView) findViewById(R.id.chuyendangky);
        edtemail= (EditText)findViewById(R.id.emaildangnhap);
        edtpass= (EditText)findViewById(R.id.passdangnhap);
        bntdangnhap= (Button)findViewById(R.id.button_dangnhap);
    }
}
