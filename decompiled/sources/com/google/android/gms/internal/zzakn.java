package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import com.google.android.gms.ads.internal.zzbt;

@zzabh
public final class zzakn {
    private final View mView;
    private Activity zzdid;
    private boolean zzdie;
    private boolean zzdif;
    private boolean zzdig;
    private ViewTreeObserver.OnGlobalLayoutListener zzdih;
    private ViewTreeObserver.OnScrollChangedListener zzdii;

    public zzakn(Activity activity, View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        this.zzdid = activity;
        this.mView = view;
        this.zzdih = onGlobalLayoutListener;
        this.zzdii = onScrollChangedListener;
    }

    private static ViewTreeObserver zzj(Activity activity) {
        Window window;
        View decorView;
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return null;
        }
        return decorView.getViewTreeObserver();
    }

    private final void zzrx() {
        ViewTreeObserver zzj;
        ViewTreeObserver zzj2;
        if (!this.zzdie) {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzdih;
            if (onGlobalLayoutListener != null) {
                Activity activity = this.zzdid;
                if (!(activity == null || (zzj2 = zzj(activity)) == null)) {
                    zzj2.addOnGlobalLayoutListener(onGlobalLayoutListener);
                }
                zzbt.zzfg();
                zzaml.zza(this.mView, this.zzdih);
            }
            ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = this.zzdii;
            if (onScrollChangedListener != null) {
                Activity activity2 = this.zzdid;
                if (!(activity2 == null || (zzj = zzj(activity2)) == null)) {
                    zzj.addOnScrollChangedListener(onScrollChangedListener);
                }
                zzbt.zzfg();
                zzaml.zza(this.mView, this.zzdii);
            }
            this.zzdie = true;
        }
    }

    private final void zzry() {
        ViewTreeObserver zzj;
        ViewTreeObserver zzj2;
        Activity activity = this.zzdid;
        if (activity != null && this.zzdie) {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = this.zzdih;
            if (!(onGlobalLayoutListener == null || (zzj2 = zzj(activity)) == null)) {
                zzbt.zzen().zza(zzj2, onGlobalLayoutListener);
            }
            ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = this.zzdii;
            if (!(onScrollChangedListener == null || (zzj = zzj(this.zzdid)) == null)) {
                zzj.removeOnScrollChangedListener(onScrollChangedListener);
            }
            this.zzdie = false;
        }
    }

    public final void onAttachedToWindow() {
        this.zzdif = true;
        if (this.zzdig) {
            zzrx();
        }
    }

    public final void onDetachedFromWindow() {
        this.zzdif = false;
        zzry();
    }

    public final void zzi(Activity activity) {
        this.zzdid = activity;
    }

    public final void zzrv() {
        this.zzdig = true;
        if (this.zzdif) {
            zzrx();
        }
    }

    public final void zzrw() {
        this.zzdig = false;
        zzry();
    }
}
