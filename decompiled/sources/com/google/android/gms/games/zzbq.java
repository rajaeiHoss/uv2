package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzbq implements Continuation<String, Task<Boolean>> {
    private /* synthetic */ zzci zzhut;
    private /* synthetic */ RealTimeMultiplayerClient zzhuu;

    zzbq(RealTimeMultiplayerClient realTimeMultiplayerClient, zzci zzci) {
        this.zzhuu = realTimeMultiplayerClient;
        this.zzhut = zzci;
    }

    public final Task<Boolean> then(Task<String> task) throws Exception {
        return this.zzhuu.zza((zzck<?>) this.zzhut.zzakx());
    }
}
