package com.hjq.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.hjq.base.BaseActivity;
import com.hjq.base.action.ActivityAction;
import com.hjq.base.action.BundleAction;
import com.hjq.base.action.ClickAction;
import com.hjq.base.action.HandlerAction;
import com.hjq.base.action.KeyboardAction;
import com.hjq.base.action.ResourcesAction;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class BaseFragment<A extends BaseActivity> extends Fragment implements ActivityAction, ResourcesAction, HandlerAction, ClickAction, BundleAction, KeyboardAction {
    private A mActivity;
    private boolean mLoading;
    private View mRootView;

    public /* synthetic */ boolean getBoolean(String str) {
        return BundleAction.CC.$default$getBoolean(this, str);
    }

    public /* synthetic */ boolean getBoolean(String str, boolean z) {
        return BundleAction.CC.$default$getBoolean(this, str, z);
    }

    public /* synthetic */ int getColor(int i) {
        return ResourcesAction.CC.$default$getColor(this, i);
    }

    public /* synthetic */ double getDouble(String str) {
        return BundleAction.CC.$default$getDouble(this, str);
    }

    public /* synthetic */ double getDouble(String str, int i) {
        return BundleAction.CC.$default$getDouble(this, str, i);
    }

    public /* synthetic */ Drawable getDrawable(int i) {
        return ResourcesAction.CC.$default$getDrawable(this, i);
    }

    public /* synthetic */ float getFloat(String str) {
        return BundleAction.CC.$default$getFloat(this, str);
    }

    public /* synthetic */ float getFloat(String str, int i) {
        return BundleAction.CC.$default$getFloat(this, str, i);
    }

    public /* synthetic */ Handler getHandler() {
        return HandlerAction.CC.$default$getHandler(this);
    }

    public /* synthetic */ int getInt(String str) {
        return BundleAction.CC.$default$getInt(this, str);
    }

    public /* synthetic */ int getInt(String str, int i) {
        return BundleAction.CC.$default$getInt(this, str, i);
    }

    public /* synthetic */ ArrayList getIntegerArrayList(String str) {
        return BundleAction.CC.$default$getIntegerArrayList(this, str);
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    public /* synthetic */ long getLong(String str) {
        return BundleAction.CC.$default$getLong(this, str);
    }

    public /* synthetic */ long getLong(String str, int i) {
        return BundleAction.CC.$default$getLong(this, str, i);
    }

    public /* synthetic */ Parcelable getParcelable(String str) {
        return BundleAction.CC.$default$getParcelable(this, str);
    }

    public /* synthetic */ Serializable getSerializable(String str) {
        return BundleAction.CC.$default$getSerializable(this, str);
    }

    public /* synthetic */ String getString(String str) {
        return BundleAction.CC.$default$getString(this, str);
    }

    public /* synthetic */ ArrayList getStringArrayList(String str) {
        return BundleAction.CC.$default$getStringArrayList(this, str);
    }

    public /* synthetic */ Object getSystemService(Class cls) {
        return ResourcesAction.CC.$default$getSystemService(this, cls);
    }

    public /* synthetic */ void hideKeyboard(View view) {
        KeyboardAction.CC.$default$hideKeyboard(this, view);
    }

    /* access modifiers changed from: protected */
    public abstract void initData();

    /* access modifiers changed from: protected */
    public abstract void initView();

    /* access modifiers changed from: protected */
    public void onActivityResume() {
    }

    public /* synthetic */ void onClick(View view) {
        ClickAction.CC.$default$onClick(this, view);
    }

    /* access modifiers changed from: protected */
    public void onFragmentResume(boolean z) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    public /* synthetic */ boolean post(Runnable runnable) {
        return HandlerAction.CC.$default$post(this, runnable);
    }

    public /* synthetic */ boolean postAtTime(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postAtTime(this, runnable, j);
    }

    public /* synthetic */ boolean postDelayed(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postDelayed(this, runnable, j);
    }

    public /* synthetic */ void removeCallbacks() {
        HandlerAction.CC.$default$removeCallbacks(this);
    }

    public /* synthetic */ void removeCallbacks(Runnable runnable) {
        HandlerAction.CC.$default$removeCallbacks(this, runnable);
    }

    public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, int... iArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, onClickListener, iArr);
    }

    public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, View... viewArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, onClickListener, viewArr);
    }

    public /* synthetic */ void setOnClickListener(int... iArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, iArr);
    }

    public /* synthetic */ void setOnClickListener(View... viewArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, viewArr);
    }

    public /* synthetic */ void showKeyboard(View view) {
        KeyboardAction.CC.$default$showKeyboard(this, view);
    }

    public /* synthetic */ void startActivity(Class cls) {
        ActivityAction.CC.$default$startActivity((ActivityAction) this, cls);
    }

    public /* synthetic */ void toggleSoftInput(View view) {
        KeyboardAction.CC.$default$toggleSoftInput(this, view);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (A) requireActivity();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getLayoutId() <= 0) {
            return null;
        }
        this.mLoading = false;
        this.mRootView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        initView();
        return this.mRootView;
    }

    public void onResume() {
        super.onResume();
        if (!this.mLoading) {
            this.mLoading = true;
            initData();
            onFragmentResume(true);
            return;
        }
        A a = this.mActivity;
        if (a == null || a.getLifecycle().getCurrentState() != Lifecycle.State.STARTED) {
            onFragmentResume(false);
        } else {
            onActivityResume();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mRootView = null;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mLoading = false;
        removeCallbacks();
    }

    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public View getView() {
        return this.mRootView;
    }

    public A getAttachActivity() {
        return this.mActivity;
    }

    public Application getApplication() {
        A a = this.mActivity;
        if (a != null) {
            return a.getApplication();
        }
        return null;
    }

    public <V extends View> V findViewById(int i) {
        return this.mRootView.findViewById(i);
    }

    public Bundle getBundle() {
        return getArguments();
    }

    public void startActivityForResult(Class<? extends Activity> cls, BaseActivity.OnActivityCallback onActivityCallback) {
        getAttachActivity().startActivityForResult(cls, onActivityCallback);
    }

    public void startActivityForResult(Intent intent, BaseActivity.OnActivityCallback onActivityCallback) {
        getAttachActivity().startActivityForResult(intent, (Bundle) null, onActivityCallback);
    }

    public void startActivityForResult(Intent intent, Bundle bundle, BaseActivity.OnActivityCallback onActivityCallback) {
        getAttachActivity().startActivityForResult(intent, bundle, onActivityCallback);
    }

    public void finish() {
        A a = this.mActivity;
        if (a != null && !a.isFinishing() && !this.mActivity.isDestroyed()) {
            this.mActivity.finish();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        for (Fragment next : getChildFragmentManager().getFragments()) {
            if ((next instanceof BaseFragment) && next.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && ((BaseFragment) next).dispatchKeyEvent(keyEvent)) {
                return true;
            }
        }
        int action = keyEvent.getAction();
        if (action == 0) {
            return onKeyDown(keyEvent.getKeyCode(), keyEvent);
        }
        if (action != 1) {
            return false;
        }
        return onKeyUp(keyEvent.getKeyCode(), keyEvent);
    }
}
