package com.android.uv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    String str;
    String timezone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView tv = findViewById(R.id.tv_uv);
        TextView tz = findViewById(R.id.timezone);

        ProgressDialog progressDialog = ProgressDialog.show(this,"","loading",false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                str = DataUtils.doQuery(SearchActivity.this,progressDialog);
                timezone = DataUtils.doQuerytimecoon(SearchActivity.this,progressDialog);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!TextUtils.isEmpty(str)) {
                            tv.setText(str+" UV max");
                            tz.setText(timezone+" ");
                        }
                    }
                });
            }
        }).start();


        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转到建议页面
                Intent intent = new Intent(getApplicationContext(),SuggestActivity.class);
                intent.putExtra("data",str);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("ResourceType")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.self_exams:
                setContentView(R.menu.self_exams);
                break;
            case R.id.skin_cancer:
                setContentView(R.menu.skin_cancer);
                break;
            case R.id.tips:
                setContentView(R.menu.tips);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}