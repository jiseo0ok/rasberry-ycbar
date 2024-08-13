package com.example.yc_bar.Adaptor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yc_bar.Fragment.ChildFragment1;
import com.example.yc_bar.Fragment.select_1;
import com.example.yc_bar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class AdapterViewHolder1  extends RecyclerView.ViewHolder {
    public TextView drinkname;

    public ImageButton drinkphoto;

    Context context;


    AdapterViewHolder1(Context context, View itemView) {
        super(itemView);
        this.context = context;
        drinkphoto = itemView.findViewById(R.id.drinkphoto);
        drinkname = itemView.findViewById(R.id.drinkname);


        drinkphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadDataToDB();
                ContentValues values = new ContentValues();
                values.put("drinkname", drinkname.getText().toString());
                String url = "http://bighero.iptime.org/selectName.php?drinkname="+drinkname.getText();
                // HTTP 요청 보내기
                HttpUtil networkTask = new HttpUtil(url, values);
                networkTask.execute();




            }
        });
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
                    String drinkphoto = jsonObject.getString("drinkphoto");
                    String drinkname = jsonObject.getString("drinkname");
                    String drinkcontent = jsonObject.getString("drinkcontent");
                    String how = jsonObject.getString("how");
                    // Intent에 값 추가
                    Intent intent = new Intent(context, select_1.class);
                    intent.putExtra("drinkphoto", drinkphoto);
                    intent.putExtra("drinkname", drinkname);
                    intent.putExtra("drinkcontent", drinkcontent);

                    intent.putExtra("how", how);

                    // 액티비티 시작
                    if (context instanceof Activity) {
                        Toast.makeText(context, drinkname + "를 선택합니다..", Toast.LENGTH_SHORT).show();
                        ((Activity) context).startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
}

