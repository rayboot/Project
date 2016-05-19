package com.github.rayboot.project.utils.ptr;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.rayboot.project.adapters.base.BaseRecyclerAdapter;


/**
 * @author YW.SUN
 * @from 2015/7/28
 * @TODO
 */
public class TFPTRRecyclerViewHelper {
    protected IPTRRecyclerListener tfPtrListener;
    private Context mContext;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private Mode refreshMode = Mode.BOTH;
    private boolean isLoad = false;
    private int lastVisibleItem;
    private int firstVisibleItem;

    public TFPTRRecyclerViewHelper(Context context, RecyclerView recyclerView, SwipeRefreshLayout refreshLayout) {
        this.mRecyclerView = recyclerView;
        this.mContext = context;
        this.mRefreshLayout = refreshLayout;
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        setupPull2RefreshListView();
    }

    public TFPTRRecyclerViewHelper setTFPTRMode(Mode mode) {
        this.refreshMode = mode;
        switch (mode) {
            case PULL_FORM_START:
                mRefreshLayout.setEnabled(true);
                setProgVisibility(false);
                break;

            case BOTH:
                mRefreshLayout.setEnabled(true);
                break;

            case PULL_FROM_END:
                mRefreshLayout.setEnabled(false);
                break;

            case DISABLED:
                mRefreshLayout.setEnabled(false);
                setProgVisibility(false);
                break;

            default:
                mRefreshLayout.setEnabled(true);
                break;
        }
        return this;
    }

    public TFPTRRecyclerViewHelper tfPtrListener(IPTRRecyclerListener tfPtrListener) {
        this.tfPtrListener = tfPtrListener;
        return this;
    }

    public void setupPull2RefreshListView() {
//        mRefreshLayout.setColorSchemeResources(R.color.primaryColor, R.color.my_1, R.color.my_2, R.color.my_3);
        mRefreshLayout.setOnRefreshListener(() -> {
            if (refreshMode == Mode.BOTH || refreshMode == Mode.PULL_FORM_START) {
                if (tfPtrListener != null) tfPtrListener.onTFPullDownToRefresh(mRecyclerView);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    //滑动停止
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //glide加载图片
//                        Glide.with(mContext).resumeRequests();

                        //加载更多
                        if (lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()
                                && !isLoad
                                && recyclerView.getAdapter().getItemCount() > 1) {
                            doLoadMore();
                        }
                        //开始滑动 glide停止加载图片
                    } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                        Glide.with(mContext).pauseRequests();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    if (dy > 0) {
                        if (tfPtrListener != null) tfPtrListener.onScrollUp(firstVisibleItem);
                    } else {
                        if (tfPtrListener != null) tfPtrListener.onScrollDown(firstVisibleItem);
                    }
                }
            }
        });
    }

    public void doLoadMore() {
        isLoad = true;
        if (refreshMode == Mode.BOTH || refreshMode == Mode.PULL_FROM_END) {
            if (tfPtrListener != null) tfPtrListener.onTFPullUpToRefresh(mRecyclerView);
            setProgVisibility(true);
        }
    }

    public void setProgVisibility(boolean visibility) {
        if (mRecyclerView.getAdapter() instanceof BaseRecyclerAdapter) {
            ((BaseRecyclerAdapter) mRecyclerView.getAdapter()).setProgVisibility(visibility);
        }
    }

    public void finishTFPTRRefresh() {
        mRefreshLayout.setRefreshing(false);
        isLoad = false;
    }

    public enum Mode {
        //上、下拉全部禁止
        DISABLED(0x0),

        //仅下拉刷新
        PULL_FORM_START(0x1),

        //仅上拉加载更多
        PULL_FROM_END(0x2),

        //上、下拉都可以
        BOTH(0x3);

        Mode(int modeInt) {
        }
    }
}
