package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;

public final class zzaqt extends zzi<zzaqt> {
    public String zzdxh;
    public boolean zzdxi;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.zzdxh);
        hashMap.put(AppMeasurement.Param.FATAL, Boolean.valueOf(this.zzdxi));
        return zzl(hashMap);
    }

    @Override
    public final void zzb(zzaqt zzaqt) {
        if (!TextUtils.isEmpty(this.zzdxh)) {
            zzaqt.zzdxh = this.zzdxh;
        }
        boolean z = this.zzdxi;
        if (z) {
            zzaqt.zzdxi = z;
        }
    }
}
