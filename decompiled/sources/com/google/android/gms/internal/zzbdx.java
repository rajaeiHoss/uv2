package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import java.util.Arrays;

public final class zzbdx extends zzbgl {
    public static final Parcelable.Creator<zzbdx> CREATOR = new zzbdy();
    private double zzexh;
    private boolean zzexi;
    private int zzfmk;
    private int zzfml;
    private ApplicationMetadata zzfmv;

    public zzbdx() {
        this(Double.NaN, false, -1, (ApplicationMetadata) null, -1);
    }

    zzbdx(double d, boolean z, int i, ApplicationMetadata applicationMetadata, int i2) {
        this.zzexh = d;
        this.zzexi = z;
        this.zzfmk = i;
        this.zzfmv = applicationMetadata;
        this.zzfml = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbdx)) {
            return false;
        }
        zzbdx zzbdx = (zzbdx) obj;
        return this.zzexh == zzbdx.zzexh && this.zzexi == zzbdx.zzexi && this.zzfmk == zzbdx.zzfmk && zzbdw.zza(this.zzfmv, zzbdx.zzfmv) && this.zzfml == zzbdx.zzfml;
    }

    public final int getActiveInputState() {
        return this.zzfmk;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzfmv;
    }

    public final int getStandbyState() {
        return this.zzfml;
    }

    public final double getVolume() {
        return this.zzexh;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Double.valueOf(this.zzexh), Boolean.valueOf(this.zzexi), Integer.valueOf(this.zzfmk), this.zzfmv, Integer.valueOf(this.zzfml)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzexh);
        zzbgo.zza(parcel, 3, this.zzexi);
        zzbgo.zzc(parcel, 4, this.zzfmk);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzfmv, i, false);
        zzbgo.zzc(parcel, 6, this.zzfml);
        zzbgo.zzai(parcel, zze);
    }

    public final boolean zzagw() {
        return this.zzexi;
    }
}
