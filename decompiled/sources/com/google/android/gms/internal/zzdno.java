package com.google.android.gms.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.collection.SimpleArrayMap;

public class zzdno extends AnimatorListenerAdapter {
    private SimpleArrayMap<Animator, Boolean> zzlxr = new SimpleArrayMap<>();

    public void onAnimationCancel(Animator animator) {
        this.zzlxr.put(animator, true);
    }

    public void onAnimationStart(Animator animator) {
        this.zzlxr.put(animator, false);
    }

    /* access modifiers changed from: protected */
    public final boolean zzb(Animator animator) {
        return this.zzlxr.containsKey(animator) && this.zzlxr.get(animator).booleanValue();
    }
}
