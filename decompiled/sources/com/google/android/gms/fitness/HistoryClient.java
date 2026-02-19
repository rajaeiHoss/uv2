package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.internal.zzbxj;
import com.google.android.gms.internal.zzcam;
import com.google.android.gms.tasks.Task;

public class HistoryClient extends GoogleApi<FitnessOptions> {
    private static final HistoryApi zzhho = new zzcam();

    HistoryClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbxj.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    HistoryClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbxj.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<Void> deleteData(DataDeleteRequest dataDeleteRequest) {
        return zzbj.zzb(zzhho.deleteData(zzahw(), dataDeleteRequest));
    }

    public Task<Void> insertData(DataSet dataSet) {
        return zzbj.zzb(zzhho.insertData(zzahw(), dataSet));
    }

    public Task<DataSet> readDailyTotal(DataType dataType) {
        return zzbj.zza(zzhho.readDailyTotal(zzahw(), dataType), zzi.zzgui);
    }

    public Task<DataSet> readDailyTotalFromLocalDevice(DataType dataType) {
        return zzbj.zza(zzhho.readDailyTotalFromLocalDevice(zzahw(), dataType), zzj.zzgui);
    }

    public Task<DataReadResponse> readData(DataReadRequest dataReadRequest) {
        return zzbj.zza(zzhho.readData(zzahw(), dataReadRequest), new DataReadResponse());
    }

    public Task<Void> registerDataUpdateListener(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return zzbj.zzb(zzhho.registerDataUpdateListener(zzahw(), dataUpdateListenerRegistrationRequest));
    }

    public Task<Void> unregisterDataUpdateListener(PendingIntent pendingIntent) {
        return zzbj.zzb(zzhho.unregisterDataUpdateListener(zzahw(), pendingIntent));
    }

    public Task<Void> updateData(DataUpdateRequest dataUpdateRequest) {
        return zzbj.zzb(zzhho.updateData(zzahw(), dataUpdateRequest));
    }
}
