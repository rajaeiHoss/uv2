package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.drive.TransferPreferences;

@Deprecated
public final class zzbre extends zzbgl implements FileUploadPreferences {
    public static final Parcelable.Creator<zzbre> CREATOR = new zzbrf();
    private int zzgrg;
    private int zzgwt;
    private boolean zzgwu;

    public zzbre(int i, int i2, boolean z) {
        this.zzgwt = i;
        this.zzgrg = i2;
        this.zzgwu = z;
    }

    public zzbre(TransferPreferences transferPreferences) {
        this(transferPreferences.getNetworkPreference(), transferPreferences.getBatteryUsagePreference(), transferPreferences.isRoamingAllowed());
    }

    private static boolean zzcx(int i) {
        return i == 1 || i == 2;
    }

    private static boolean zzcy(int i) {
        return i == 256 || i == 257;
    }

    public final int getBatteryUsagePreference() {
        if (!zzcy(this.zzgrg)) {
            return 0;
        }
        return this.zzgrg;
    }

    public final int getNetworkTypePreference() {
        if (!zzcx(this.zzgwt)) {
            return 0;
        }
        return this.zzgwt;
    }

    public final boolean isRoamingAllowed() {
        return this.zzgwu;
    }

    public final void setBatteryUsagePreference(int i) {
        if (zzcy(i)) {
            this.zzgrg = i;
            return;
        }
        throw new IllegalArgumentException("Invalid battery usage preference value.");
    }

    public final void setNetworkTypePreference(int i) {
        if (zzcx(i)) {
            this.zzgwt = i;
            return;
        }
        throw new IllegalArgumentException("Invalid data connection preference value.");
    }

    public final void setRoamingAllowed(boolean z) {
        this.zzgwu = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgwt);
        zzbgo.zzc(parcel, 3, this.zzgrg);
        zzbgo.zza(parcel, 4, this.zzgwu);
        zzbgo.zzai(parcel, zze);
    }
}
