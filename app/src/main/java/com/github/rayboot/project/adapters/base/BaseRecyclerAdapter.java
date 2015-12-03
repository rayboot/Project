package com.github.rayboot.project.adapters.base;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.github.rayboot.project.utils.ptr.PullUpFooter;
import com.github.rayboot.project.utils.ptr.recyclerview.ViewHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author YW.SUN
 * @from 2015/8/4
 * @TODO
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_VIEW_TYPE = -1000;
    private static final int FOOTER_VIEW_TYPE = -2000;
    private final List<View> mHeaders = new ArrayList<>();
    private final List<View> mFooters = new ArrayList<>();
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    public List<T> listData;
    public PullUpFooter progView;
    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();

    private int mLastPosition = -1;
    private boolean isFirstOnly = true;

    public BaseRecyclerAdapter(Context mContext, List<T> listData) {
        super();
        this.mContext = mContext;
        this.listData = listData;
        mLayoutInflater = LayoutInflater.from(mContext);
        progView = new PullUpFooter(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progView.setLayoutParams(layoutParams);
        progView.setVisibility(View.GONE);
        addFooter(progView);
    }

    public List<T> getListData() {
        return listData == null ? new ArrayList<>() : listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public abstract RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup, int viewType);

    public abstract void bindData(RecyclerView.ViewHolder viewHolder, int position);

    public abstract int getItemType(int position);

    protected abstract Animator[] getAnimators(View var1);

    @Override
    public int getItemViewType(int position) {
        if (position < mHeaders.size()) {
            return HEADER_VIEW_TYPE + position;

        } else if (position < (mHeaders.size() + listData.size())) {
            return getItemType(position - mHeaders.size());

        } else {
            return FOOTER_VIEW_TYPE + position - mHeaders.size() - listData.size();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (isHeader(viewType)) {
            int whichHeader = Math.abs(viewType - HEADER_VIEW_TYPE);
            View headerView = mHeaders.get(whichHeader);
            return new RecyclerView.ViewHolder(headerView) {
            };
        } else if (isFooter(viewType)) {
            int whichFooter = Math.abs(viewType - FOOTER_VIEW_TYPE);
            View footerView = mFooters.get(whichFooter);
            return new RecyclerView.ViewHolder(footerView) {
            };
        } else {
            return getViewHolder(viewGroup, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (position < mHeaders.size()) {
            // Headers don't need anything special

        } else if (position < mHeaders.size() + listData.size()) {
            // This is a real position, not a header or footer. Bind it.
            bindData(viewHolder, position - mHeaders.size());

        } else {
            // Footers don't need anything special
        }

        if (this.isFirstOnly && position <= this.mLastPosition) {
            ViewHelper.clear(viewHolder.itemView);
        } else {
            Animator[] var3 = this.getAnimators(viewHolder.itemView);
            int var4 = var3.length;
            for (int var5 = 0; var5 < var4; ++var5) {
                Animator anim = var3[var5];
                anim.setDuration((long) this.mDuration).start();
                anim.setInterpolator(this.mInterpolator);
            }
            this.mLastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mHeaders.size() + listData.size() + mFooters.size();
    }

    public int getCount() {
        return listData.size();
    }

    public T getItem(int position) {
        return listData.get(position);
    }

    /**
     * Adds a header view.
     */
    public void addHeader(@NonNull View view) {
        mHeaders.add(view);
    }

    /**
     * Adds a footer view.
     */
    public void addFooter(@NonNull View view) {
        if (mFooters.contains(progView)) {
            mFooters.add(mFooters.size() - 1, view);
        } else {
            mFooters.add(view);
        }
    }

    /**
     * Toggles the visibility of the header views.
     */
    public void setHeaderVisibility(boolean shouldShow) {
        for (View header : mHeaders) {
            header.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Toggles the visibility of the footer views.
     */
    public void setFooterVisibility(boolean shouldShow) {
        for (View footer : mFooters) {
            if (!(footer instanceof PullUpFooter)) {
                footer.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
            }
        }
    }

    /**
     * @return the number of headers.
     */
    public int getHeaderCount() {
        return mHeaders.size();
    }

    /**
     * @return the number of footers.
     */
    public int getFooterCount() {
        return mFooters.size();
    }

    /**
     * Gets the indicated header, or null if it doesn't exist.
     */
    public View addgetHeader(int i) {
        return i < mHeaders.size() ? mHeaders.get(i) : null;
    }

    /**
     * Gets the indicated footer, or null if it doesn't exist.
     */
    public View getFooter(int i) {
        return i < mFooters.size() ? mFooters.get(i) : null;
    }

    public void removeHeader(View view) {
        if (mHeaders.contains(view)) {
            mHeaders.remove(view);
        }
    }

    public void removeFooter(View view) {
        if (mFooters.contains(view)) {
            mFooters.remove(view);
        }
    }

    public void removeAllFooter() {
        mFooters.clear();
    }

    private boolean isHeader(int viewType) {
        return viewType >= HEADER_VIEW_TYPE && viewType < (HEADER_VIEW_TYPE + mHeaders.size());
    }

    private boolean isFooter(int viewType) {
        return viewType >= FOOTER_VIEW_TYPE && viewType < (FOOTER_VIEW_TYPE + mFooters.size());
    }

    public void setProgVisibility(boolean visibility) {
        if (progView != null) {
            progView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        }
    }
}
