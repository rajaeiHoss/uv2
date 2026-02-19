package com.hjq.http.lifecycle;

import android.app.Service;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public abstract class LifecycleService extends Service implements LifecycleOwner {
    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public void onCreate() {
        super.onCreate();
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }
}
