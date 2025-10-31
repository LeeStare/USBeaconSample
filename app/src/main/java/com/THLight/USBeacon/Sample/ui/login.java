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
import com.THLight.USBeacon.Sample.entity.HttpJsonObject.ApiHelper;
import com.THLight.USBeacon.Sample.service.MysqlCon;

public class login extends Activity {
    public static final int FUNC_LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("登入頁面");
        setContentView(R.layout.activity_login);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        Button btn_to_login_successfully = (Button) findViewById(R.id.btn_to_login_successfully);
        Button btn_to_sign_up = (Button) findViewById(R.id.btn_to_sign_up);
        Button btn_to_MainActivity = (Button) findViewById(R.id.btn_to_MainActivity);
        final EditText student_id = (EditText) findViewById(R.id.student_id);
        final EditText password = (EditText) findViewById(R.id.password);

        btn_to_login_successfully.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MysqlCon con = new MysqlCon();
                //con.run();
                String id = student_id.getText().toString().trim();
                String psw = password.getText().toString().trim();
                String user = con.getUserName(id);
                //System.out.println(con.checkIfExistAccount(student_id.getText().toString().trim()
                //        ,password.getText().toString().trim()));

                // 呼叫 API 並用 Callback 接收結果
                con.checkIfExistAccount(id, psw, new ApiHelper.BooleanCallback() {
                    @Override
                    public void onResult(boolean exist) {
                        // 背景執行緒，所以要切回 UI 執行緒
                        runOnUiThread(() -> {
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
                        });
                        //intent.setClass(login.this, login_successfully.class);
                        /*Bundle bundle = new Bundle();
                        bundle.putString("student_id",student_id.getText().toString());
                        //bundle.putString("password",password.getText().toString());
                        intent.putExtras(bundle);*/
                        //startActivity(intent);
                    }
                });
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
    // Disable back button
    /*@Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }*/
}