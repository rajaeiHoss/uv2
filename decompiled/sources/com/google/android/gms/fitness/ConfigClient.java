package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.internal.zzbwy;
import com.google.android.gms.internal.zzcae;
import com.google.android.gms.tasks.Task;

public class ConfigClient extends GoogleApi<FitnessOptions> {
    private static final ConfigApi zzhhe = new zzcae();

    ConfigClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbwy.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    ConfigClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbwy.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<DataType> createCustomDataType(DataTypeCreateRequest dataTypeCreateRequest) {
        return zzbj.zza(zzhhe.createCustomDataType(zzahw(), dataTypeCreateRequest), zzd.zzgui);
    }

    public Task<Void> disableFit() {
        return zzbj.zzb(zzhhe.disableFit(zzahw()));
    }

    public Task<DataType> readDataType(String str) {
        return zzbj.zza(zzhhe.readDataType(zzahw(), str), zze.zzgui);
    }
}
