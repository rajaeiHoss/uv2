package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

public final class zzaqs extends zzi<zzaqs> {
    private String mCategory;
    private String zzdxe;
    private String zzdxf;
    private long zzdxg;

    public final String getAction() {
        return this.zzdxe;
    }

    public final String getCategory() {
        return this.mCategory;
    }

    public final String getLabel() {
        return this.zzdxf;
    }

    public final long getValue() {
        return this.zzdxg;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.mCategory);
        hashMap.put("action", this.zzdxe);
        hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, this.zzdxf);
        hashMap.put(FirebaseAnalytics.Param.VALUE, Long.valueOf(this.zzdxg));
        return zzl(hashMap);
    }

    public final void zzb(zzaqs zzaqs) {
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzaqs.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzdxe)) {
            zzaqs.zzdxe = this.zzdxe;
        }
        if (!TextUtils.isEmpty(this.zzdxf)) {
            zzaqs.zzdxf = this.zzdxf;
        }
        long j = this.zzdxg;
        if (j != 0) {
            zzaqs.zzdxg = j;
        }
    }
}
