package com.example.yc_bar.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.yc_bar.R;



public class FragmentOne extends Fragment {


    Context ct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_one, container, false);
        ct = container.getContext();
        //처음 childfragment 지정
        getFragmentManager().beginTransaction().add(R.id.child_fragment, new ChildFragment1()).commit();



        //하위버튼
        Button sub_menu1 = (Button) v.findViewById(R.id.sub_menu1);
        Button sub_menu2 = (Button) v.findViewById(R.id.sub_menu2);
        Button sub_menu3 = (Button) v.findViewById(R.id.sub_menu3);



        //클릭 이벤트
        sub_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment1()).commit();
                Toast.makeText(ct, "칵테일을 선택합니다.", Toast.LENGTH_SHORT).show();

            }
        });
        sub_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment2()).commit();
                Toast.makeText(ct, "논알코올을 선택합니다.", Toast.LENGTH_SHORT).show();

            }
        });
        sub_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment3()).commit();
                Toast.makeText(ct, "DIY를 선택합니다..", Toast.LENGTH_SHORT).show();

            }
        });




        return v;
    }


}