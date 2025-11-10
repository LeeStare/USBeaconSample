package com.THLight.USBeacon.Sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.THLight.USBeacon.Sample.R;
import com.THLight.USBeacon.Sample.service.ApiService;

public class login_teacher extends Activity {

    ApiService apiService = new ApiService();
    private final String CHANNEL_ID = "Coder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        TextView user_name = findViewById(R.id.user_name);
        final String get_user_name = getIntent().getExtras().getString("user");
        user_name.setText(get_user_name);
        Button btn_to_game = findViewById(R.id.btn_to_game);
        Button btn_to_create_sign_up_form = findViewById(R.id.btn_to_create_sign_up_form);
        Button btn_to_open_roll_call = findViewById(R.id.btn_to_open_roll_call);
        Button btn_to_close_roll_call = findViewById(R.id.btn_to_close_roll_call);
        Button btn_to_search_today = findViewById(R.id.btn_to_search_today);
        Button btn_to_search_student = findViewById(R.id.btn_to_search_student);
        Button btn_to_delete_table = findViewById(R.id.btn_to_delete_table);

        btn_to_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login_teacher.this, GameActivity9.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", get_user_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_to_create_sign_up_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login_teacher.this, classProduce.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", get_user_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_to_open_roll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login_teacher.this, rollCall.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", get_user_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_to_close_roll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run(){
                        apiService.setFlagZero(get_user_name, exist -> runOnUiThread(() -> {
                            if (exist) {
                                Toast.makeText(getApplicationContext(), "已關閉課程點名", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "關閉課程點名失敗", Toast.LENGTH_SHORT).show();
                            }
                        }));
                    }
                }).start();
            }
        });

        btn_to_search_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login_teacher.this, todayAttendantInquire.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", get_user_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_to_search_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login_teacher.this, Attendant_teacher.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", get_user_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_to_delete_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login_teacher.this, Attendant_teacher.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", get_user_name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}