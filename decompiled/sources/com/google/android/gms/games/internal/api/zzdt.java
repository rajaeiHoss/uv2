package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.video.Videos;

public final class zzdt implements Videos {
    public final PendingResult<Videos.CaptureCapabilitiesResult> getCaptureCapabilities(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzdu(this, googleApiClient));
    }

    public final Intent getCaptureOverlayIntent(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzaum();
    }

    public final PendingResult<Videos.CaptureStateResult> getCaptureState(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzdv(this, googleApiClient));
    }

    public final PendingResult<Videos.CaptureAvailableResult> isCaptureAvailable(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.zzd(new zzdw(this, googleApiClient, i));
    }

    public final boolean isCaptureSupported(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzauo();
    }

    public final void registerCaptureOverlayStateChangedListener(GoogleApiClient googleApiClient, Videos.CaptureOverlayStateListener captureOverlayStateListener) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzl(googleApiClient.zzt(captureOverlayStateListener));
        }
    }

    public final void unregisterCaptureOverlayStateChangedListener(GoogleApiClient googleApiClient) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzauq();
        }
    }
}
