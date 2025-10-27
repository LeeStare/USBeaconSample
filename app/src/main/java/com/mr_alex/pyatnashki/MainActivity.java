package com.mr_alex.pyatnashki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageView logo;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.show);

        logo = (ImageView) findViewById(R.id.logo);

        new CountDownTimer(5000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText("請等待：" + millisUntilFinished / 1000 + "秒...");
            }

            @Override
            public void onFinish() {
                menuActivity();
            }
        }.start();
    }

    private void menuActivity() {
        startActivity(new Intent(MainActivity.this, MenuActivity.class));
        finish();
    }
}
