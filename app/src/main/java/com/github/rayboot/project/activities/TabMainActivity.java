package com.github.rayboot.project.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.rayboot.project.R;
import com.github.rayboot.project.activities.base.BaseAppCompatActivity;

public class TabMainActivity extends BaseAppCompatActivity {

    public static void open(Context context) {
        context.startActivity(new Intent(context, TabMainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
    }
}
