package com.hjq.http.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public final class ApplicationLifecycle implements LifecycleOwner {
    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }
}
