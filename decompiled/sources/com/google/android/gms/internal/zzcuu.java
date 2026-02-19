package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcuu extends zzbgl {
    public static final Parcelable.Creator<zzcuu> CREATOR = new zzcuv();
    private final int zzehz;
    private final ParcelUuid zzkbu;
    private final ParcelUuid zzkbv;
    private final ParcelUuid zzkbw;
    private final byte[] zzkbx;
    private final byte[] zzkby;
    private final int zzkbz;
    private final byte[] zzkca;
    private final byte[] zzkcb;

    zzcuu(int i, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        this.zzehz = i;
        this.zzkbu = parcelUuid;
        this.zzkbv = parcelUuid2;
        this.zzkbw = parcelUuid3;
        this.zzkbx = bArr;
        this.zzkby = bArr2;
        this.zzkbz = i2;
        this.zzkca = bArr3;
        this.zzkcb = bArr4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzcuu zzcuu = (zzcuu) obj;
            return this.zzkbz == zzcuu.zzkbz && Arrays.equals(this.zzkca, zzcuu.zzkca) && Arrays.equals(this.zzkcb, zzcuu.zzkcb) && zzbg.equal(this.zzkbw, zzcuu.zzkbw) && Arrays.equals(this.zzkbx, zzcuu.zzkbx) && Arrays.equals(this.zzkby, zzcuu.zzkby) && zzbg.equal(this.zzkbu, zzcuu.zzkbu) && zzbg.equal(this.zzkbv, zzcuu.zzkbv);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzkbz), Integer.valueOf(Arrays.hashCode(this.zzkca)), Integer.valueOf(Arrays.hashCode(this.zzkcb)), this.zzkbw, Integer.valueOf(Arrays.hashCode(this.zzkbx)), Integer.valueOf(Arrays.hashCode(this.zzkby)), this.zzkbu, this.zzkbv});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzkbu, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzkbv, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzkbw, i, false);
        zzbgo.zza(parcel, 7, this.zzkbx, false);
        zzbgo.zza(parcel, 8, this.zzkby, false);
        zzbgo.zzc(parcel, 9, this.zzkbz);
        zzbgo.zza(parcel, 10, this.zzkca, false);
        zzbgo.zza(parcel, 11, this.zzkcb, false);
        zzbgo.zzai(parcel, zze);
    }
}
