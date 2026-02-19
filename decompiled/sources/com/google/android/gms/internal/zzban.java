package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.R;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzi;

public final class zzban extends RelativeLayout implements IntroductoryOverlay {
    /* access modifiers changed from: private */
    public Activity mActivity;
    private int mColor;
    private View zzfax;
    private String zzfaz;
    /* access modifiers changed from: private */
    public IntroductoryOverlay.OnOverlayDismissedListener zzfba;
    private final boolean zzfbp;
    /* access modifiers changed from: private */
    public com.google.android.gms.cast.framework.internal.featurehighlight.zza zzfbq;
    /* access modifiers changed from: private */
    public boolean zzfbr;

    public zzban(IntroductoryOverlay.Builder builder) {
        super(builder.getActivity());
        this.mActivity = builder.getActivity();
        this.zzfbp = builder.zzaep();
        this.zzfba = builder.zzaen();
        this.zzfax = builder.zzaem();
        this.zzfaz = builder.zzaeq();
        this.mColor = builder.zzaeo();
    }

    /* access modifiers changed from: private */
    public final void reset() {
        removeAllViews();
        this.mActivity = null;
        this.zzfba = null;
        this.zzfax = null;
        this.zzfbq = null;
        this.zzfaz = null;
        this.mColor = 0;
        this.zzfbr = false;
    }

    static boolean zzbz(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        reset();
        super.onDetachedFromWindow();
    }

    public final void remove() {
        if (this.zzfbr) {
            ((ViewGroup) this.mActivity.getWindow().getDecorView()).removeView(this);
            reset();
        }
    }

    public final void show() {
        Activity activity = this.mActivity;
        if (activity != null && this.zzfax != null && !this.zzfbr && !zzbz(activity)) {
            if (!this.zzfbp || !IntroductoryOverlay.zza.zzbx(this.mActivity)) {
                com.google.android.gms.cast.framework.internal.featurehighlight.zza zza = new com.google.android.gms.cast.framework.internal.featurehighlight.zza(this.mActivity);
                this.zzfbq = zza;
                int i = this.mColor;
                if (i != 0) {
                    zza.zzbg(i);
                }
                addView(this.zzfbq);
                zzi zzi = (zzi) this.mActivity.getLayoutInflater().inflate(R.layout.cast_help_text, this.zzfbq, false);
                zzi.setText(this.zzfaz, (CharSequence) null);
                this.zzfbq.zza(zzi);
                this.zzfbq.zza(this.zzfax, (View) null, true, new zzbao(this));
                this.zzfbr = true;
                ((ViewGroup) this.mActivity.getWindow().getDecorView()).addView(this);
                this.zzfbq.zze((Runnable) null);
                return;
            }
            reset();
        }
    }
}
