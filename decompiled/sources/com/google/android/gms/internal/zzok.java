package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.ads.internal.zzbt;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@zzabh
public final class zzok {
    private Context mContext = null;
    private String zzavp = null;
    private boolean zzbwc;
    private String zzbwd;
    private Map<String, String> zzbwe;

    public zzok(Context context, String str) {
        this.mContext = context;
        this.zzavp = str;
        this.zzbwc = ((Boolean) zzlc.zzio().zzd(zzoi.zzbne)).booleanValue();
        this.zzbwd = (String) zzlc.zzio().zzd(zzoi.zzbnf);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzbwe = linkedHashMap;
        linkedHashMap.put("s", "gmob_sdk");
        this.zzbwe.put("v", "3");
        this.zzbwe.put("os", Build.VERSION.RELEASE);
        this.zzbwe.put("sdk", Build.VERSION.SDK);
        Map<String, String> map = this.zzbwe;
        zzbt.zzel();
        map.put("device", zzaij.zzrd());
        this.zzbwe.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        Map<String, String> map2 = this.zzbwe;
        zzbt.zzel();
        map2.put("is_lite_sdk", zzaij.zzas(context) ? "1" : "0");
        Future<zzaea> zzo = zzbt.zzew().zzo(this.mContext);
        try {
            zzo.get();
            this.zzbwe.put("network_coarse", Integer.toString(zzo.get().zzcyo));
            this.zzbwe.put("network_fine", Integer.toString(zzo.get().zzcyp));
        } catch (Exception e) {
            zzbt.zzep().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* access modifiers changed from: package-private */
    public final Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public final String zzfw() {
        return this.zzavp;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzjh() {
        return this.zzbwc;
    }

    /* access modifiers changed from: package-private */
    public final String zzji() {
        return this.zzbwd;
    }

    /* access modifiers changed from: package-private */
    public final Map<String, String> zzjj() {
        return this.zzbwe;
    }
}
