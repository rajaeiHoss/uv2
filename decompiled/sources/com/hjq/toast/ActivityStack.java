package com.hjq.toast;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class ActivityStack implements Application.ActivityLifecycleCallbacks {
    private Activity mForegroundActivity;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    ActivityStack() {
    }

    static ActivityStack register(Application application) {
        ActivityStack activityStack = new ActivityStack();
        application.registerActivityLifecycleCallbacks(activityStack);
        return activityStack;
    }

    public Activity getForegroundActivity() {
        return this.mForegroundActivity;
    }

    public void onActivityResumed(Activity activity) {
        this.mForegroundActivity = activity;
    }

    public void onActivityPaused(Activity activity) {
        if (this.mForegroundActivity == activity) {
            this.mForegroundActivity = null;
        }
    }
}
