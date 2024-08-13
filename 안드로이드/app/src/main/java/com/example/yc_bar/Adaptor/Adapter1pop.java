package com.example.yc_bar.Adaptor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.yc_bar.Fragment.menu1;
import com.example.yc_bar.List.drinkdb;
import com.example.yc_bar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Adapter1pop extends RecyclerView.Adapter<AdapterViewHolder1pop> {
    private ArrayList<drinkdb> items =new ArrayList<>();
    Button change1;
    String a;

    public Adapter1pop(String module_number) {
        a=module_number;
    }

    @NonNull
    @Override
    public AdapterViewHolder1pop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dlist1, parent, false);
        change1=view.findViewById(R.id.change1);

      AdapterViewHolder1pop viewholder = new AdapterViewHolder1pop(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder1pop holder, int position) {
    drinkdb item = items.get(position);
//        String text = arrayList.get(position);
        //holder.title.setText(arrayList.getTitle());
        //holder.textView.setText(text);
        if (item != null) {
        Glide.with(holder.itemView.getContext()).load("http://bighero.iptime.org/photo/"+item.getDrinkphoto()).into(holder.drinkphoto);
        holder.drinkname.setText(item.getDrinkname());

        holder.drinkphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(holder.context, a, Toast.LENGTH_SHORT).show();
                UpdateDataAsyncTask updateTask = new UpdateDataAsyncTask();
                String drinkId = a; // 수정할 음료의 ID
                String updatedDrinkName = item.getDrinkname();
                String updatedDrinkPhoto = item.getDrinkphoto();
                String updatedMl = "300";
                updateTask.execute(drinkId, updatedDrinkName, updatedDrinkPhoto, updatedMl);


            }
        });


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

    private void uploadDataToDB() {





    }

}
