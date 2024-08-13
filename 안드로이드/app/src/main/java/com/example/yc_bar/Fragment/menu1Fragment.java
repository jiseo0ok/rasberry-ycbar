package com.example.yc_bar.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.yc_bar.R;
import com.example.yc_bar.bluetooth;


public class menu1Fragment extends Fragment {


    Context ct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu1fragment, container, false);
        ct = container.getContext();
        //처음 childfragment 지정
        getFragmentManager().beginTransaction().add(R.id.child_fragment1, new menu1()).commit();



        //하위버튼
        Button sub_menu1 = (Button) v.findViewById(R.id.setting_menu1);
        //Button sub_menu2 = (Button) v.findViewById(R.id.setting_menu2);




        //클릭 이벤트
        sub_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment1, new menu1()).commit();
                Toast.makeText(ct, "모듈을 설정합니다..", Toast.LENGTH_SHORT).show();

            }
        });
        /*sub_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getFragmentManager().beginTransaction().replace(R.id.child_fragment1, new bluetooth()).commit();
                Intent intent = new Intent(view.getContext(),bluetooth.class);
                startActivity(intent);
                Toast.makeText(ct, "블루투스를 선택합니다..", Toast.LENGTH_SHORT).show();

            }
        });*/





        return v;
    }


}