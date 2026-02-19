package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbq;
import java.util.HashMap;
import java.util.Set;

public final class zzccw {
    private static final String[] zzids = {"requestId", "outcome"};
    private final int zzcc;
    private final HashMap<String, Integer> zzidt;

    zzccw(int i, HashMap<String, Integer> hashMap) {
        this.zzcc = i;
        this.zzidt = hashMap;
    }

    public static zzccw zzbj(DataHolder dataHolder) {
        zzccy zzccy = new zzccy();
        zzccy.zzdx(dataHolder.getStatusCode());
        int count = dataHolder.getCount();
        for (int i = 0; i < count; i++) {
            int zzby = dataHolder.zzby(i);
            zzccy.zzw(dataHolder.zzd("requestId", i, zzby), dataHolder.zzc("outcome", i, zzby));
        }
        return zzccy.zzavo();
    }

    public final Set<String> getRequestIds() {
        return this.zzidt.keySet();
    }

    public final int getRequestOutcome(String str) {
        boolean containsKey = this.zzidt.containsKey(str);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46);
        sb.append("Request ");
        sb.append(str);
        sb.append(" was not part of the update operation!");
        zzbq.checkArgument(containsKey, sb.toString());
        return this.zzidt.get(str).intValue();
    }
}
