package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.zzbxp;
import com.google.android.gms.internal.zzcaw;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class RecordingClient extends GoogleApi<FitnessOptions> {
    private static final RecordingApi zzhhp = new zzcaw();

    RecordingClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbxp.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    RecordingClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbxp.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<List<Subscription>> listSubscriptions() {
        return zzbj.zza(zzhhp.listSubscriptions(zzahw()), zzk.zzgui);
    }

    public Task<List<Subscription>> listSubscriptions(DataType dataType) {
        return zzbj.zza(zzhhp.listSubscriptions(zzahw(), dataType), zzl.zzgui);
    }

    public Task<Void> subscribe(DataSource dataSource) {
        return zzbj.zzb(zzhhp.subscribe(zzahw(), dataSource));
    }

    public Task<Void> subscribe(DataType dataType) {
        return zzbj.zzb(zzhhp.subscribe(zzahw(), dataType));
    }

    public Task<Void> unsubscribe(DataSource dataSource) {
        return zzbj.zzb(zzhhp.unsubscribe(zzahw(), dataSource));
    }

    public Task<Void> unsubscribe(DataType dataType) {
        return zzbj.zzb(zzhhp.unsubscribe(zzahw(), dataType));
    }

    public Task<Void> unsubscribe(Subscription subscription) {
        return zzbj.zzb(zzhhp.unsubscribe(zzahw(), subscription));
    }
}
