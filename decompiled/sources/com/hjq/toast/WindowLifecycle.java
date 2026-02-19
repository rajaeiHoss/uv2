package com.hjq.toast;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

final class WindowLifecycle implements Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private ToastImpl mToastImpl;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    WindowLifecycle(Activity activity) {
        this.mActivity = activity;
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.mActivity;
    }

    public void onActivityPaused(Activity activity) {
        ToastImpl toastImpl;
        if (this.mActivity == activity && (toastImpl = this.mToastImpl) != null) {
            toastImpl.cancel();
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.mActivity == activity) {
            ToastImpl toastImpl = this.mToastImpl;
            if (toastImpl != null) {
                toastImpl.cancel();
            }
            unregister();
            this.mActivity = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void register(ToastImpl toastImpl) {
        this.mToastImpl = toastImpl;
        if (this.mActivity != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mActivity.registerActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unregister() {
        this.mToastImpl = null;
        if (this.mActivity != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mActivity.unregisterActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
        }
    }
}
