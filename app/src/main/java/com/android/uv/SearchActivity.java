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

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                // 跳转到图表页面
                startActivity(new Intent(getApplicationContext(),CancerChart.class));
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
                    setContentView(R.layout.activity_selfcheck);
                    break;
                case R.id.skin_cancer:
                    setContentView(R.layout.activity_cancer_charts);
                    startActivity(new Intent(getApplicationContext(),CancerChart.class));
                    break;
                case R.id.tips:
                    setContentView(R.layout.activity_suggest);
                    Intent intent = new Intent(getApplicationContext(),SuggestActivity.class);
                    intent.putExtra("data",str);
                    startActivity(intent);
                    break;
                case R.id.exit:
                    setContentView(R.layout.activity_search);
                    startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
}