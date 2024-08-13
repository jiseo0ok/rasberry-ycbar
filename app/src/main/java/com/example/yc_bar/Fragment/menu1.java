package com.example.yc_bar.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.yc_bar.Adaptor.Adapter1;
import com.example.yc_bar.Adaptor.Adapter1pop;
import com.example.yc_bar.List.drinkdb;
import com.example.yc_bar.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class menu1 extends Fragment {
    RecyclerView recyclerView;
    Adapter1pop adapter1;
    Button change1;
    Button change2;
    Button change3;
    Button change4;
    Button change5;
    Button change6;

    Context ct;
    ImageView module_img1;
    ImageView module_img2;
    ImageView module_img3;
    ImageView module_img4;
    ImageView module_img5;
    ImageView module_img6;
    TextView module_name1;
    TextView module_name2;
    TextView module_name3;
    TextView module_name4;
    TextView module_name5;
    TextView module_name6;

    private SwipeRefreshLayout mysrl;
    Context context = getContext();

    String name[] = new String[6];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);
        ct = container.getContext();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mysrl = v.findViewById(R.id.content_srl);
        new qnffjdhrl().execute();
        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new qnffjdhrl().execute();

                mysrl.setRefreshing(false);

                // 종료

            }
        });

        module_img1 = v.findViewById(R.id.module_img1);
        module_img2 = v.findViewById(R.id.module_img2);
        module_img3 = v.findViewById(R.id.module_img3);
        module_img4 = v.findViewById(R.id.module_img4);
        module_img5 = v.findViewById(R.id.module_img5);
        module_img6 = v.findViewById(R.id.module_img6);
        module_name1 = v.findViewById(R.id.module_name1);
        module_name2 = v.findViewById(R.id.module_name2);
        module_name3 = v.findViewById(R.id.module_name3);
        module_name4 = v.findViewById(R.id.module_name4);
        module_name5 = v.findViewById(R.id.module_name5);
        module_name6 = v.findViewById(R.id.module_name6);
        change1 = (Button) v.findViewById(R.id.change1);


        change1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "1";
                new menu1_popup(a).execute();

            }
        });
        change2 = (Button) v.findViewById(R.id.change2);
        change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "2";
                new menu1_popup(a).execute();

            }
        });

        change3 = (Button) v.findViewById(R.id.change3);
        change3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "3";
                new menu1_popup(a).execute();

            }
        });

        change4 = (Button) v.findViewById(R.id.change4);
        change4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "4";
                new menu1_popup(a).execute();

            }
        });

        change5 = (Button) v.findViewById(R.id.change5);
        change5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "5";
                new menu1_popup(a).execute();

            }
        });

        change6 = (Button) v.findViewById(R.id.change6);
        change6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "6";
                new menu1_popup(a).execute();

            }
        });

        //button1=ct.
        return v;
    }

    class qnffjdhrl extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://bighero.iptime.org/module.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(String result) {
            System.out.println(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));

                String drinkname;
                String drinkphoto;
                String ml;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    drinkname = object.getString("drinkname");
                    drinkphoto = object.getString("drinkphoto");
                    ml = object.getString("ml");


                    if (i == 0) {
                        module_name1.setText(drinkname + "\n" + ml + "ml");
                        Glide.with(ct).load("http://bighero.iptime.org/photo/" + drinkphoto).into(module_img1);
                    } else if (i == 1) {
                        module_name2.setText(drinkname + "\n" + ml + "ml");
                        Glide.with(ct).load("http://bighero.iptime.org/photo/" + drinkphoto).into(module_img2);
                    } else if (i == 2) {
                        module_name3.setText(drinkname + "\n" + ml + "ml");
                        Glide.with(ct).load("http://bighero.iptime.org/photo/" + drinkphoto).into(module_img3);
                    } else if (i == 3) {
                        module_name4.setText(drinkname + "\n" + ml + "ml");
                        Glide.with(ct).load("http://bighero.iptime.org/photo/" + drinkphoto).into(module_img4);
                    } else if (i == 4) {
                        module_name5.setText(drinkname + "\n" + ml + "ml");
                        Glide.with(ct).load("http://bighero.iptime.org/photo/" + drinkphoto).into(module_img5);
                    } else if (i == 5) {
                        module_name6.setText(drinkname + "\n" + ml + "ml");
                        Glide.with(ct).load("http://bighero.iptime.org/photo/" + drinkphoto).into(module_img6);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    class menu1_popup extends AsyncTask<Void, Void, String> {
        String target;
        String module_number;

        public menu1_popup(String a) {
            module_number = a;
        }

        @Override
        protected void onPreExecute() {
            target = "http://bighero.iptime.org/module1.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            System.out.println(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));

                String drinkname;
                String drinkphoto;
                // String drinkcontent;

                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.popup_layout, null);
                builder.setView(dialogView);
                ImageButton close = dialogView.findViewById(R.id.close);

                RecyclerView recyclerView = dialogView.findViewById(R.id.recycler_view_popup);
                // recyclerView에 데이터를 로드하고 어댑터 설정하는 부분을 여기에 추가하세요

                // RecyclerView에 데이터를 설정하고 어댑터를 연결하는 부분입니다.

                // RecyclerView를 위한 데이터 리스트 생성 (이 부분은 실제 데이터를 사용해야 합니다)


                // 리사이클러뷰 어댑터 생성 및 설정
                adapter1 = new Adapter1pop(module_number); // RecyclerViewAdapter는 여러분이 작성한 어댑터 클래스입니다.
                recyclerView.setLayoutManager(new LinearLayoutManager(ct, RecyclerView.HORIZONTAL, false)); // 좌우 스크롤
                //recyclerView.setLayoutManager(new GridLayoutManager(ct,2));
                // 팝업 보이기
                AlertDialog dialog = builder.create();
                dialog.show();
                ImageButton plus = dialogView.findViewById(R.id.plus);
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //      ((Activity) view.getContext()).finish();
                        dialog.dismiss();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //      ((Activity) view.getContext()).finish();
                        dialog.dismiss();
                    }
                });
                mysrl = dialogView.findViewById(R.id.content_srl);
                mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        // 종료
                        new qnffjdhrl().execute();

                        mysrl.setRefreshing(false);
                    }
                });
                //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    drinkname = object.getString("drinkname");
                    drinkphoto = object.getString("drinkphoto");
                    // drinkcontent = object.getString("drinkcontent");


//                item=();

                    adapter1.setArrayData(new drinkdb(drinkname, drinkphoto));


                }

                recyclerView.setAdapter(adapter1);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }


    }
}