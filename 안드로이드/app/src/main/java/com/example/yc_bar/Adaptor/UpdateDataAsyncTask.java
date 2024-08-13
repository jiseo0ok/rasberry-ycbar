package com.example.yc_bar.Adaptor;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UpdateDataAsyncTask extends AsyncTask<String, Void, String> {

    private static final String TAG = UpdateDataAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(String... params) {
        String updateUrl = "http://bighero.iptime.org/tnwjd.php"; // 여기에 PHP 스크립트의 URL을 입력하세요.

        String drinkId = params[0];
        String drinkName = params[1];
        String drinkPhoto = params[2];
        String ml = params[3];

        try {
            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String postData = URLEncoder.encode("drinkid", "UTF-8") + "=" + URLEncoder.encode(drinkId, "UTF-8") + "&" +
                    URLEncoder.encode("drinkname", "UTF-8") + "=" + URLEncoder.encode(drinkName, "UTF-8") + "&" +
                    URLEncoder.encode("drinkphoto", "UTF-8") + "=" + URLEncoder.encode(drinkPhoto, "UTF-8") + "&" +
                    URLEncoder.encode("ml", "UTF-8") + "=" + URLEncoder.encode(ml, "UTF-8");

            writer.write(postData);
            writer.flush();
            writer.close();
            outputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // 여기서 서버로부터의 응답을 처리할 수 있습니다.
        // 예를 들어, 업데이트 성공 여부에 따른 처리 등을 할 수 있습니다.
    }
}
