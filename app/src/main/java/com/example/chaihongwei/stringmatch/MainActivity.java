package com.example.chaihongwei.stringmatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBF:
                startActivity(new Intent(this, BFActivity.class));
                break;
            case R.id.btnRK:
                startActivity(new Intent(this, RKActivity.class));
                break;
            case R.id.btnKMP:
                startActivity(new Intent(this, KMPActivity.class));
                break;
            default:
                //无需处理
                break;
        }
    }
}
