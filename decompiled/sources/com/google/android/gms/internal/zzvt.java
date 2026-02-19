package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzvt implements zzvx {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final zzwf zzanu;
    private final zzqh zzapm;
    private final List<String> zzapn;
    private final zzala zzapq;
    private zzkk zzarx;
    private final zzko zzasd;
    private final boolean zzavr;
    /* access modifiers changed from: private */
    public final String zzcip;
    private final long zzciq;
    private final zzvq zzcir;
    private final zzvp zzcis;
    private final List<String> zzcit;
    private final List<String> zzciu;
    private final boolean zzciv;
    private final boolean zzciw;
    /* access modifiers changed from: private */
    public zzwi zzcix;
    /* access modifiers changed from: private */
    public int zzciy = -2;
    private zzwo zzciz;

    public zzvt(Context context, String str, zzwf zzwf, zzvq zzvq, zzvp zzvp, zzkk zzkk, zzko zzko, zzala zzala, boolean z, boolean z2, zzqh zzqh, List<String> list, List<String> list2, List<String> list3, boolean z3) {
        String str2 = str;
        zzvq zzvq2 = zzvq;
        zzvp zzvp2 = zzvp;
        this.mContext = context;
        this.zzanu = zzwf;
        this.zzcis = zzvp2;
        this.zzcip = "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str) ? zzmi() : str2;
        this.zzcir = zzvq2;
        this.zzciq = zzvp2.zzchu != -1 ? zzvp2.zzchu : zzvq2.zzchu != -1 ? zzvq2.zzchu : NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        this.zzarx = zzkk;
        this.zzasd = zzko;
        this.zzapq = zzala;
        this.zzavr = z;
        this.zzciv = z2;
        this.zzapm = zzqh;
        this.zzapn = list;
        this.zzcit = list2;
        this.zzciu = list3;
        this.zzciw = z3;
    }

    private static zzwi zza(MediationAdapter mediationAdapter) {
        return new zzxc(mediationAdapter);
    }

    /* access modifiers changed from: private */
    public final void zza(zzvs zzvs) {
        String zzbe = zzbe(this.zzcis.zzchk);
        try {
            if (this.zzapq.zzdja >= 4100000) {
                if (!this.zzavr) {
                    if (!this.zzcis.zzmh()) {
                        if (this.zzasd.zzbib) {
                            this.zzcix.zza(zzn.zzz(this.mContext), this.zzarx, zzbe, this.zzcis.zzchb, (zzwl) zzvs);
                            return;
                        } else if (!this.zzciv) {
                            this.zzcix.zza(zzn.zzz(this.mContext), this.zzasd, this.zzarx, zzbe, this.zzcis.zzchb, zzvs);
                            return;
                        } else if (this.zzcis.zzcho != null) {
                            this.zzcix.zza(zzn.zzz(this.mContext), this.zzarx, zzbe, this.zzcis.zzchb, zzvs, new zzqh(zzbf(this.zzcis.zzchs)), this.zzcis.zzchr);
                            return;
                        } else {
                            this.zzcix.zza(zzn.zzz(this.mContext), this.zzasd, this.zzarx, zzbe, this.zzcis.zzchb, zzvs);
                            return;
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(this.zzapn);
                List<String> list = this.zzcit;
                if (list != null) {
                    for (String next : list) {
                        String str = ":false";
                        List<String> list2 = this.zzciu;
                        if (list2 != null && list2.contains(next)) {
                            str = ":true";
                        }
                        StringBuilder sb = new StringBuilder(String.valueOf(next).length() + 7 + str.length());
                        sb.append("custom:");
                        sb.append(next);
                        sb.append(str);
                        arrayList.add(sb.toString());
                    }
                }
                this.zzcix.zza(zzn.zzz(this.mContext), this.zzarx, zzbe, this.zzcis.zzchb, zzvs, this.zzapm, arrayList);
            } else if (this.zzasd.zzbib) {
                this.zzcix.zza(zzn.zzz(this.mContext), this.zzarx, zzbe, zzvs);
            } else {
                this.zzcix.zza(zzn.zzz(this.mContext), this.zzasd, this.zzarx, zzbe, (zzwl) zzvs);
            }
        } catch (RemoteException e) {
            zzahw.zzc("Could not request ad from mediation adapter.", e);
            zzv(5);
        }
    }

    private final String zzbe(String str) {
        if (str != null && zzml() && !zzw(2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                return jSONObject.toString();
            } catch (JSONException unused) {
                zzahw.zzcz("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private static NativeAdOptions zzbf(String str) {
        NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            String optString = jSONObject.optString("native_image_orientation", "any");
            if ("landscape".equals(optString)) {
                i = 2;
            } else if ("portrait".equals(optString)) {
                i = 1;
            } else if (!"any".equals(optString)) {
                i = -1;
            }
            builder.setImageOrientation(i);
        } catch (JSONException e) {
            zzahw.zzc("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private final String zzmi() {
        try {
            return (TextUtils.isEmpty(this.zzcis.zzchf) || !this.zzanu.zzbh(this.zzcis.zzchf)) ? "com.google.ads.mediation.customevent.CustomEventAdapter" : "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        } catch (RemoteException unused) {
            zzahw.zzcz("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private final zzwo zzmj() {
        zzwo zzwo;
        if (this.zzciy != 0 || !zzml()) {
            return null;
        }
        try {
            if (!(!zzw(4) || (zzwo = this.zzciz) == null || zzwo.zzmn() == 0)) {
                return this.zzciz;
            }
        } catch (RemoteException unused) {
            zzahw.zzcz("Could not get cpm value from MediationResponseMetadata");
        }
        return new zzvv(zzmm());
    }

    /* access modifiers changed from: private */
    public final zzwi zzmk() {
        String valueOf = String.valueOf(this.zzcip);
        zzahw.zzcy(valueOf.length() != 0 ? "Instantiating mediation adapter: ".concat(valueOf) : new String("Instantiating mediation adapter: "));
        if (!this.zzavr && !this.zzcis.zzmh()) {
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrb)).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzcip)) {
                return zza((MediationAdapter) new AdMobAdapter());
            }
            if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrc)).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzcip)) {
                return zza((MediationAdapter) new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzcip)) {
                return new zzxc(new zzxx());
            }
        }
        try {
            return this.zzanu.zzbg(this.zzcip);
        } catch (RemoteException e) {
            String valueOf2 = String.valueOf(this.zzcip);
            zzahw.zza(valueOf2.length() != 0 ? "Could not instantiate mediation adapter: ".concat(valueOf2) : new String("Could not instantiate mediation adapter: "), e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzml() {
        return this.zzcir.zzcif != -1;
    }

    private final int zzmm() {
        if (this.zzcis.zzchk == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzcis.zzchk);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzcip)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzw(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException unused) {
            zzahw.zzcz("Could not convert to json. Returning 0");
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzw(int i) {
        try {
            Bundle zzms = this.zzavr ? this.zzcix.zzms() : this.zzasd.zzbib ? this.zzcix.getInterstitialAdapterInfo() : this.zzcix.zzmr();
            return zzms != null && (zzms.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException unused) {
            zzahw.zzcz("Could not get adapter info. Returning false");
            return false;
        }
    }

    public final void cancel() {
        synchronized (this.mLock) {
            try {
                zzwi zzwi = this.zzcix;
                if (zzwi != null) {
                    zzwi.destroy();
                }
            } catch (RemoteException e) {
                zzahw.zzc("Could not destroy mediation adapter.", e);
            }
            this.zzciy = -1;
            this.mLock.notify();
        }
    }

    public final zzvw zza(long j, long j2) {
        zzvw zzvw;
        synchronized (this.mLock) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzvs zzvs = new zzvs();
            zzaij.zzdfn.post(new zzvu(this, zzvs));
            long j3 = this.zzciq;
            while (this.zzciy == -2) {
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                long j4 = j3 - (elapsedRealtime2 - elapsedRealtime);
                long j5 = j2 - (elapsedRealtime2 - j);
                if (j4 <= 0 || j5 <= 0) {
                    zzahw.zzcy("Timed out waiting for adapter.");
                    this.zzciy = 3;
                } else {
                    try {
                        this.mLock.wait(Math.min(j4, j5));
                    } catch (InterruptedException unused) {
                        this.zzciy = 5;
                    }
                }
            }
            zzvw = new zzvw(this.zzcis, this.zzcix, this.zzcip, zzvs, this.zzciy, zzmj(), zzbt.zzes().elapsedRealtime() - elapsedRealtime);
        }
        return zzvw;
    }

    public final void zza(int i, zzwo zzwo) {
        synchronized (this.mLock) {
            this.zzciy = 0;
            this.zzciz = zzwo;
            this.mLock.notify();
        }
    }

    public final void zzv(int i) {
        synchronized (this.mLock) {
            this.zzciy = i;
            this.mLock.notify();
        }
    }
}
