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

public class sign_up extends Activity {
    ApiService apiService = new ApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("註冊頁面");
        setContentView(R.layout.activity_sign_up);

        Button btn_sign_up = findViewById(R.id.btn_sign_up);
        Button btn_to_login = findViewById(R.id.btn_to_login);
        final LinearLayout layout = findViewById(R.id.layout);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得 EditText 資料
                final EditText account_text = findViewById(R.id.student_id);
                final EditText password_text = findViewById(R.id.password);
                final EditText user_name_text = findViewById(R.id.name);
                final EditText phone_number_text = findViewById(R.id.phone);
                String account = account_text.getText().toString();
                String password = password_text.getText().toString();
                String user_name = user_name_text.getText().toString();
                String phone_number = phone_number_text.getText().toString();

                if(account.isEmpty() || password.isEmpty() || user_name.isEmpty() || phone_number.isEmpty()) {
                    layout.post(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "輸入資料不可為空", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                apiService.checkAccountExist(account, exist -> runOnUiThread(() -> {
                    if (exist) {
                        layout.post(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "此學號已被註冊過", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // 將資料寫入資料庫
                        apiService.createUser(account, password, user_name, phone_number, success -> runOnUiThread(() -> {
                            if (success) {
                                Toast.makeText(getApplicationContext(), "帳號註冊成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "帳號註冊失敗", Toast.LENGTH_SHORT).show();
                            }
                        // 清空 EditText
                        account_text.post(new Runnable() {
                            public void run() {
                                account_text.setText("");
                                password_text.setText("");
                                user_name_text.setText("");
                                phone_number_text.setText("");
                            }
                        });
                        Intent intent = new Intent();
                        intent.setClass(sign_up.this, login.class);
                        startActivity(intent);
                        }));
                    }
                }));
            }
        });

        btn_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(sign_up.this, login.class);
                startActivity(intent);
            }
        });

    }
}
