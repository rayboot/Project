package com.github.rayboot.project.utils.ptr;

import android.view.View;

/**
 * @author YW.SUN
 * @from 2015/8/4
 * @TODO
 */
public interface IPTRRecyclerListener {


    void onTFPullDownToRefresh(View refreshView);

    void onTFPullUpToRefresh(View refreshView);

    void onScrollUp(int firstVisibleItem);

    void onScrollDown(int firstVisibleItem);
}
