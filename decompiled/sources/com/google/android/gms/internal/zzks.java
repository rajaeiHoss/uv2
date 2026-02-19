package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashMap;

@zzabh
public class zzks {
    private final Object mLock = new Object();
    private zzmb zzbih;
    /* access modifiers changed from: private */
    public final zzkj zzbii;
    /* access modifiers changed from: private */
    public final zzki zzbij;
    /* access modifiers changed from: private */
    public final zznc zzbik;
    /* access modifiers changed from: private */
    public final zzso zzbil;
    /* access modifiers changed from: private */
    public final zzafg zzbim;
    /* access modifiers changed from: private */
    public final zzyp zzbin;
    /* access modifiers changed from: private */
    public final zzsp zzbio;

    abstract class zza<T> {
        zza() {
        }

        /* access modifiers changed from: protected */
        public abstract T zza(zzmb zzmb) throws RemoteException;

        /* access modifiers changed from: protected */
        public abstract T zzif() throws RemoteException;

        /* access modifiers changed from: protected */
        public final T zzig() {
            zzmb zza = zzks.this.zzie();
            if (zza == null) {
                zzaky.zzcz("ClientApi class cannot be loaded.");
                return null;
            }
            try {
                return zza(zza);
            } catch (RemoteException e) {
                zzaky.zzc("Cannot invoke local loader using ClientApi class", e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public final T zzih() {
            try {
                return zzif();
            } catch (RemoteException e) {
                zzaky.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzks(zzkj zzkj, zzki zzki, zznc zznc, zzso zzso, zzafg zzafg, zzyp zzyp, zzsp zzsp) {
        this.zzbii = zzkj;
        this.zzbij = zzki;
        this.zzbik = zznc;
        this.zzbil = zzso;
        this.zzbim = zzafg;
        this.zzbin = zzyp;
        this.zzbio = zzsp;
    }

    static <T> T zza(Context context, boolean z, zza<T> zza2) {
        boolean z2 = true;
        if (!z) {
            zzlc.zzij();
            if (!zzako.zzbb(context)) {
                zzaky.zzby("Google Play Services is not available");
                z = true;
            }
        }
        zzlc.zzij();
        int zzbd = zzako.zzbd(context);
        zzlc.zzij();
        if (zzbd <= zzako.zzbc(context)) {
            z2 = z;
        }
        if (z2) {
            T zzig = zza2.zzig();
            return zzig == null ? zza2.zzih() : zzig;
        }
        T zzih = zza2.zzih();
        return zzih == null ? zza2.zzig() : zzih;
    }

    /* access modifiers changed from: private */
    public static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzlc.zzij().zza(context, (String) null, "gmob-apps", bundle, true);
    }

    private static zzmb zzid() {
        try {
            Object newInstance = zzks.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzmc.asInterface((IBinder) newInstance);
            }
            zzaky.zzcz("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Exception e) {
            zzaky.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final zzmb zzie() {
        zzmb zzmb;
        synchronized (this.mLock) {
            if (this.zzbih == null) {
                this.zzbih = zzid();
            }
            zzmb = this.zzbih;
        }
        return zzmb;
    }

    public final zzqw zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzqw) zza(context, false, new zzky(this, frameLayout, frameLayout2, context));
    }

    public final zzrb zza(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        return (zzrb) zza(view.getContext(), false, new zzkz(this, view, hashMap, hashMap2));
    }

    public final zzlo zzb(Context context, String str, zzwf zzwf) {
        return (zzlo) zza(context, false, new zzkw(this, context, str, zzwf));
    }

    public final zzyq zzb(Activity activity) {
        Intent intent = activity.getIntent();
        boolean z = false;
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzaky.e("useClientJar flag not found in activity intent extras.");
        } else {
            z = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        return (zzyq) zza((Context) activity, z, new zzlb(this, activity));
    }
}
