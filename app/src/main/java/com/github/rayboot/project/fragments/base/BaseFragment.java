package com.github.rayboot.project.fragments.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.rayboot.project.activities.base.BaseAppCompatActivity;
import com.github.rayboot.project.api.services.ApiService;
import com.github.rayboot.project.managers.listeners.IEventBus;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * author: rayboot  Created on 15/12/3.
 * email : sy0725work@gmail.com
 */
public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    public static final ApiService apiService = BaseAppCompatActivity.apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this instanceof IEventBus) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (getActivity() instanceof BaseAppCompatActivity) {
            CompositeSubscription subscription = ((BaseAppCompatActivity) getActivity()).getCompositeSubscription();
            if (subscription != null) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this instanceof IEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void addSubscription(Subscription s) {
        if (getActivity() instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) getActivity()).addSubscription(s);
        }
    }
}
