package com.google.android.gms.awareness.fence;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbkg;
import java.util.Collection;

public abstract class FenceQueryRequest extends zzbgl {
    protected FenceQueryRequest() {
    }

    public static FenceQueryRequest all() {
        return new zzbkg();
    }

    public static FenceQueryRequest forFences(Collection<String> collection) {
        zzbq.checkNotNull(collection);
        for (String zzgv : collection) {
            zzbq.zzgv(zzgv);
        }
        return new zzbkg(collection);
    }

    public static FenceQueryRequest forFences(String... strArr) {
        zzbq.checkNotNull(strArr);
        for (String zzgv : strArr) {
            zzbq.zzgv(zzgv);
        }
        return new zzbkg(strArr);
    }
}
