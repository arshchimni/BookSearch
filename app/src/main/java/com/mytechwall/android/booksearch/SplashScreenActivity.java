package com.mytechwall.android.booksearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by arshdeep chimni on 03-06-2017.
 */

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
