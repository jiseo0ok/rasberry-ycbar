package com.example.yc_bar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yc_bar.Fragment.FragmentOne;
import com.example.yc_bar.Fragment.menu2;
import com.example.yc_bar.Fragment.menu1Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager manager;
    FragmentTransaction ft;
    FragmentOne fragmentOne;
    menu2 menu2;
    menu1Fragment menu1Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        ImageButton menu_1 = findViewById(R.id.menu_1);
        ImageButton menu_2 = findViewById(R.id.menu_2);
        Button home = findViewById(R.id.home);
        fragmentOne = new FragmentOne();

        menu2 = new menu2();
        menu1Fragment = new menu1Fragment();
        ft = manager.beginTransaction();
        ft.add(R.id.fragment_container, fragmentOne);
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ft.addToBackStack(null);

        ft.commit();
        menu_1.setOnClickListener(this);
        menu_2.setOnClickListener(this);
        home.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ft.addToBackStack(null);
        int id = v.getId();
        switch (id) {
            case R.id.menu_1:

                ft.replace(R.id.fragment_container, menu1Fragment);

                ft.commit();
                break;
            case R.id.menu_2:

                ft.replace(R.id.fragment_container, menu2);
                ft.commit();
                break;
            case R.id.home:

                ft.replace(R.id.fragment_container, fragmentOne);
                ft.commit();
                break;
    }
}}
