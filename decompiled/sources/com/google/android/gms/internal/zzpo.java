package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zzabh
public final class zzpo extends zzrp implements zzpx {
    private final Object mLock = new Object();
    private final zzph zzbyd;
    private zzmm zzbye;
    private View zzbyf;
    /* access modifiers changed from: private */
    public zzpv zzbyi;
    private final String zzbyn;
    private final SimpleArrayMap<String, zzpj> zzbyo;
    private final SimpleArrayMap<String, String> zzbyp;

    public zzpo(String str, SimpleArrayMap<String, zzpj> simpleArrayMap, SimpleArrayMap<String, String> simpleArrayMap2, zzph zzph, zzmm zzmm, View view) {
        this.zzbyn = str;
        this.zzbyo = simpleArrayMap;
        this.zzbyp = simpleArrayMap2;
        this.zzbyd = zzph;
        this.zzbye = zzmm;
        this.zzbyf = view;
    }

    public final void destroy() {
        zzaij.zzdfn.post(new zzpq(this));
        this.zzbye = null;
        this.zzbyf = null;
    }

    public final List<String> getAvailableAssetNames() {
        String[] strArr = new String[(this.zzbyo.size() + this.zzbyp.size())];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.zzbyo.size()) {
            strArr[i3] = this.zzbyo.keyAt(i2);
            i2++;
            i3++;
        }
        while (i < this.zzbyp.size()) {
            strArr[i3] = this.zzbyp.keyAt(i);
            i++;
            i3++;
        }
        return Arrays.asList(strArr);
    }

    public final String getCustomTemplateId() {
        return this.zzbyn;
    }

    public final zzmm getVideoController() {
        return this.zzbye;
    }

    public final void performClick(String str) {
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyi;
            if (zzpv == null) {
                zzaky.e("Attempt to call performClick before ad initialized.");
            } else {
                zzpv.zza((View) null, str, (Bundle) null, (Map<String, WeakReference<View>>) null, (View) null);
            }
        }
    }

    public final void recordImpression() {
        synchronized (this.mLock) {
            zzpv zzpv = this.zzbyi;
            if (zzpv == null) {
                zzaky.e("Attempt to perform recordImpression before ad initialized.");
            } else {
                zzpv.zza((View) null, (Map<String, WeakReference<View>>) null);
            }
        }
    }

    public final String zzap(String str) {
        return this.zzbyp.get(str);
    }

    public final zzqs zzaq(String str) {
        return this.zzbyo.get(str);
    }

    public final void zzb(zzpv zzpv) {
        synchronized (this.mLock) {
            this.zzbyi = zzpv;
        }
    }

    public final boolean zzf(IObjectWrapper iObjectWrapper) {
        if (this.zzbyi == null) {
            zzaky.e("Attempt to call renderVideoInMediaView before ad initialized.");
            return false;
        } else if (this.zzbyf == null) {
            return false;
        } else {
            zzpp zzpp = new zzpp(this);
            this.zzbyi.zza((View) (FrameLayout) zzn.zzy(iObjectWrapper), (zzpt) zzpp);
            return true;
        }
    }

    public final IObjectWrapper zzkd() {
        return zzn.zzz(this.zzbyi);
    }

    public final String zzke() {
        return "3";
    }

    public final zzph zzkf() {
        return this.zzbyd;
    }

    public final View zzkg() {
        return this.zzbyf;
    }

    public final IObjectWrapper zzkk() {
        return zzn.zzz(this.zzbyi.getContext().getApplicationContext());
    }
}
