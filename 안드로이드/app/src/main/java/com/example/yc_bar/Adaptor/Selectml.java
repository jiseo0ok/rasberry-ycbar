package com.example.yc_bar.Adaptor;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yc_bar.List.drinkdb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Selectml extends AsyncTask<String, Void, String> {

    public String[] drinkid= new String[6];
    public String[] ml= new String[6];

    public String getDrinkId(int i) {
        if (drinkid != null && i >= 0 && i < drinkid.length) {
            return drinkid[i];
        } else {
            return null; // 예외 처리: 유효하지 않은 인덱스나 빈 배열일 경우 null 반환
        }
    }
    public String getMl(int i) {
        if (ml != null && i >= 0 && i < ml.length) {
            return ml[i];
        } else {
            return null; // 예외 처리: 유효하지 않은 인덱스나 빈 배열일 경우 null 반환
        }
    }
    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;

        try {

            String url1 = "http://bighero.iptime.org/selectml.php";
            URL url = new URL(url1);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            result = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {


        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray(("response"));


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                drinkid[i] = object.getString("drinkid");
                ml[i] = object.getString("ml");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
