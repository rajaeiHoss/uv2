package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import java.lang.ref.WeakReference;

final class zzaq implements zzj {
    private final Api<?> zzfop;
    /* access modifiers changed from: private */
    public final boolean zzfvo;
    private final WeakReference<zzao> zzfxu;

    public zzaq(zzao zzao, Api<?> api, boolean z) {
        this.zzfxu = new WeakReference<>(zzao);
        this.zzfop = api;
        this.zzfvo = z;
    }

    public final void zzf(ConnectionResult connectionResult) {
        zzao zzao = (zzao) this.zzfxu.get();
        if (zzao != null) {
            zzbq.zza(Looper.myLooper() == zzao.zzfxd.zzfvq.getLooper(), (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zzao.zzfwa.lock();
            try {
                if (zzao.zzbs(0)) {
                    if (!connectionResult.isSuccess()) {
                        zzao.zzb(connectionResult, this.zzfop, this.zzfvo);
                    }
                    if (zzao.zzajk()) {
                        zzao.zzajl();
                    }
                }
            } finally {
                zzao.zzfwa.unlock();
            }
        }
    }
}
