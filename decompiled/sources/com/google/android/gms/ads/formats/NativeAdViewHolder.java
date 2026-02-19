package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzrb;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class NativeAdViewHolder {
    public static WeakHashMap<View, NativeAdViewHolder> zzamn = new WeakHashMap<>();
    private zzrb zzamm;
    private WeakReference<View> zzamo;

    public NativeAdViewHolder(View view, Map<String, View> map, Map<String, View> map2) {
        zzbq.checkNotNull(view, "ContainerView must not be null");
        if (view instanceof NativeAdView) {
            zzaky.e("The provided containerView is of type NativeAdView. NativeAdView objects should not be used with NativeAdViewHolder.");
        } else if (zzamn.get(view) != null) {
            zzaky.e("The provided containerView is already in use with another NativeAdViewHolder.");
        } else {
            zzamn.put(view, this);
            this.zzamo = new WeakReference<>(view);
            this.zzamm = zzlc.zzik().zza(view, zzd(map), zzd(map2));
        }
    }

    private static HashMap<String, View> zzd(Map<String, View> map) {
        return map == null ? new HashMap<>() : new HashMap<>(map);
    }

    public final void setNativeAd(NativeAd nativeAd) {
        WeakReference<View> weakReference = this.zzamo;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null) {
            zzaky.zzcz("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        if (!zzamn.containsKey(view)) {
            zzamn.put(view, this);
        }
        zzrb zzrb = this.zzamm;
        if (zzrb != null) {
            try {
                zzrb.zza((IObjectWrapper) nativeAd.zzbi());
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public final void unregisterNativeAd() {
        zzrb zzrb = this.zzamm;
        if (zzrb != null) {
            try {
                zzrb.unregisterNativeAd();
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call unregisterNativeAd on delegate", e);
            }
        }
        WeakReference<View> weakReference = this.zzamo;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null) {
            zzamn.remove(view);
        }
    }
}
