package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.common.api.Releasable;
import java.lang.ref.WeakReference;
import java.util.Map;

@zzabh
public abstract class zzany implements Releasable {
    protected Context mContext;
    private String zzddt;
    private WeakReference<zzann> zzdop;

    public zzany(zzann zzann) {
        this.mContext = zzann.getContext();
        this.zzddt = zzbt.zzel().zzl(this.mContext, zzann.zztl().zzcu);
        this.zzdop = new WeakReference<>(zzann);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, Map<String, String> map) {
        zzann zzann = (zzann) this.zzdop.get();
        if (zzann != null) {
            zzann.zza(str, map);
        }
    }

    /* access modifiers changed from: private */
    public static String zzdd(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1396664534:
                if (str.equals("badUrl")) {
                    c = 0;
                    break;
                }
                break;
            case -1347010958:
                if (str.equals("inProgress")) {
                    c = 1;
                    break;
                }
                break;
            case -918817863:
                if (str.equals("downloadTimeout")) {
                    c = 2;
                    break;
                }
                break;
            case -659376217:
                if (str.equals("contentLengthMissing")) {
                    c = 3;
                    break;
                }
                break;
            case -642208130:
                if (str.equals("playerFailed")) {
                    c = 4;
                    break;
                }
                break;
            case -354048396:
                if (str.equals("sizeExceeded")) {
                    c = 5;
                    break;
                }
                break;
            case -32082395:
                if (str.equals("externalAbort")) {
                    c = 6;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    c = 7;
                    break;
                }
                break;
            case 580119100:
                if (str.equals("expireFailed")) {
                    c = 8;
                    break;
                }
                break;
            case 725497484:
                if (str.equals("noCacheDir")) {
                    c = 9;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
                return "network";
            case 5:
            case 6:
                return "policy";
            case 8:
            case 9:
                return "io";
            default:
                return "internal";
        }
    }

    public abstract void abort();

    public void release() {
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, String str2, int i) {
        zzako.zzaju.post(new zzaoa(this, str, str2, i));
    }

    public final void zza(String str, String str2, String str3, String str4) {
        zzako.zzaju.post(new zzaob(this, str, str2, str3, str4));
    }

    public abstract boolean zzdc(String str);
}
