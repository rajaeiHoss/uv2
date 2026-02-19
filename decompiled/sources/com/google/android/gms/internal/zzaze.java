package com.google.android.gms.internal;

import com.google.android.gms.awareness.SnapshotApi;
import com.google.android.gms.awareness.snapshot.BeaconStateResult;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.awareness.snapshot.HeadphoneStateResult;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.awareness.snapshot.PlacesResult;
import com.google.android.gms.awareness.snapshot.TimeIntervalsResult;
import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.GamesActivityResultCodes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class zzaze implements SnapshotApi {
    private final PendingResult<BeaconStateResult> zza(GoogleApiClient googleApiClient, ArrayList<zzayq> arrayList) {
        return new zzazt(this, googleApiClient.zzd(new zzazi(googleApiClient, GamesActivityResultCodes.RESULT_LICENSE_FAILED, arrayList)));
    }

    private static zzbld zza(GoogleApiClient googleApiClient, int i) {
        return new zzazh(googleApiClient, i);
    }

    public final PendingResult<BeaconStateResult> getBeaconState(GoogleApiClient googleApiClient, Collection<BeaconState.TypeFilter> collection) {
        zzbq.checkNotNull(collection, "beaconTypes cannot be null");
        zzbq.checkArgument(collection.size() > 0, "beaconTypes must not be empty");
        ArrayList arrayList = new ArrayList();
        Iterator<BeaconState.TypeFilter> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add((zzayq) it.next());
        }
        return zza(googleApiClient, (ArrayList<zzayq>) arrayList);
    }

    public final PendingResult<BeaconStateResult> getBeaconState(GoogleApiClient googleApiClient, BeaconState.TypeFilter... typeFilterArr) {
        zzbq.checkNotNull(typeFilterArr, "beaconTypes cannot be null");
        zzbq.checkArgument(typeFilterArr.length > 0, "beaconTypes must not be empty");
        ArrayList arrayList = new ArrayList();
        for (BeaconState.TypeFilter typeFilter : typeFilterArr) {
            arrayList.add((zzayq) typeFilter);
        }
        return zza(googleApiClient, (ArrayList<zzayq>) arrayList);
    }

    public final PendingResult<DetectedActivityResult> getDetectedActivity(GoogleApiClient googleApiClient) {
        return new zzazj(this, googleApiClient.zzd(zza(googleApiClient, (int) GamesActivityResultCodes.RESULT_SIGN_IN_FAILED)));
    }

    public final PendingResult<HeadphoneStateResult> getHeadphoneState(GoogleApiClient googleApiClient) {
        return new zzazl(this, googleApiClient.zzd(zza(googleApiClient, (int) GamesActivityResultCodes.RESULT_APP_MISCONFIGURED)));
    }

    public final PendingResult<LocationResult> getLocation(GoogleApiClient googleApiClient) {
        return new zzazn(this, googleApiClient.zzd(zza(googleApiClient, (int) GamesActivityResultCodes.RESULT_LEFT_ROOM)));
    }

    public final PendingResult<PlacesResult> getPlaces(GoogleApiClient googleApiClient) {
        return new zzazp(this, googleApiClient.zzd(zza(googleApiClient, (int) GamesActivityResultCodes.RESULT_NETWORK_FAILURE)));
    }

    public final PendingResult<TimeIntervalsResult> getTimeIntervals(GoogleApiClient googleApiClient) {
        return new zzazf(this, googleApiClient.zzd(zza(googleApiClient, (int) GamesActivityResultCodes.RESULT_INVALID_ROOM)));
    }

    public final PendingResult<WeatherResult> getWeather(GoogleApiClient googleApiClient) {
        return new zzazr(this, googleApiClient.zzd(zza(googleApiClient, (int) GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED)));
    }
}
