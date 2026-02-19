package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BleDevicesResult extends zzbgl implements Result {
    public static final Parcelable.Creator<BleDevicesResult> CREATOR = new zza();
    private final Status zzefs;
    private final List<BleDevice> zzhpu;

    public BleDevicesResult(List<BleDevice> list, Status status) {
        this.zzhpu = Collections.unmodifiableList(list);
        this.zzefs = status;
    }

    public static BleDevicesResult zzae(Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BleDevicesResult) {
                BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
                if (this.zzefs.equals(bleDevicesResult.zzefs) && zzbg.equal(this.zzhpu, bleDevicesResult.zzhpu)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<BleDevice> getClaimedBleDevices() {
        return this.zzhpu;
    }

    public List<BleDevice> getClaimedBleDevices(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice next : this.zzhpu) {
            if (next.getDataTypes().contains(dataType)) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhpu});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("bleDevices", this.zzhpu).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getClaimedBleDevices(), false);
        zzbgo.zza(parcel, 2, (Parcelable) getStatus(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
