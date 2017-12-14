package com.example.chaihongwei.stringmatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String TAG = "chwMatch";

    private static final int MAX_LOOP_NUM = 10_0000;

    private EditText etSource, etKeyWord;
    private Button btnMatch;
    protected TextView tvTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        etSource = findViewById(R.id.etSource);
        etKeyWord = findViewById(R.id.etKeyWord);
        btnMatch = findViewById(R.id.btnMatch);
        tvTips = findViewById(R.id.tvTips);

        btnMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTips.setText("循环搜索中,请稍等...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final long startTime = System.currentTimeMillis();
                        for (int i = 0; i < MAX_LOOP_NUM; i++) {
                            textMatch(etSource.getText().toString(), etKeyWord.getText().toString());
                        }
                        final long endTime = System.currentTimeMillis();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvTips.setText("循环" + MAX_LOOP_NUM + "次,耗时:" + (endTime - startTime) + "毫秒");
                            }
                        });
                    }
                }).start();
            }
        });
    }

    protected abstract void textMatch(String originText, String keyword);
}
