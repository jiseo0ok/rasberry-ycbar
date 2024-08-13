package com.example.yc_bar.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yc_bar.List.drinkdb;
import com.example.yc_bar.R;


import java.util.ArrayList;


public class Adapter1 extends RecyclerView.Adapter<AdapterViewHolder1> {
    private ArrayList<drinkdb> items =new ArrayList<>();


    @NonNull
    @Override
    public AdapterViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dlist, parent, false);

      AdapterViewHolder1 viewholder = new AdapterViewHolder1(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder1 holder, int position) {
    drinkdb item = items.get(position);
//        String text = arrayList.get(position);
        //holder.title.setText(arrayList.getTitle());
        //holder.textView.setText(text);
        if (item != null) {
        Glide.with(holder.itemView.getContext()).load("http://bighero.iptime.org/photo/"+item.getDrinkphoto()).into(holder.drinkphoto);
        holder.drinkname.setText(item.getDrinkname());

        //holder.drinkphoto.setText(item.getDrinkphoto());
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 데이터를 입력
    public void setArrayData(drinkdb item) {
        items.add(item);
    }

}
