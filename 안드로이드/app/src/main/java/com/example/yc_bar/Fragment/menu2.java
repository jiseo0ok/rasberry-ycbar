package com.example.yc_bar.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yc_bar.MainActivity;
import com.example.yc_bar.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;


public class menu2 extends Fragment {

    String encodeImageString;
    String checkedRadioButtonText;
    TextView action1TextView;
    TextView action2TextView;
    TextView action3TextView;
    TextView action4TextView;
    TextView action5TextView;
    TextView action6TextView;
    TextView action7TextView;
    RadioButton checkedRadioButton;
    Context ct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    private void increaseActionValue(TextView action1TextView) {
        int currentValue = Integer.parseInt(action1TextView.getText().toString());
        currentValue++;
        action1TextView.setText(String.valueOf(currentValue));
    }

    // action_1 값을 감소시키는 메소드
    private void decreaseActionValue(TextView action1TextView) {
        int currentValue = Integer.parseInt(action1TextView.getText().toString());
        if (currentValue > 0) {
            currentValue--;
            action1TextView.setText(String.valueOf(currentValue));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu2, container, false);
        ct = container.getContext();

        // action_1 값을 증가시키는 메소드



        RadioGroup radioGroup = v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 중복 선택 방지를 위해 선택된 라디오 버튼 확인
                checkedRadioButton = v.findViewById(checkedId);
                checkedRadioButtonText = checkedRadioButton.getText().toString();

                // 선택된 라디오 버튼에 대한 처리
                switch (checkedId) {
                    case R.id.menu_2_kind_1:
                        // Option 1 선택 시 처리

                        break;
                    case R.id.menu_2_kind_2:
                        // Option 2 선택 시 처리
                        break;
                    case R.id.menu_2_kind_3:
                        // Option 2 선택 시 처리
                        break;
                    // 다른 라디오 버튼들에 대한 처리 추가
                }
            }
        });
        EditText drinkname = v.findViewById(R.id.drinkname);

        EditText drinkcontent = v.findViewById(R.id.drinkcontent);
        ImageButton plus1Button = v.findViewById(R.id.plus1);
        ImageButton minus1Button = v.findViewById(R.id.minus1);
        TextView action_1=v.findViewById(R.id.action_1);

        plus1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_1);
            }
        });

        minus1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_1);
            }
        });
        action1TextView = v.findViewById(R.id.action_1);

        ImageButton plus2Button = v.findViewById(R.id.plus2);
        ImageButton minus2Button = v.findViewById(R.id.minus2);
        TextView action_2=v.findViewById(R.id.action_2);

        plus2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_2);
            }
        });

        minus2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_2);
            }
        });
        action2TextView = v.findViewById(R.id.action_2);

        ImageButton plus3Button = v.findViewById(R.id.plus3);
        ImageButton minus3Button = v.findViewById(R.id.minus3);
        TextView action_3=v.findViewById(R.id.action_3);

        plus3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_3);
            }
        });

        minus3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_3);
            }
        });
        action3TextView = v.findViewById(R.id.action_3);

        ImageButton plus4Button = v.findViewById(R.id.plus4);
        ImageButton minus4Button = v.findViewById(R.id.minus4);
        TextView action_4=v.findViewById(R.id.action_4);

        plus4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_4);
            }
        });

        minus4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_4);
            }
        });
        action4TextView = v.findViewById(R.id.action_4);

        ImageButton plus5Button = v.findViewById(R.id.plus5);
        ImageButton minus5Button = v.findViewById(R.id.minus5);
        TextView action_5=v.findViewById(R.id.action_5);

        plus5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_5);
            }
        });

        minus5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_5);
            }
        });
        action5TextView = v.findViewById(R.id.action_5);

        ImageButton plus6Button = v.findViewById(R.id.plus6);
        ImageButton minus6Button = v.findViewById(R.id.minus6);
        TextView action_6=v.findViewById(R.id.action_6);

        plus6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_6);
            }
        });

        minus6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_6);
            }
        });
        action6TextView = v.findViewById(R.id.action_6);



        /*ImageButton plus7Button = v.findViewById(R.id.plus7);
        ImageButton minus7Button = v.findViewById(R.id.minus7);
        TextView action_7=v.findViewById(R.id.action_7);

        plus7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // plus1 버튼 클릭 시 action_1 값을 증가시킴
                increaseActionValue(action_7);
            }
        });

        minus7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // minus1 버튼 클릭 시 action_1 값을 감소시킴
                decreaseActionValue(action_7);
            }
        });

        action7TextView = v.findViewById(R.id.action_7);
        */
        Button upload = v.findViewById(R.id.upload);
        upload.setOnClickListener(view -> {
            Dexter.withContext(getContext())
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            // 권한이 거부된 경우 처리
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        });


        Button enter =v.findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 입력된 정보를 string으로 가져오기
                String drinkname1 = drinkname.getText().toString();
                String drinkcontent1 = drinkcontent.getText().toString();
                String drinkphoto1 = encodeImageString;

                String kind1=checkedRadioButtonText;
                String how1 = action1TextView.getText().toString()+action2TextView.getText().toString()+action3TextView.getText().toString()+action4TextView.getText().toString()+action5TextView.getText().toString()+action6TextView.getText().toString()/*+action7TextView.getText().toString()*/;
                //image1 = image.getText().toString();





                // 회원가입 절차 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(ct.getApplicationContext(), "레시피 추가 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ct, MainActivity.class); // MainClass 대신에 실제로 전환하고자 하는 클래스명을 넣어주세요.
                                ct.startActivity(intent);



                            }else{// 회원가입이 안된다면
                                Toast.makeText(ct.getApplicationContext(), "레시피 추가 실패. 다시 한 번 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                drinkregister drinkregister = new drinkregister(drinkname1, drinkcontent1, drinkphoto1,kind1,how1,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ct);
                queue.add(drinkregister);

            }
        });


        return v;

    }
    public class drinkregister extends StringRequest {
        final static private String URL = "http://bighero.iptime.org/cnrk.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
        private Map<String, String> parameters;


        public drinkregister( String drinkname, String drinkcontent, String drinkphoto, String kind, String how, Response.Listener<String> listener){
            super(Request.Method.POST, URL, listener, null);

            parameters = new HashMap<>();
            parameters.put("drinkname", drinkname);
            parameters.put("drinkcontent", drinkcontent);
            parameters.put("drinkphoto", drinkphoto);
            parameters.put("kind", kind);
            parameters.put("how", how);
        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {
                        // Convert the Uri to a Bitmap
                        InputStream inputStream = ct.getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        // Set the Bitmap to the ImageView
                        ImageView imageView = getView().findViewById(R.id.uploadphoto); // Replace 'imageView' with your ImageView ID
                        imageView.setImageBitmap(bitmap);
                        encodeBitmapImage(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesOfImage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesOfImage, Base64.DEFAULT);

    }

}