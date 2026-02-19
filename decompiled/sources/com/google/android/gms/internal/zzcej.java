package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.instantapps.zzd;
import com.google.android.gms.instantapps.zze;

public final class zzcej implements com.google.android.gms.instantapps.zzc {
    public final PendingResult<zze> zza(GoogleApiClient googleApiClient, String str) {
        zzbq.checkNotNull(googleApiClient);
        zzbq.checkNotNull(str);
        return googleApiClient.zzd(new zzcek(this, googleApiClient, str));
    }

    public final PendingResult<zzd> zzh(GoogleApiClient googleApiClient) {
        zzbq.checkNotNull(googleApiClient);
        return googleApiClient.zzd(new zzcem(this, googleApiClient));
    }
}
