package com.hjq.toast;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hjq.toast.config.IToast;
import com.hjq.toast.config.IToastStrategy;
import com.hjq.toast.config.IToastStyle;
import java.lang.ref.WeakReference;

public class ToastStrategy extends Handler implements IToastStrategy {
    private static final int DELAY_TIMEOUT = 200;
    private static final int TYPE_CANCEL = 2;
    private static final int TYPE_SHOW = 1;
    private ActivityStack mActivityStack;
    private Application mApplication;
    private WeakReference<IToast> mToastReference;
    private IToastStyle<?> mToastStyle;

    public ToastStrategy() {
        super(Looper.getMainLooper());
    }

    public void registerStrategy(Application application) {
        this.mApplication = application;
        this.mActivityStack = ActivityStack.register(application);
    }

    public void bindStyle(IToastStyle<?> iToastStyle) {
        this.mToastStyle = iToastStyle;
    }

    public IToast createToast(Application application) {
        IToast iToast;
        Activity foregroundActivity = this.mActivityStack.getForegroundActivity();
        if (foregroundActivity != null) {
            iToast = new ActivityToast(foregroundActivity);
        } else if (Build.VERSION.SDK_INT == 25) {
            iToast = new SafeToast(application);
        } else {
            iToast = new SystemToast(application);
        }
        if ((iToast instanceof ActivityToast) || Build.VERSION.SDK_INT < 30 || application.getApplicationInfo().targetSdkVersion < 30) {
            iToast.setView(this.mToastStyle.createView(application));
            iToast.setGravity(this.mToastStyle.getGravity(), this.mToastStyle.getXOffset(), this.mToastStyle.getYOffset());
            iToast.setMargin(this.mToastStyle.getHorizontalMargin(), this.mToastStyle.getVerticalMargin());
        }
        return iToast;
    }

    public void showToast(CharSequence charSequence) {
        removeMessages(1);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = charSequence;
        sendMessageDelayed(obtain, 200);
    }

    public void cancelToast() {
        removeMessages(2);
        sendEmptyMessage(2);
    }

    public void handleMessage(Message message) {
        WeakReference<IToast> weakReference = this.mToastReference;
        IToast iToast = weakReference != null ? (IToast) weakReference.get() : null;
        int i = message.what;
        if (i != 1) {
            if (i == 2 && iToast != null) {
                iToast.cancel();
            }
        } else if (message.obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) message.obj;
            if (iToast != null) {
                iToast.cancel();
            }
            IToast createToast = createToast(this.mApplication);
            this.mToastReference = new WeakReference<>(createToast);
            createToast.setDuration(getToastDuration(charSequence));
            createToast.setText(charSequence);
            createToast.show();
        }
    }

    /* access modifiers changed from: protected */
    public int getToastDuration(CharSequence charSequence) {
        return charSequence.length() > 20 ? 1 : 0;
    }
}
