package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zze;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzdk {
    public static final Status zzgbq = new Status(8, "The connection to Google Play services was lost");
    private static final BasePendingResult<?>[] zzgbr = new BasePendingResult[0];
    private final Map<Api.zzc<?>, Api.zze> zzfyj;
    final Set<BasePendingResult<?>> zzgbs = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzdn zzgbt = new zzdl(this);

    public zzdk(Map<Api.zzc<?>, Api.zze> map) {
        this.zzfyj = map;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.android.gms.common.api.internal.zzdn, com.google.android.gms.common.api.ResultCallback, com.google.android.gms.common.api.zze, com.google.android.gms.common.api.internal.zzdl] */
    public final void release() {
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zzgbs.toArray(zzgbr)) {
            zze zze = null;
            basePendingResult.zza((zzdn) null);
            if (basePendingResult.zzaid() != null) {
                basePendingResult.setResultCallback((ResultCallback) null);
                IBinder zzaho = this.zzfyj.get(((zzm) basePendingResult).zzahm()).zzaho();
                if (basePendingResult.isReady()) {
                    basePendingResult.zza((zzdn) new zzdm(basePendingResult, zze, zzaho, (zzdl) null));
                } else {
                    if (zzaho == null || !zzaho.isBinderAlive()) {
                        basePendingResult.zza((zzdn) null);
                    } else {
                        zzdm zzdm = new zzdm(basePendingResult, zze, zzaho, (zzdl) null);
                        basePendingResult.zza((zzdn) zzdm);
                        try {
                            zzaho.linkToDeath(zzdm, 0);
                        } catch (RemoteException unused) {
                        }
                    }
                    basePendingResult.cancel();
                    if (zze != null) {
                        zze.remove(basePendingResult.zzaid().intValue());
                    }
                }
            } else if (!basePendingResult.zzaip()) {
            }
            this.zzgbs.remove(basePendingResult);
        }
    }

    public final void zzald() {
        for (BasePendingResult zzv : (BasePendingResult[]) this.zzgbs.toArray(zzgbr)) {
            zzv.zzv(zzgbq);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(BasePendingResult<? extends Result> basePendingResult) {
        this.zzgbs.add(basePendingResult);
        basePendingResult.zza(this.zzgbt);
    }
}
