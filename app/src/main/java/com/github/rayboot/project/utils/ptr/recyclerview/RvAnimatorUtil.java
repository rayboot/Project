package com.github.rayboot.project.utils.ptr.recyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @author YW.SUN
 * @from 2015/9/15
 * @TODO
 */
public class RvAnimatorUtil {

    public static Animator[] getSlideInBottomAnimator(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationY", (float) view.getMeasuredHeight(), 0.0F)};
    }

    public static Animator[] getAlphaInAnimatorIn(float mFrom, View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", mFrom, 1.0F)};
    }

    public static Animator[] getScalInAnimator(float mFrom, View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", mFrom, 1.0F);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", mFrom, 1.0F);
        return new ObjectAnimator[]{scaleX, scaleY};
    }

    public static Animator[] getSlideInLeftAnimator(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", (float) (-view.getRootView().getWidth()), 0.0F)};
    }

    public static Animator[] getSlideInRightAnimator(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", (float) view.getRootView().getWidth(), 0.0F)};
    }

    public static Animator[] getScaleAndAlphaAnimator(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.0F);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.0F);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1.0F);
        return new Animator[]{alpha, scaleX, scaleY};
    }
}
