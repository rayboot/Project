package com.github.rayboot.project.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.github.rayboot.project.R;
import com.github.rayboot.project.activities.base.BaseAppCompatActivity;
import com.github.rayboot.project.fragments.HomeFragment;
import com.github.rayboot.project.fragments.MineFragment;
import com.github.rayboot.project.fragments.MsgFragment;
import com.github.rayboot.project.fragments.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabMainActivity extends BaseAppCompatActivity {
    @Bind(R.id.container)
    FrameLayout container;
    private BaseFragment currentFragment = null;

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
        showContent(view.getId());
    }

    public BaseFragment getFragment(int navType) {
        switch (navType) {
            case R.id.tab1:
                return HomeFragment.newInstance("首页");
            case R.id.tab2:
                return MsgFragment.newInstance("消息");
            case R.id.tab3:
                return MineFragment.newInstance("我的");
        }
        return null;
    }


    public void showContent(int navType) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(navType + "");
        if (fragment == null) {
            fragment = getFragment(navType);
        }
        if (currentFragment != null && currentFragment.equals(fragment)) {
            return;
        }

        invalidateOptionsMenu();
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.container, fragment, navType + "");
        }
        ft.commitAllowingStateLoss();
    }
}
