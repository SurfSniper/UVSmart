package com.android.uv;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SelfCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfcheck);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        // add return icon.
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                setContentView(R.layout.activity_search);
//                startActivity(new Intent(getApplicationContext(),SearchActivity.class));
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}