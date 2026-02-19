package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.internal.zzbxv;
import com.google.android.gms.internal.zzcbd;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class SensorsClient extends GoogleApi<FitnessOptions> {
    private static final SensorsApi zzhhq = new zzcbd();

    SensorsClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbxv.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    SensorsClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbxv.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<Void> add(SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return zzbj.zzb(zzhhq.add(zzahw(), sensorRequest, pendingIntent));
    }

    public Task<Void> add(SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        zzci zza = zza(onDataPointListener, OnDataPointListener.class.getSimpleName());
        return zza(new zzn(this, zza, zza, sensorRequest), new zzo(this, zza.zzakx(), zza));
    }

    public Task<List<DataSource>> findDataSources(DataSourcesRequest dataSourcesRequest) {
        return zzbj.zza(zzhhq.findDataSources(zzahw(), dataSourcesRequest), zzm.zzgui);
    }

    public Task<Void> remove(PendingIntent pendingIntent) {
        return zzbj.zzb(zzhhq.remove(zzahw(), pendingIntent));
    }

    public Task<Boolean> remove(OnDataPointListener onDataPointListener) {
        return zza((zzck<?>) zzcm.zzb(onDataPointListener, OnDataPointListener.class.getSimpleName()));
    }
}
