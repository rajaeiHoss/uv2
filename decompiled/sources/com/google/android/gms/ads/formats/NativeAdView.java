package com.google.android.gms.ads.formats;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzqw;

public class NativeAdView extends FrameLayout {
    private final FrameLayout zzamk;
    private final zzqw zzaml = zzbj();

    public NativeAdView(Context context) {
        super(context);
        this.zzamk = zzb(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzamk = zzb(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzamk = zzb(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzamk = zzb(context);
    }

    private final FrameLayout zzb(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final zzqw zzbj() {
        zzbq.checkNotNull(this.zzamk, "createDelegate must be called after mOverlayFrame has been created");
        if (isInEditMode()) {
            return null;
        }
        return zzlc.zzik().zza(this.zzamk.getContext(), (FrameLayout) this, this.zzamk);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzamk);
    }

    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.zzamk;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    public void destroy() {
        try {
            this.zzaml.destroy();
        } catch (RemoteException e) {
            zzaky.zzb("Unable to destroy native ad view", e);
        }
    }

    public AdChoicesView getAdChoicesView() {
        View zzq = zzq(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW);
        if (zzq instanceof AdChoicesView) {
            return (AdChoicesView) zzq;
        }
        return null;
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        zzqw zzqw = this.zzaml;
        if (zzqw != null) {
            try {
                zzqw.zzb(zzn.zzz(view), i);
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzamk);
    }

    public void removeView(View view) {
        if (this.zzamk != view) {
            super.removeView(view);
        }
    }

    public void setAdChoicesView(AdChoicesView adChoicesView) {
        zza(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, adChoicesView);
    }

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zzaml.zza((IObjectWrapper) nativeAd.zzbi());
        } catch (RemoteException e) {
            zzaky.zzb("Unable to call setNativeAd on delegate", e);
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, View view) {
        try {
            this.zzaml.zzb(str, zzn.zzz(view));
        } catch (RemoteException e) {
            zzaky.zzb("Unable to call setAssetView on delegate", e);
        }
    }

    /* access modifiers changed from: protected */
    public final View zzq(String str) {
        try {
            IObjectWrapper zzal = this.zzaml.zzal(str);
            if (zzal != null) {
                return (View) zzn.zzy(zzal);
            }
            return null;
        } catch (RemoteException e) {
            zzaky.zzb("Unable to call getAssetView on delegate", e);
            return null;
        }
    }
}
