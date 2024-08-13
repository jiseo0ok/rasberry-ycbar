package com.example.yc_bar.Adaptor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yc_bar.Fragment.select_1;
import com.example.yc_bar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


class AdapterViewHolder1pop extends RecyclerView.ViewHolder {
    public TextView drinkname;
    String drinkid1;

    public ImageButton drinkphoto;

    String drinkphoto1;
    String drinkname1;
    String drinkcontent1;
    String how1;
    String url1;

    Context context;


    AdapterViewHolder1pop(Context context, View itemView) {
        super(itemView);
        this.context = context;
        drinkphoto = itemView.findViewById(R.id.drinkphoto);
        drinkname = itemView.findViewById(R.id.drinkname);


      /*  drinkphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadDataToDB();
                ContentValues values = new ContentValues();
                values.put("drinkname", drinkname.getText().toString());
                String url = "http://bighero.iptime.org/selectName1.php?drinkname="+drinkname.getText();
                // HTTP 요청 보내기
                HttpUtil networkTask = new HttpUtil(url, values);
                networkTask.execute();




            }
        });*/
    }

    public class HttpUtil extends AsyncTask<Void, Void, String> {

        String url;
        ContentValues values;

        HttpUtil(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progress bar를 보여주는 등등의 행위
        }

        @Override
        protected String doInBackground(Void... params) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            String result = requestHttpURLConnection.postRequest(url, values);
            return result; // 아래 onPostExecute()의 파라미터로 전달됩니다.
        }

        @Override
        protected void onPostExecute(String result) {



            try {
                JSONObject jsonResponse = new JSONObject(result);
                JSONArray jsonArray = jsonResponse.getJSONArray("response");

                if (jsonArray.length() > 0) {
                    // 응답 처리
                    JSONObject jsonObject = jsonArray.getJSONObject(0); // 첫 번째 응답 데이터 가져오기
                    drinkid1 =jsonObject.getString("drinkid");
                     drinkphoto1 = jsonObject.getString("drinkphoto");
                     drinkname1 = jsonObject.getString("drinkname");
                     drinkcontent1 = jsonObject.getString("drinkcontent");
                     how1 = jsonObject.getString("how");
                    // Intent에 값 추가

                    // 액티비티 시작
                    if (context instanceof Activity) {
                        Toast.makeText(context, drinkname1 + "를 선택합니다..", Toast.LENGTH_SHORT).show();
                        url1 = "http://bighero.iptime.org/tnwjd.php?drinkid=1";
                        uploadDataToDB();
                    } else {
                        // Context가 Activity가 아닌 경우에 대한 처리 (Optional)
                        // Context가 Activity 인 경우에만 startActivity 호출할 수 있으므로 다른 처리 방법을 고려해야 할 수 있습니다.
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadDataToDB() {





        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("drinkname", drinkname1);
                map.put("drinkphoto", drinkphoto1);
                map.put("drinkcontent", drinkcontent1);
                map.put("ml", "100");

                return map;
            }
        };


    }
}

