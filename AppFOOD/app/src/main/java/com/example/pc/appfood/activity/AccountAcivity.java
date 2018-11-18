package com.example.pc.appfood.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class AccountAcivity extends AppCompatActivity {
    public static final String EmailTK = "email";
    public static final String PassTK = "pass";
    TextView txtemail, txtname, txtsdt, txtdiachi;
    Button bntdangxuat;
    ImageView bt_hoat_dong;
    //String urlgetdata="http://192.168.1.6/ginyong/getdata.php";
    String urlgetdata="http://192.168.43.177/ginyong/getdata.php";
    String pass=null ,email= null, sdt="",diachi="", name ="";
    int so=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_acivity);

        anhxa();

        nhandatahoatdong();
        if (email==null&& pass== null){
            startActivity(new Intent(AccountAcivity.this,LoginActivity.class));
        }

        nhandatalogin();




        getdata(urlgetdata);




        bntdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountAcivity.this,LoginActivity.class));
            }
        });

        bt_hoat_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byExtras();

            }
        });
    }

    private  void getdata(final String url ){

        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        xulychuoi(response);
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
                Map<String, String> params = new HashMap<>();
                params.put("emailnd", email);
                params.put("passnd", pass);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }


    private void  xulychuoi(String chuoi){
        int dem=0;
        String st1="",st2="",st3="";
        for (int i =0 ; i<chuoi.length(); i++ ){

            if (dem==7){
                st1+= chuoi.charAt(i);
            }
            if (chuoi.charAt(i)=='"'){
                dem++;
            }
            if(dem==19){
                st2+=chuoi.charAt(i);
            }
            if(dem==23){
                st3+=chuoi.charAt(i);
            }
        }
        for (int i =0; i<st1.length()-1; i++){
            name+=st1.charAt(i);
        }
        for (int i =1 ; i <st2.length(); i++){
            sdt+=st2.charAt(i);
        }
        for(int i= 1; i<st3.length();i++){
            diachi+=st3.charAt(i);
        }
        setdata();
    }

    private  void setdata(){
        txtname.setText("Tên:      "+name);
        txtemail.setText("Email:   "+email);
        txtsdt.setText("SĐT:     0"+sdt);
        txtdiachi.setText("Địa Chỉ: "+diachi);
    }
    private  void  nhandatalogin(){
        Intent intent = getIntent();
        {
            if (intent!= null){
                email = intent.getStringExtra(LoginActivity.Email);
                pass= intent.getStringExtra(LoginActivity.Pass);

            }
        }
    }
    private  void nhandatahoatdong(){
        Intent intent = getIntent();
        {
            if (intent != null) {
                email = intent.getStringExtra(MainActivity.EmailHD);
                pass = intent.getStringExtra(MainActivity.PassHD);
            }
        }
    }
    private  void anhxa(){
        bt_hoat_dong = (ImageView) findViewById(R.id.bt_hoat_dong);
        txtemail= (TextView)findViewById(R.id.taikhoan_email);
        bntdangxuat=(Button)findViewById(R.id.taikhoan_dangxuat);
        txtname= (TextView)findViewById(R.id.taikhoan_name);
        txtdiachi=(TextView)findViewById(R.id.taikhoan_diachi);
        txtsdt= (TextView)findViewById(R.id.taikhoan_sdt);

    }

    public void byExtras(){
        Intent intent = new Intent(AccountAcivity.this, MainActivity.class);
        intent.putExtra(EmailTK, email);
        intent.putExtra(PassTK, pass);
        startActivity(intent);

    }
}
