package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.TransferPreferences;

public final class zzbte extends zzbgl implements TransferPreferences {
    public static final Parcelable.Creator<zzbte> CREATOR = new zzbtf();
    private boolean zzgrf;
    private int zzgrg;
    private int zzgwt;

    zzbte(int i, int i2, boolean z) {
        this.zzgwt = i;
        this.zzgrg = i2;
        this.zzgrf = z;
    }

    public final int getBatteryUsagePreference() {
        return this.zzgrg;
    }

    public final int getNetworkPreference() {
        return this.zzgwt;
    }

    public final boolean isRoamingAllowed() {
        return this.zzgrf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgwt);
        zzbgo.zzc(parcel, 3, this.zzgrg);
        zzbgo.zza(parcel, 4, this.zzgrf);
        zzbgo.zzai(parcel, zze);
    }
}
