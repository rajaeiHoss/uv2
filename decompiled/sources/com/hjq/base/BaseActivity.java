package com.hjq.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.hjq.base.action.ActivityAction;
import com.hjq.base.action.BundleAction;
import com.hjq.base.action.ClickAction;
import com.hjq.base.action.HandlerAction;
import com.hjq.base.action.KeyboardAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class BaseActivity extends AppCompatActivity implements ActivityAction, ClickAction, HandlerAction, BundleAction, KeyboardAction {
    public static final int RESULT_ERROR = -2;
    private SparseArray<OnActivityCallback> mActivityCallbacks;

    public interface OnActivityCallback {
        void onActivityResult(int i, Intent intent);
    }

    public /* synthetic */ Activity getActivity() {
        return ActivityAction.CC.$default$getActivity(this);
    }

    public /* synthetic */ boolean getBoolean(String str) {
        return BundleAction.CC.$default$getBoolean(this, str);
    }

    public /* synthetic */ boolean getBoolean(String str, boolean z) {
        return BundleAction.CC.$default$getBoolean(this, str, z);
    }

    public Context getContext() {
        return this;
    }

    public /* synthetic */ double getDouble(String str) {
        return BundleAction.CC.$default$getDouble(this, str);
    }

    public /* synthetic */ double getDouble(String str, int i) {
        return BundleAction.CC.$default$getDouble(this, str, i);
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

    public /* synthetic */ void hideKeyboard(View view) {
        KeyboardAction.CC.$default$hideKeyboard(this, view);
    }

    /* access modifiers changed from: protected */
    public abstract void initData();

    /* access modifiers changed from: protected */
    public abstract void initView();

    public /* synthetic */ void onClick(View view) {
        ClickAction.CC.$default$onClick(this, view);
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

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initActivity();
    }

    /* access modifiers changed from: protected */
    public void initActivity() {
        initLayout();
        initView();
        initData();
    }

    /* access modifiers changed from: protected */
    public void initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            initSoftKeyboard();
        }
    }

    /* access modifiers changed from: protected */
    public void initSoftKeyboard() {
        getContentView().setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BaseActivity.this.lambda$initSoftKeyboard$0$BaseActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initSoftKeyboard$0$BaseActivity(View view) {
        hideKeyboard(getCurrentFocus());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        removeCallbacks();
    }

    public void finish() {
        super.finish();
        hideKeyboard(getCurrentFocus());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    public ViewGroup getContentView() {
        return (ViewGroup) findViewById(16908290);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        for (Fragment next : getSupportFragmentManager().getFragments()) {
            if ((next instanceof BaseFragment) && next.getLifecycle().getCurrentState() == Lifecycle.State.RESUMED && ((BaseFragment) next).dispatchKeyEvent(keyEvent)) {
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        hideKeyboard(getCurrentFocus());
        super.startActivityForResult(intent, i, bundle);
    }

    public void startActivityForResult(Class<? extends Activity> cls, OnActivityCallback onActivityCallback) {
        startActivityForResult(new Intent(this, cls), (Bundle) null, onActivityCallback);
    }

    public void startActivityForResult(Intent intent, OnActivityCallback onActivityCallback) {
        startActivityForResult(intent, (Bundle) null, onActivityCallback);
    }

    public void startActivityForResult(Intent intent, Bundle bundle, OnActivityCallback onActivityCallback) {
        if (this.mActivityCallbacks == null) {
            this.mActivityCallbacks = new SparseArray<>(1);
        }
        int nextInt = new Random().nextInt((int) Math.pow(2.0d, 16.0d));
        this.mActivityCallbacks.put(nextInt, onActivityCallback);
        startActivityForResult(intent, nextInt, bundle);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        OnActivityCallback onActivityCallback;
        SparseArray<OnActivityCallback> sparseArray = this.mActivityCallbacks;
        if (sparseArray == null || (onActivityCallback = sparseArray.get(i)) == null) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        onActivityCallback.onActivityResult(i2, intent);
        this.mActivityCallbacks.remove(i);
    }
}
