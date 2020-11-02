package com.hzb.utils.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseFragment extends AppCompatDialogFragment {
    protected Activity mContext;
    protected CompositeDisposable mCompositeDisposable;

    private boolean isCreated = false, isVisible = false, isEnter = false, isLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
//        Logger.d("log-fragment", getClass().getSimpleName() + ".java");
        isCreated = true;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public boolean isVisibleNow() {
        return isCreated && isVisible;
    }

//    public boolean ancestorsVisible() {
//        if (getParentFragment() != null && getParentFragment() instanceof BaseFragment) {
//            return isVisibleNow() && ((BaseFragment) getParentFragment()).ancestorsVisible();
//        } else {
//            return isVisible();
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        if (isVisible) {
            onLeave();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isVisible) {
            onEnter();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isCreated && isVisible) {
            onEnter();
            if (!isLoaded)
                onLoad();
        } else {
            onLeave();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear(); // clear时网络请求会随即cancel
            mCompositeDisposable = null;
        }
    }

    protected void onEnter() {
        if (!isEnter && getContext() != null) {
            isEnter = true;
        }
    }

    private void onLeave() {
        if (isEnter && getContext() != null) {
            isEnter = false;
        }
    }

    public boolean isLazyLoaded() {
        return isLoaded;
    }

    private void onLoad() {
        isLoaded = true;
        new Handler(Looper.getMainLooper()).post(this::lazyLoad);
    }

    protected void lazyLoad() {
    }

    public boolean onBackPressed() {
        return false;
    }

}

