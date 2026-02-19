package com.hjq.http.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public final class ActivityLifecycle implements LifecycleOwner, LifecycleEventObserver, Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public ActivityLifecycle(Activity activity) {
        this.mActivity = activity;
        if (activity instanceof LifecycleOwner) {
            ((LifecycleOwner) activity).getLifecycle().addObserver(this);
        } else if (Build.VERSION.SDK_INT >= 29) {
            this.mActivity.registerActivityLifecycleCallbacks(this);
        } else {
            this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.mLifecycle.handleLifecycleEvent(event);
        if (event == Lifecycle.Event.ON_DESTROY) {
            lifecycleOwner.getLifecycle().removeObserver(this);
            this.mActivity = null;
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.mActivity == activity) {
            this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        }
    }

    public void onActivityStarted(Activity activity) {
        if (this.mActivity == activity) {
            this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.mActivity == activity) {
            this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.mActivity == activity) {
            this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.mActivity == activity) {
            this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.mActivity == activity) {
            this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            if (Build.VERSION.SDK_INT >= 29) {
                this.mActivity.unregisterActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
            this.mActivity = null;
        }
    }
}
