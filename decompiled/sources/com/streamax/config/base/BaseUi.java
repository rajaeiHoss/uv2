package com.streamax.config.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.FragmentActivity;
import com.hjq.toast.ToastUtils;
import com.streamax.utils.AppProxy;
import com.streamax.utils.StringUtils;

public abstract class BaseUi extends FragmentActivity {
    protected Context mContext;
    /* access modifiers changed from: protected */
    public Handler mHandler;
    /* access modifiers changed from: protected */
    public Resources mResources;

    /* access modifiers changed from: protected */
    public void init() {
    }

    /* access modifiers changed from: protected */
    public abstract void initData();

    /* access modifiers changed from: protected */
    public abstract void initEvent();

    /* access modifiers changed from: protected */
    public abstract void initView();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = AppProxy.getContext();
        this.mHandler = AppProxy.getHandler();
        this.mResources = AppProxy.getResources();
        init();
        initView();
        initData();
        initEvent();
    }

    /* access modifiers changed from: protected */
    public void toastSf(int i) {
        ToastUtils.show((CharSequence) StringUtils.getString(Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void toastSf(String str) {
        ToastUtils.show((CharSequence) str);
    }
}
