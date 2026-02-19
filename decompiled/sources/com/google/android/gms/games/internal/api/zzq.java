package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class zzq implements Events {
    public final void increment(GoogleApiClient googleApiClient, String str, int i) {
        GamesClientImpl zzb = Games.zzb(googleApiClient, false);
        if (zzb != null) {
            if (zzb.isConnected()) {
                zzb.zzp(str, i);
            } else {
                googleApiClient.zze(new zzt(this, googleApiClient, str, i));
            }
        }
    }

    public final PendingResult<Events.LoadEventsResult> load(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.zzd(new zzs(this, googleApiClient, z));
    }

    public final PendingResult<Events.LoadEventsResult> loadByIds(GoogleApiClient googleApiClient, boolean z, String... strArr) {
        return googleApiClient.zzd(new zzr(this, googleApiClient, z, strArr));
    }
}
