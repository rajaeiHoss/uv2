package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.games.internal.zza;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbh extends zza {
    private /* synthetic */ TaskCompletionSource zzeuo;

    zzbh(zzbg zzbg, TaskCompletionSource taskCompletionSource) {
        this.zzeuo = taskCompletionSource;
    }

    public final void onLeftRoom(int i, String str) {
        zzdf.zza(GamesClientStatusCodes.zzdg(GamesClientStatusCodes.zzdh(i)), str, this.zzeuo);
    }
}
