package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class zzay implements Notifications {
    public final void clear(GoogleApiClient googleApiClient, int i) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzdu(i);
        }
    }

    public final void clearAll(GoogleApiClient googleApiClient) {
        clear(googleApiClient, 63);
    }
}
