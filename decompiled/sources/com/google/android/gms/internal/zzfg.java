package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

final class zzfg<T1 extends Result, T2 extends Result> implements ResultCallback<T2> {
    private /* synthetic */ ResultCallback<? super T1> zzalh;
    private /* synthetic */ zzff<T1, T2> zzali;

    zzfg(zzff<T1, T2> zzff, ResultCallback<? super T1> resultCallback) {
        this.zzali = zzff;
        this.zzalh = resultCallback;
    }

    public final void onResult(T2 t2) {
        this.zzalh.onResult(this.zzali.zza(t2));
    }
}
