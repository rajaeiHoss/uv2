package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcub extends zzbgl {
    public static final Parcelable.Creator<zzcub> CREATOR = new zzcuc();
    private long id;
    private int type;
    private byte[] zzjwl;
    private ParcelFileDescriptor zzjzu;
    private String zzjzv;
    private long zzjzw;
    private ParcelFileDescriptor zzjzx;

    private zzcub() {
    }

    public zzcub(long j, int i, byte[] bArr, ParcelFileDescriptor parcelFileDescriptor, String str, long j2, ParcelFileDescriptor parcelFileDescriptor2) {
        this.id = j;
        this.type = i;
        this.zzjwl = bArr;
        this.zzjzu = parcelFileDescriptor;
        this.zzjzv = str;
        this.zzjzw = j2;
        this.zzjzx = parcelFileDescriptor2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcub) {
            zzcub zzcub = (zzcub) obj;
            return zzbg.equal(Long.valueOf(this.id), Long.valueOf(zzcub.id)) && zzbg.equal(Integer.valueOf(this.type), Integer.valueOf(zzcub.type)) && Arrays.equals(this.zzjwl, zzcub.zzjwl) && zzbg.equal(this.zzjzu, zzcub.zzjzu) && zzbg.equal(this.zzjzv, zzcub.zzjzv) && zzbg.equal(Long.valueOf(this.zzjzw), Long.valueOf(zzcub.zzjzw)) && zzbg.equal(this.zzjzx, zzcub.zzjzx);
        }
        return false;
    }

    public final byte[] getBytes() {
        return this.zzjwl;
    }

    public final long getId() {
        return this.id;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.id), Integer.valueOf(this.type), Integer.valueOf(Arrays.hashCode(this.zzjwl)), this.zzjzu, this.zzjzv, Long.valueOf(this.zzjzw), this.zzjzx});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.id);
        zzbgo.zzc(parcel, 2, this.type);
        zzbgo.zza(parcel, 3, this.zzjwl, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzjzu, i, false);
        zzbgo.zza(parcel, 5, this.zzjzv, false);
        zzbgo.zza(parcel, 6, this.zzjzw);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzjzx, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final ParcelFileDescriptor zzbdn() {
        return this.zzjzu;
    }

    public final String zzbdo() {
        return this.zzjzv;
    }

    public final long zzbdp() {
        return this.zzjzw;
    }
}
