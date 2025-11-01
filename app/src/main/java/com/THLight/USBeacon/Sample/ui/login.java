package com.THLight.USBeacon.Sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.THLight.USBeacon.Sample.R;
import com.THLight.USBeacon.Sample.service.ApiService;

public class login extends Activity {

    ApiService apiService = new ApiService();
    private String currentUserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("登入頁面");
        setContentView(R.layout.activity_login);
        final LinearLayout layout = findViewById(R.id.layout);
        Button btn_to_login_successfully = findViewById(R.id.btn_to_login_successfully);
        Button btn_to_sign_up = findViewById(R.id.btn_to_sign_up);
        Button btn_to_MainActivity = findViewById(R.id.btn_to_MainActivity);
        final EditText student_id = findViewById(R.id.student_id);
        final EditText password = findViewById(R.id.password);

        btn_to_login_successfully.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = student_id.getText().toString().trim();
                String psw = password.getText().toString().trim();
                apiService.getUserName("teacher01", userName -> runOnUiThread(() -> {
                    // ✅ 這裡是 callback，請求已完成
                    currentUserName = userName;

                    if (currentUserName.isEmpty()) {
                        Toast.makeText(login.this, "查無此帳號", Toast.LENGTH_SHORT).show();
                    }
                }));
                String user = currentUserName;

                // 呼叫方法
                apiService.checkIfExistAccount(id, psw, exist -> runOnUiThread(() -> {
                    if (exist) {
                        Toast.makeText(login.this, "登入成功！", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        if (user.equals("郭教授")) {
                            intent.setClass(login.this, login_teacher.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("user", user);
                            intent.putExtras(bundle);

                        } else {
                            Toast.makeText(login.this, "帳號或密碼錯誤", Toast.LENGTH_SHORT).show();
                            intent.setClass(login.this, login_successfully.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("user", user);
                            intent.putExtras(bundle);
                        }
                        startActivity(intent);
                    } else {
                        layout.post(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "帳號密碼有誤或尚未註冊", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }));
            }
        });

        btn_to_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login.this, sign_up.class);
                startActivity(intent);
            }
        });

        btn_to_MainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.isConnect);
                Intent intent = new Intent();
                intent.setClass(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}