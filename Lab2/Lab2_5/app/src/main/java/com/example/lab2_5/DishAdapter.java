package com.example.lab2_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Dish> dishList=null;

    DishAdapter(Context context, int layout, ArrayList<Dish>dishList){
        this.context=context;
        this.layout=layout;
        this.dishList=dishList;
    }
    @Override
    public int getCount(){
        return dishList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_dish,null,false);
        }
        Dish dish=dishList.get(position);
        ImageView imgDish=(ImageView) convertView.findViewById(R.id.img_dish);
        TextView tvDish= (TextView) convertView.findViewById(R.id.tv_dish);
        tvDish.setSelected(true);
        ImageView imgStart=(ImageView) convertView.findViewById(R.id.img_Star);
        if(dish.isPromotion()){
            imgStart.setVisibility(View.VISIBLE);
        }
        else{
            imgStart.setVisibility(View.GONE);
        }
        imgDish.setImageResource(dish.getThumbnail());
        tvDish.setText(dish.getMonAn());
        return convertView;
    }
}
