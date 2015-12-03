package com.github.rayboot.project.utils.ptr.recyclerview;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * @author YW.SUN
 * @from 2015/9/15
 * @TODO
 */
public class ViewHelper {
    public ViewHelper() {
    }

    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1.0F);
        ViewCompat.setScaleY(v, 1.0F);
        ViewCompat.setScaleX(v, 1.0F);
        ViewCompat.setTranslationY(v, 0.0F);
        ViewCompat.setTranslationX(v, 0.0F);
        ViewCompat.setRotation(v, 0.0F);
        ViewCompat.setRotationY(v, 0.0F);
        ViewCompat.setRotationX(v, 0.0F);
        v.setPivotY((float) (v.getMeasuredHeight() / 2));
        ViewCompat.setPivotX(v, (float) (v.getMeasuredWidth() / 2));
        ViewCompat.animate(v).setInterpolator(null);
    }
}
