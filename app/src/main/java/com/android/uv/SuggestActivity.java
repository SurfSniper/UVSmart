package com.android.uv;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        TextView tv = findViewById(R.id.tv);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        // add return icon.
        actionBar.setDisplayHomeAsUpEnabled(true);

        String data = getIntent().getStringExtra("data");
        if (TextUtils.isEmpty(data) || !TextUtils.isDigitsOnly(data.replaceAll("\\.", ""))) {
            return;
        }

        float uvNum = Float.parseFloat(data);
        String str = "";
        if (uvNum >= 0 && uvNum <= 3) {
            str = "Low UV (0~3): \n" +
                    "You can safely stay outside! A UV Index reading of 0 to 2 means low danger from the sun's UV rays for the average person.\n" +
                    " - Wear sunglasses on bright days.\n" +
                    " - If you burn easily, cover up and use broad spectrum SPF 30+ sunscreen.\n" +
                    " - Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.";
        } else if (uvNum > 3 && uvNum <= 6) {
            str = "Moderate UV (3~5):\n" +
                    "Seek shade during midday hours! A UV Index reading of 3 to 5 means moderate risk of harm from unprotected sun exposure.\n" +
                    "- Stay in shade near midday when the sun is strongest.\n" +
                    "- If outdoors, wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n" +
                    "- Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating. \n" +
                    "- Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.";
        } else if (uvNum > 6 && uvNum <= 8) {
            str = "High UV (6~7):\n" +
                    "A UV Index reading of 6 to 7 means high risk of harm from unprotected sun exposure. Protection against skin and eye damage is needed.\n" +
                    "Reduce time in the sun between 10 a.m. and 4 p.m.\n" +
                    "- If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n" +
                    "- Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating. \n" +
                    "- Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.";
        } else if (uvNum > 8 && uvNum <= 11) {
            str = "Very High (8-10):\n" +
                    "A UV Index reading of 8 to 10 means very high risk of harm from unprotected sun exposure. Take extra precautions because unprotected skin and eyes will be damaged and can burn quickly.\n" +
                    "Minimise sun exposure between 10 a.m. and 4 p.m.\n" +
                    "- If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n" +
                    "- Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating. \n" +
                    "- Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.";
        } else if (uvNum >= 11) {
            str = "Extreme UV (11+):\n" +
                    "Avoid being exposed to the sun! A UV Index reading of 11 or more means extreme risk of harm from unprotected sun exposure. Take all precautions because unprotected skin and eyes can burn in minutes. \n" +
                    "- Try to avoid sun exposure between 10 a.m. and 4 p.m.\n" +
                    "- If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n" +
                    "- Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating.\n" +
                    "- Watch out for bright surfaces, like sand, water and snow, which reflect UV and increase exposure.";
        }

        if(TextUtils.isEmpty(str)) {
            return;
        }
        tv.setText(str);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}