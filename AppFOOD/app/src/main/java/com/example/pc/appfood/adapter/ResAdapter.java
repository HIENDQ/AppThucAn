package com.example.pc.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.appfood.R;
import com.example.pc.appfood.activity.ResActivity;
import com.example.pc.appfood.model.Res;
import com.example.pc.appfood.activity.MenuActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResAdapter extends BaseAdapter {


    private Context context;
    private  int layout;
    private List<Res> reslist;

    public ResAdapter(Context context, int layout, List<Res> reslist) {
        this.context = context;
        this.layout = layout;
        this.reslist = reslist;
    }

    @Override
    public int getCount() {
        return reslist.size();
    }

    @Override
    public Object getItem(int i) {
        return reslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private   class ViewHolder{
        TextView txtnhahang;
        TextView txtdichi;
        TextView txttghoatdong;
        ImageView Imnhahang;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null){
            holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtnhahang=(TextView)view.findViewById(R.id.tennhahang);
            holder.txtdichi=(TextView)view.findViewById(R.id.diachi);
            holder.txttghoatdong=(TextView)view.findViewById(R.id.tgian);
            holder.Imnhahang=(ImageView)view.findViewById(R.id.imageView);


            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }
        final Res res= (Res) getItem(i);
        holder.txtnhahang.setText(res.getResname());
        holder.txttghoatdong.setText("Mở cửa "+res.getTghoatdong());
        String diachi =res.getDiachi();
        SpannableString noidungspanned = new SpannableString(diachi);

        noidungspanned.setSpan(new UnderlineSpan(),0 ,diachi.length() ,0 );
        holder.txtdichi.setText(noidungspanned);
        Picasso.get().load(res.getHinhanh()).into(holder.Imnhahang);

        holder.txtdichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,res.getDiachi(), Toast.LENGTH_SHORT).show();

            }
        });


        return view;




    }



}
