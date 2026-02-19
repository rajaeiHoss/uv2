package com.hjq.toast;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import androidx.media2.subtitle.Cea708CCParser;
import com.hjq.base.action.AnimAction;
import com.hjq.toast.ToastImpl;
import com.hjq.toast.config.IToast;

final class ToastImpl {
    /* access modifiers changed from: private */
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final int LONG_DURATION_TIMEOUT = 3500;
    private static final int SHORT_DURATION_TIMEOUT = 2000;
    private final Runnable mCancelRunnable = new Runnable() {
        public void run() {
            try {
                Activity activity = ToastImpl.this.mWindowLifecycle.getActivity();
                if (activity != null) {
                    WindowManager windowManager = (WindowManager) activity.getSystemService("window");
                    if (windowManager != null) {
                        windowManager.removeViewImmediate(ToastImpl.this.mToast.getView());
                        ToastImpl.this.mWindowLifecycle.unregister();
                        ToastImpl.this.setShow(false);
                        return;
                    }
                }
                ToastImpl.this.mWindowLifecycle.unregister();
                ToastImpl.this.setShow(false);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                ToastImpl.this.mWindowLifecycle.unregister();
                ToastImpl.this.setShow(false);
                throw th;
            }
        }
    };
    /* access modifiers changed from: private */
    public final String mPackageName;
    private boolean mShow;
    private final Runnable mShowRunnable = new Runnable() {
        public void run() {
            Activity activity = ToastImpl.this.mWindowLifecycle.getActivity();
            if (activity != null && !activity.isFinishing()) {
                if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.height = -2;
                    layoutParams.width = -2;
                    layoutParams.format = -3;
                    layoutParams.windowAnimations = AnimAction.ANIM_TOAST;
                    layoutParams.flags = Cea708CCParser.Const.CODE_C1_DF0;
                    layoutParams.packageName = ToastImpl.this.mPackageName;
                    layoutParams.gravity = ToastImpl.this.mToast.getGravity();
                    layoutParams.x = ToastImpl.this.mToast.getXOffset();
                    layoutParams.y = ToastImpl.this.mToast.getYOffset();
                    layoutParams.verticalMargin = ToastImpl.this.mToast.getVerticalMargin();
                    layoutParams.horizontalMargin = ToastImpl.this.mToast.getHorizontalMargin();
                    WindowManager windowManager = (WindowManager) activity.getSystemService("window");
                    if (windowManager != null) {
                        try {
                            windowManager.addView(ToastImpl.this.mToast.getView(), layoutParams);
                            ToastImpl.HANDLER.postDelayed(new Runnable() {
                                public final void run() {
                                    ToastImpl.this.cancel();
                                }
                            }, ToastImpl.this.mToast.getDuration() == 1 ? 3500 : 2000);
                            ToastImpl.this.mWindowLifecycle.register(ToastImpl.this);
                            ToastImpl.this.setShow(true);
                        } catch (WindowManager.BadTokenException | IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public /* synthetic */ void lambda$run$0$ToastImpl$1() {
            ToastImpl.this.cancel();
        }
    };
    /* access modifiers changed from: private */
    public final IToast mToast;
    /* access modifiers changed from: private */
    public final WindowLifecycle mWindowLifecycle;

    ToastImpl(Activity activity, IToast iToast) {
        this.mToast = iToast;
        this.mPackageName = activity.getPackageName();
        this.mWindowLifecycle = new WindowLifecycle(activity);
    }

    /* access modifiers changed from: package-private */
    public boolean isShow() {
        return this.mShow;
    }

    /* access modifiers changed from: package-private */
    public void setShow(boolean z) {
        this.mShow = z;
    }

    /* access modifiers changed from: package-private */
    public void show() {
        if (!isShow()) {
            Handler handler = HANDLER;
            handler.removeCallbacks(this.mShowRunnable);
            handler.post(this.mShowRunnable);
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        if (isShow()) {
            Handler handler = HANDLER;
            handler.removeCallbacks(this.mCancelRunnable);
            handler.post(this.mCancelRunnable);
        }
    }
}
