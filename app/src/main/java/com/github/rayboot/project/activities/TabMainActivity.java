package com.github.rayboot.project.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.github.rayboot.project.R;
import com.github.rayboot.project.activities.base.BaseAppCompatActivity;
import com.github.rayboot.project.fragments.HomeFragment;
import com.github.rayboot.project.fragments.MineFragment;
import com.github.rayboot.project.fragments.MsgFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabMainActivity extends BaseAppCompatActivity {

    @Bind(R.id.container)
    FrameLayout container;

    public static void open(Context context) {
        context.startActivity(new Intent(context, TabMainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
        ButterKnife.bind(this);
    }

    public void clickTab(View view) {

        switch (view.getId()) {
            case R.id.tab1:
                showContent(HomeFragment.newInstance("首页"));
                break;
            case R.id.tab2:
                showContent(MsgFragment.newInstance("消息"));
                break;
            case R.id.tab3:
                showContent(MineFragment.newInstance("我的"));
                break;
        }
    }


    public void showContent(Fragment fragment) {
        invalidateOptionsMenu();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container, fragment);

        ft.commitAllowingStateLoss();
    }
}
