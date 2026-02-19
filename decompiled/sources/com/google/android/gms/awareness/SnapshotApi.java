package com.google.android.gms.awareness;

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
import java.util.Collection;

@Deprecated
public interface SnapshotApi {
    @Deprecated
    PendingResult<BeaconStateResult> getBeaconState(GoogleApiClient googleApiClient, Collection<BeaconState.TypeFilter> collection);

    @Deprecated
    PendingResult<BeaconStateResult> getBeaconState(GoogleApiClient googleApiClient, BeaconState.TypeFilter... typeFilterArr);

    @Deprecated
    PendingResult<DetectedActivityResult> getDetectedActivity(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<HeadphoneStateResult> getHeadphoneState(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<LocationResult> getLocation(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<PlacesResult> getPlaces(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<TimeIntervalsResult> getTimeIntervals(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<WeatherResult> getWeather(GoogleApiClient googleApiClient);
}
