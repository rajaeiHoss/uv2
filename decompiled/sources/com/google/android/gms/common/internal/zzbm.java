package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

final class zzbm<R extends Result, T extends Response<R>> implements zzbo<R, T> {
    private /* synthetic */ T zzghu;

    zzbm(T response) {
        this.zzghu = response;
    }

    public final T zzb(R result) {
        this.zzghu.setResult(result);
        return this.zzghu;
    }
}
