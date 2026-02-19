package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzj extends zzbgl {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    private int versionCode;
    @Deprecated
    private ClientAppContext zzkcy;
    private int zzkcz;

    public zzj(int i) {
        this(1, (ClientAppContext) null, i);
    }

    zzj(int i, ClientAppContext clientAppContext, int i2) {
        this.versionCode = i;
        this.zzkcy = clientAppContext;
        this.zzkcz = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzkcy, i, false);
        zzbgo.zzc(parcel, 3, this.zzkcz);
        zzbgo.zzai(parcel, zze);
    }
}
