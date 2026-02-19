package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class BleDevice extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<BleDevice> CREATOR = new zzd();
    private final String address;
    private final String name;
    private final List<String> zzhhy;
    private final List<DataType> zzhhz;

    BleDevice(String str, String str2, List<String> list, List<DataType> list2) {
        this.address = str;
        this.name = str2;
        this.zzhhy = Collections.unmodifiableList(list);
        this.zzhhz = Collections.unmodifiableList(list2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BleDevice)) {
            return false;
        }
        BleDevice bleDevice = (BleDevice) obj;
        return this.name.equals(bleDevice.name) && this.address.equals(bleDevice.address) && new HashSet(this.zzhhy).equals(new HashSet(bleDevice.zzhhy)) && new HashSet(this.zzhhz).equals(new HashSet(bleDevice.zzhhz));
    }

    public String getAddress() {
        return this.address;
    }

    public List<DataType> getDataTypes() {
        return this.zzhhz;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getSupportedProfiles() {
        return this.zzhhy;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, this.address, this.zzhhy, this.zzhhz});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("name", this.name).zzg("address", this.address).zzg("dataTypes", this.zzhhz).zzg("supportedProfiles", this.zzhhy).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getAddress(), false);
        zzbgo.zza(parcel, 2, getName(), false);
        zzbgo.zzb(parcel, 3, getSupportedProfiles(), false);
        zzbgo.zzc(parcel, 4, getDataTypes(), false);
        zzbgo.zzai(parcel, zze);
    }
}
