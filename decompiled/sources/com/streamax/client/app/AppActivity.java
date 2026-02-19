package com.streamax.client.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.hjq.base.BaseActivity;
import com.hjq.base.BaseDialog;
import com.hjq.http.listener.OnHttpListener;
import com.streamax.client.action.TitleBarAction;
import com.streamax.client.action.ToastAction;
import com.streamax.client.http.model.HttpData;
import com.streamax.client.ui.dialog.WaitDialog;
import com.zycs.UView.R;
import okhttp3.Call;

public abstract class AppActivity extends BaseActivity implements ToastAction, TitleBarAction, OnHttpListener<Object> {
    private BaseDialog mDialog;
    private int mDialogCount;
    private ImmersionBar mImmersionBar;
    private TitleBar mTitleBar;

    public /* synthetic */ Drawable getLeftIcon() {
        return TitleBarAction.CC.$default$getLeftIcon(this);
    }

    public /* synthetic */ CharSequence getLeftTitle() {
        return TitleBarAction.CC.$default$getLeftTitle(this);
    }

    public /* synthetic */ Drawable getRightIcon() {
        return TitleBarAction.CC.$default$getRightIcon(this);
    }

    public /* synthetic */ CharSequence getRightTitle() {
        return TitleBarAction.CC.$default$getRightTitle(this);
    }

    /* access modifiers changed from: protected */
    public boolean isStatusBarDarkFont() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isStatusBarEnabled() {
        return true;
    }

    public /* synthetic */ TitleBar obtainTitleBar(ViewGroup viewGroup) {
        return TitleBarAction.CC.$default$obtainTitleBar(this, viewGroup);
    }

    public /* synthetic */ void onRightClick(View view) {
        TitleBarAction.CC.$default$onRightClick(this, view);
    }

    public /* synthetic */ void onSucceed(Object obj, boolean z) {
        OnHttpListener.CC.$default$onSucceed(this, obj, z);
    }

    public /* synthetic */ void onTitleClick(View view) {
        TitleBarAction.CC.$default$onTitleClick(this, view);
    }

    public /* synthetic */ void setLeftIcon(int i) {
        TitleBarAction.CC.$default$setLeftIcon((TitleBarAction) this, i);
    }

    public /* synthetic */ void setLeftIcon(Drawable drawable) {
        TitleBarAction.CC.$default$setLeftIcon((TitleBarAction) this, drawable);
    }

    public /* synthetic */ void setLeftTitle(int i) {
        TitleBarAction.CC.$default$setLeftTitle((TitleBarAction) this, i);
    }

    public /* synthetic */ void setLeftTitle(CharSequence charSequence) {
        TitleBarAction.CC.$default$setLeftTitle((TitleBarAction) this, charSequence);
    }

    public /* synthetic */ void setRightIcon(int i) {
        TitleBarAction.CC.$default$setRightIcon((TitleBarAction) this, i);
    }

    public /* synthetic */ void setRightIcon(Drawable drawable) {
        TitleBarAction.CC.$default$setRightIcon((TitleBarAction) this, drawable);
    }

    public /* synthetic */ void setRightTitle(int i) {
        TitleBarAction.CC.$default$setRightTitle((TitleBarAction) this, i);
    }

    public /* synthetic */ void setRightTitle(CharSequence charSequence) {
        TitleBarAction.CC.$default$setRightTitle((TitleBarAction) this, charSequence);
    }

    public /* synthetic */ void toast(int i) {
        ToastAction.CC.$default$toast((ToastAction) this, i);
    }

    public /* synthetic */ void toast(CharSequence charSequence) {
        ToastAction.CC.$default$toast((ToastAction) this, charSequence);
    }

    public /* synthetic */ void toast(Object obj) {
        ToastAction.CC.$default$toast((ToastAction) this, obj);
    }

    public boolean isShowDialog() {
        BaseDialog baseDialog = this.mDialog;
        return baseDialog != null && baseDialog.isShowing();
    }

    public void showDialog() {
        if (!isFinishing() && !isDestroyed()) {
            this.mDialogCount++;
            postDelayed(new Runnable() {
                public final void run() {
                    AppActivity.this.lambda$showDialog$0$AppActivity();
                }
            }, 300);
        }
    }

    public /* synthetic */ void lambda$showDialog$0$AppActivity() {
        if (this.mDialogCount > 0 && !isFinishing() && !isDestroyed()) {
            if (this.mDialog == null) {
                this.mDialog = ((WaitDialog.Builder) new WaitDialog.Builder(this).setCancelable(false)).create();
            }
            if (!this.mDialog.isShowing()) {
                this.mDialog.show();
            }
        }
    }

    public void hideDialog() {
        BaseDialog baseDialog;
        if (!isFinishing() && !isDestroyed()) {
            int i = this.mDialogCount;
            if (i > 0) {
                this.mDialogCount = i - 1;
            }
            if (this.mDialogCount == 0 && (baseDialog = this.mDialog) != null && baseDialog.isShowing()) {
                this.mDialog.dismiss();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initLayout() {
        super.initLayout();
        if (getTitleBar() != null) {
            getTitleBar().setOnTitleBarListener(this);
        }
        if (isStatusBarEnabled()) {
            getStatusBarConfig().init();
            if (getTitleBar() != null) {
                ImmersionBar.setTitleBar((Activity) this, getTitleBar());
            }
        }
    }

    public ImmersionBar getStatusBarConfig() {
        if (this.mImmersionBar == null) {
            this.mImmersionBar = createStatusBarConfig();
        }
        return this.mImmersionBar;
    }

    /* access modifiers changed from: protected */
    public ImmersionBar createStatusBarConfig() {
        return ImmersionBar.with((Activity) this).statusBarDarkFont(isStatusBarDarkFont()).navigationBarColor((int) R.color.white).autoDarkModeEnable(true, 0.2f);
    }

    public void setTitle(int i) {
        setTitle((CharSequence) getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        if (getTitleBar() != null) {
            getTitleBar().setTitle(charSequence);
        }
    }

    public TitleBar getTitleBar() {
        if (this.mTitleBar == null) {
            this.mTitleBar = obtainTitleBar(getContentView());
        }
        return this.mTitleBar;
    }

    public void onLeftClick(View view) {
        onBackPressed();
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
        overridePendingTransition(R.anim.right_in_activity, R.anim.right_out_activity);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }

    public void onStart(Call call) {
        showDialog();
    }

    public void onSucceed(Object obj) {
        if (obj instanceof HttpData) {
            toast((CharSequence) ((HttpData) obj).getMessage());
        }
    }

    public void onFail(Exception exc) {
        toast((CharSequence) exc.getMessage());
    }

    public void onEnd(Call call) {
        hideDialog();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (isShowDialog()) {
            hideDialog();
        }
        this.mDialog = null;
    }
}
