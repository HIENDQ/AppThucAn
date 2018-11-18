package com.example.pc.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.appfood.model.Food;
import com.example.pc.appfood.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends BaseAdapter {
    ArrayList<Food> arrayListfood;
    Context context;

    public MenuAdapter(ArrayList<Food> arrayListfood, Context context) {
        this.arrayListfood = arrayListfood;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListfood.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListfood.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class  ViewHolder{
        TextView txttenmon, txtgia;
        Button btngiohang;
        ImageView anhfood;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final  ViewHolder holder;
        if (view== null){
            holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_menu,null);
            holder.txttenmon=view.findViewById(R.id.tenmon);
            holder.txtgia= view.findViewById(R.id.giafood);
            holder.btngiohang=view.findViewById(R.id.btthemgiohang);
            holder.anhfood=view.findViewById(R.id.anhfood);



            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        Food food = (Food) getItem(i);
        Picasso.get().load(food.getHinhaanh()).into(holder.anhfood);
        holder.txttenmon.setText(food.getName());
        String pattern = "###,###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        holder.txtgia.setText("Giá: " + decimalFormat.format(food.getGia())+".Đ");
        Picasso.get().load(food.getHinhaanh()).into(holder.anhfood);



        return view;
    }
}
