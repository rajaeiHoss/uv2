package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzaqq extends zzi<zzaqq> {
    public int zzcly;
    public int zzclz;
    private String zzdxa;
    public int zzdxb;
    public int zzdxc;
    public int zzdxd;

    public final String getLanguage() {
        return this.zzdxa;
    }

    public final void setLanguage(String str) {
        this.zzdxa = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("language", this.zzdxa);
        hashMap.put("screenColors", Integer.valueOf(this.zzdxb));
        hashMap.put("screenWidth", Integer.valueOf(this.zzcly));
        hashMap.put("screenHeight", Integer.valueOf(this.zzclz));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzdxc));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzdxd));
        return zzl(hashMap);
    }

    @Override
    public final void zzb(zzaqq zzaqq) {
        int i = this.zzdxb;
        if (i != 0) {
            zzaqq.zzdxb = i;
        }
        int i2 = this.zzcly;
        if (i2 != 0) {
            zzaqq.zzcly = i2;
        }
        int i3 = this.zzclz;
        if (i3 != 0) {
            zzaqq.zzclz = i3;
        }
        int i4 = this.zzdxc;
        if (i4 != 0) {
            zzaqq.zzdxc = i4;
        }
        int i5 = this.zzdxd;
        if (i5 != 0) {
            zzaqq.zzdxd = i5;
        }
        if (!TextUtils.isEmpty(this.zzdxa)) {
            zzaqq.zzdxa = this.zzdxa;
        }
    }
}
