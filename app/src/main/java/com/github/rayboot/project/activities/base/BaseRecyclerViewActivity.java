package com.github.rayboot.project.activities.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.github.rayboot.project.R;
import com.github.rayboot.project.utils.ptr.IPTRRecyclerListener;
import com.github.rayboot.project.utils.ptr.TFPTRRecyclerViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseRecyclerViewActivity extends BaseAppCompatActivity {



    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.rv_content)
    protected RecyclerView rvContent;
    @Bind(R.id.srl_refresh_layout)
    protected SwipeRefreshLayout srlRefreshLayout;

    protected TFPTRRecyclerViewHelper tfptrListViewHelper;
    protected IPTRRecyclerListener ptrListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler_view);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    protected void setupPTR() {
        tfptrListViewHelper =
                new TFPTRRecyclerViewHelper(this, rvContent, srlRefreshLayout)
                        .setTFPTRMode(TFPTRRecyclerViewHelper.Mode.BOTH)
                        .tfPtrListener(ptrListener);
    }

}
