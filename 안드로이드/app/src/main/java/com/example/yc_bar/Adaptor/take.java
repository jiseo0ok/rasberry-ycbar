package com.example.yc_bar.Adaptor;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class take extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        String result = "";
        try {
            URL url = new URL("http://bighero.iptime.org/module.php"); // PHP 파일의 URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 서버 응답 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // 결과를 이용하여 원하는 동작 수행
        // 여기서 result는 PHP 서버에서 받은 JSON 형식의 데이터입니다.
        // JSON 데이터를 파싱하여 UI에 표시하거나 다른 작업에 사용할 수 있습니다.
        processJSONData(result);
    }

    private void processJSONData(String json) {
        try {
            // JSON 데이터 파싱
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            // JSON 데이터에서 필요한 정보 추출
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String drinkName = obj.getString("drinkname");
                String drinkPhoto = obj.getString("drinkphoto");
                String ml = obj.getString("ml");

                // 여기서 가져온 데이터를 원하는 방식으로 활용할 수 있음
                // 예를 들어, TextView에 표시하거나 데이터 리스트에 저장하여 사용할 수 있음
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
