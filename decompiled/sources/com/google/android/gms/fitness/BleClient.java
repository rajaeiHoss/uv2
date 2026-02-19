package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.internal.zzbws;
import com.google.android.gms.internal.zzbzw;
import com.google.android.gms.internal.zzcbt;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;

public class BleClient extends GoogleApi<FitnessOptions> {
    private static final BleApi zzhhb = (zzs.zzanu() ? new zzbzw() : new zzcbt());

    BleClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, zzbws.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    BleClient(Context context, FitnessOptions fitnessOptions) {
        super(context, zzbws.zzhmg, fitnessOptions, GoogleApi.zza.zzfsr);
    }

    public Task<Void> claimBleDevice(BleDevice bleDevice) {
        return zzbj.zzb(zzhhb.claimBleDevice(zzahw(), bleDevice));
    }

    public Task<Void> claimBleDevice(String str) {
        return zzbj.zzb(zzhhb.claimBleDevice(zzahw(), str));
    }

    public Task<List<BleDevice>> listClaimedBleDevices() {
        return zzbj.zza(zzhhb.listClaimedBleDevices(zzahw()), com.google.android.gms.fitness.zza.zzgui);
    }

    public Task<Void> startBleScan(List<DataType> list, int i, BleScanCallback bleScanCallback) {
        if (!zzs.zzanu()) {
            return Tasks.forException(new ApiException(zzcbt.zzhnn));
        }
        zzci zza = zza(bleScanCallback, BleScanCallback.class.getSimpleName());
        return zza(new zzb(this, zza, zza, list, i), new zzc(this, zza.zzakx(), zza));
    }

    public Task<Boolean> stopBleScan(BleScanCallback bleScanCallback) {
        return !zzs.zzanu() ? Tasks.forException(new ApiException(zzcbt.zzhnn)) : zza((zzck<?>) zzcm.zzb(bleScanCallback, BleScanCallback.class.getSimpleName()));
    }

    public Task<Void> unclaimBleDevice(BleDevice bleDevice) {
        return zzbj.zzb(zzhhb.unclaimBleDevice(zzahw(), bleDevice));
    }

    public Task<Void> unclaimBleDevice(String str) {
        return zzbj.zzb(zzhhb.unclaimBleDevice(zzahw(), str));
    }
}
