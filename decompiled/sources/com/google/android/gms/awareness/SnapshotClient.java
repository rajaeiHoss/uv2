package com.google.android.gms.awareness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.awareness.snapshot.BeaconStateResponse;
import com.google.android.gms.awareness.snapshot.DetectedActivityResponse;
import com.google.android.gms.awareness.snapshot.HeadphoneStateResponse;
import com.google.android.gms.awareness.snapshot.LocationResponse;
import com.google.android.gms.awareness.snapshot.PlacesResponse;
import com.google.android.gms.awareness.snapshot.TimeIntervalsResponse;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.tasks.Task;
import java.util.Collection;

public class SnapshotClient extends GoogleApi<AwarenessOptions> {
    SnapshotClient(Activity activity, AwarenessOptions awarenessOptions) {
        super(activity, Awareness.API, null, (zzda) new zzg());
    }

    SnapshotClient(Context context, AwarenessOptions awarenessOptions) {
        super(context, Awareness.API, null, (zzda) new zzg());
    }

    public Task<BeaconStateResponse> getBeaconState(Collection<BeaconState.TypeFilter> collection) {
        return zzbj.zza(Awareness.SnapshotApi.getBeaconState(zzahw(), collection), new BeaconStateResponse());
    }

    public Task<BeaconStateResponse> getBeaconState(BeaconState.TypeFilter... typeFilterArr) {
        return zzbj.zza(Awareness.SnapshotApi.getBeaconState(zzahw(), typeFilterArr), new BeaconStateResponse());
    }

    public Task<DetectedActivityResponse> getDetectedActivity() {
        return zzbj.zza(Awareness.SnapshotApi.getDetectedActivity(zzahw()), new DetectedActivityResponse());
    }

    public Task<HeadphoneStateResponse> getHeadphoneState() {
        return zzbj.zza(Awareness.SnapshotApi.getHeadphoneState(zzahw()), new HeadphoneStateResponse());
    }

    public Task<LocationResponse> getLocation() {
        return zzbj.zza(Awareness.SnapshotApi.getLocation(zzahw()), new LocationResponse());
    }

    public Task<PlacesResponse> getPlaces() {
        return zzbj.zza(Awareness.SnapshotApi.getPlaces(zzahw()), new PlacesResponse());
    }

    public Task<TimeIntervalsResponse> getTimeIntervals() {
        return zzbj.zza(Awareness.SnapshotApi.getTimeIntervals(zzahw()), new TimeIntervalsResponse());
    }

    public Task<WeatherResponse> getWeather() {
        return zzbj.zza(Awareness.SnapshotApi.getWeather(zzahw()), new WeatherResponse());
    }
}
