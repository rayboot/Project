package com.github.rayboot.project.utils.ptr;

import android.content.Context;
import android.widget.LinearLayout;

import com.github.rayboot.project.R;


/**
 * @author SUN
 * @from 2015/2/9
 * @TODO
 */
public class PullUpFooter extends LinearLayout {
    public PullUpFooter(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.footer_pull_up, this);
    }
}
