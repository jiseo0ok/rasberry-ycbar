package com.example.yc_bar.Fragment;

import static android.content.ContentValues.TAG;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yc_bar.Adaptor.Adapter1;
import com.example.yc_bar.Adaptor.Selectml;
import com.example.yc_bar.Adaptor.UpdateDataAsyncTask;

import com.example.yc_bar.Adaptor.Updateml;
import com.example.yc_bar.List.drinkdb;
import com.example.yc_bar.MainActivity;
import com.example.yc_bar.R;
import com.example.yc_bar.bluetooth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class select_1 extends AppCompatActivity {

    Context ct;
    private ProgressBar progressBar;
    int value = 1;
    int maxValue = 10;
    int digit1[] = new int[6];
    private BackgroundTask task;
    private Selectml selectml;
    private Boolean threadStatus;

    int a;


    String drinkid[] = new String[6];
    String updateId[] = new String[6];
    String updateMl[] = new String[6];

    String ml[] = new String[6];
    private static final String TAG = "BluetoothClient";


    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        //타겟 호출 직전 실행
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(value);
            threadStatus = true;
            Log.d(TAG, "onPreExecute: ");
        }

        //Target Run
        @Override
        protected Integer doInBackground(Integer... integers) { //스레드 실행 시 인수 받음
            Log.d(TAG, "doInBackground: ");
            // publishProgress(10); //onProgressUpdate를 호출
            while (threadStatus) {

                value = value + 1;
                TextView val = findViewById(R.id.value);
                val.setText("예상시간 : " + value + "초 / " + maxValue + "초");
                progressBar.setMax(maxValue);
                TextView ehdwkr = findViewById(R.id.ehdwkr);
                publishProgress(value);
                if (value <= digit1[0]) {
                    ehdwkr.setText("1번 모듈 사용 중 입니다.");
                } else if (value <= digit1[1]) {
                    ehdwkr.setText("2번 모듈 사용 중 입니다.");
                } else if (value <= digit1[2]) {
                    ehdwkr.setText("3번 모듈 사용 중 입니다.");
                } else if (value <= digit1[3]) {
                    ehdwkr.setText("4번 모듈 사용 중 입니다.");
                } else if (value <= digit1[4]) {
                    ehdwkr.setText("5번 모듈 사용 중 입니다.");
                } else if (value <= digit1[5]) {
                    ehdwkr.setText("6번 모듈 사용 중 입니다.");
                } else if (value == maxValue) {
                    ehdwkr.setText("제조 완료..");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return -1;
                }

                if (value >= maxValue) {
                    return 1;
                }
            }
            //정상적으로 수행됐으면 1, 아니면 -1 리턴 식으로 사용
            return -1;
        }

        //UI 스레드 그림을 그리는 메서드
        @Override
        protected void onProgressUpdate(Integer... values) { //publish 인수 받음
            Log.d(TAG, "onProgressUpdate: ");
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        //타겟 호출 이후 실행
        @Override
        protected void onPostExecute(Integer integer) { //doInBackground 리턴 값 받음
            Log.d(TAG, "onPostExecute: ");
            super.onPostExecute(integer);
            if (integer == 1) {
                Toast.makeText(select_1.this, "제조 완료", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void fetchDrinkData() {
        // 네트워크 요청 수행
        try {
            URL url = new URL("http://bighero.iptime.org/selectml.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp).append("\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String result = stringBuilder.toString().trim();

            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                drinkid[i] = object.getString("drinkid");
                ml[i] = object.getString("ml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateUI() {

        // 다른 UI 업데이트 또는 작업 수행
        task = new BackgroundTask();
        task.execute();


        //Glide.with(this).load(menu_2_photo).into(photo1);

        Intent intent = getIntent(); // getIntent()를 여기서 호출

        if (intent != null) {
            String drinkphotoURL = intent.getStringExtra("drinkphoto");
            String drinkname = intent.getStringExtra("drinkname");
            String drinkcontent = intent.getStringExtra("drinkcontent"); // 이 부분을 추가합니다.
            String how = intent.getStringExtra("how");
            ((bluetooth) bluetooth.mContext).sendMessage(how);

//        bluetooth.sendMessage("dd");
            char[] digits = how.toCharArray();
            // 각 자리 숫자 출력

            //시간초세팅
            for (int i = 0; i < digits.length; i++) {
                int digit = Character.getNumericValue(digits[i]);

                // 각 배열 위치에 맞게 곱하고 더하기
                if (i == 0) {//1번모듈
                    a += 15 +(digit * 14);
                    digit1[i] = a;

                } else if (i == 1) {//2번모듈
                    a += 10 +(digit * 14);
                    digit1[i] = a;
                } else if (i == 2) {//3번모듈
                    a += 10 +(digit * 14);
                    digit1[i] = a;

                } else if (i == 3) {//4모듈

                    a += 10 +(digit * 14);
                    digit1[i] = a;

                } else if (i == 4) {//5번모듈
                    a += 10 +(digit * 14);
                    digit1[i] = a;

                } else if (i == 5) {//6번모듈
                    a += 10 +(digit * 14);
                    digit1[i] = a;

                maxValue = a+8;
            }}
            // 이미지 로딩 코드는 위에서 보여준 것과 동일하게 사용합니다.
            ImageView photo = findViewById(R.id.photo);

            Glide.with(this).load("http://bighero.iptime.org/photo/" + drinkphotoURL).into(photo);

            // drinkname을 TextView에 설정
            TextView drinknameTextView = findViewById(R.id.drinkname);
            drinknameTextView.setText(drinkname);

            // drinkcontent를 TextView에 설정
            TextView drinkcontentTextView = findViewById(R.id.drinkcontent);
            drinkcontentTextView.setText(drinkcontent);
            //시간초세팅
            for (int i = 0; i < 6; i++) {
                int digit = Character.getNumericValue(digits[i]);

                updateMl[i] = String.valueOf(Integer.parseInt(ml[i]) - 30 * digit);
           //     Toast.makeText(this, "ml:"+ml[i-1]+"digit:"+digit+"end"+updateMl[i-1], Toast.LENGTH_SHORT).show();
              Updateml updateml = new Updateml();


                updateml.execute(i+"",updateMl[i]);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_select_1);
        mInit();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fetchDrinkData(); // 네트워크에서 데이터 가져오기
                // 데이터가 준비될 때까지 기다린 후 UI 업데이트
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 여기서 UI 업데이트 또는 다른 작업 수행
                        updateUI();
                    }
                });
            }
        });
        thread.start();



    }


    @Override
    public void onBackPressed() {
        if (value < maxValue) {
            // value가 maxValue보다 작은 동안에는 뒤로가기 키 동작을 막음
            Toast.makeText(this, "작업이 완료될 때까지 뒤로가기 키를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed(); // 그 외의 경우에는 뒤로가기 키 동작을 수행
        }
    }

    private void mInit() {
        progressBar = findViewById(R.id.progressBar);
    }

}

