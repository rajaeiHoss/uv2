package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;

public final class zzaqx extends zzi<zzaqx> {
    public String mCategory;
    public String zzdxf;
    public String zzdxy;
    public long zzdxz;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("variableName", this.zzdxy);
        hashMap.put("timeInMillis", Long.valueOf(this.zzdxz));
        hashMap.put("category", this.mCategory);
        hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, this.zzdxf);
        return zzl(hashMap);
    }

    public final void zzb(zzaqx zzaqx) {
        if (!TextUtils.isEmpty(this.zzdxy)) {
            zzaqx.zzdxy = this.zzdxy;
        }
        long j = this.zzdxz;
        if (j != 0) {
            zzaqx.zzdxz = j;
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzaqx.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzdxf)) {
            zzaqx.zzdxf = this.zzdxf;
        }
    }
}
