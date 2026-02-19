package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzmw;

class BaseAdView extends ViewGroup {
    protected final zzmw zzalw;

    public BaseAdView(Context context, int i) {
        super(context);
        this.zzalw = new zzmw(this, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zzalw = new zzmw(this, attributeSet, false, i);
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zzalw = new zzmw(this, attributeSet, false, i2);
    }

    public void destroy() {
        this.zzalw.destroy();
    }

    public AdListener getAdListener() {
        return this.zzalw.getAdListener();
    }

    public AdSize getAdSize() {
        return this.zzalw.getAdSize();
    }

    public String getAdUnitId() {
        return this.zzalw.getAdUnitId();
    }

    public String getMediationAdapterClassName() {
        return this.zzalw.getMediationAdapterClassName();
    }

    public boolean isLoading() {
        return this.zzalw.isLoading();
    }

    public void loadAd(AdRequest adRequest) {
        this.zzalw.zza(adRequest.zzbe());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize = null;
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzaky.zzb("Unable to retrieve ad size.", e);
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
                i4 = widthInPixels;
            } else {
                i3 = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            i4 = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public void pause() {
        this.zzalw.pause();
    }

    public void resume() {
        this.zzalw.resume();
    }

    public void setAdListener(AdListener adListener) {
        this.zzalw.setAdListener(adListener);
        if (adListener == null) {
            this.zzalw.zza((zzkf) null);
            this.zzalw.setAppEventListener((AppEventListener) null);
            return;
        }
        if (adListener instanceof zzkf) {
            this.zzalw.zza((zzkf) adListener);
        }
        if (adListener instanceof AppEventListener) {
            this.zzalw.setAppEventListener((AppEventListener) adListener);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zzalw.setAdSizes(adSize);
    }

    public void setAdUnitId(String str) {
        this.zzalw.setAdUnitId(str);
    }
}
