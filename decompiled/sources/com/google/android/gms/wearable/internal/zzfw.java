package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzfw extends zzbgl {
    public static final Parcelable.Creator<zzfw> CREATOR = new zzfx();
    private int zzehz;
    private zzem zzlsa;

    zzfw(int i, IBinder iBinder) {
        this.zzehz = i;
        zzem zzem = null;
        if (iBinder != null) {
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
                zzem = queryLocalInterface instanceof zzem ? (zzem) queryLocalInterface : new zzeo(iBinder);
            }
            this.zzlsa = zzem;
            return;
        }
        this.zzlsa = null;
    }

    public zzfw(zzem zzem) {
        this.zzehz = 1;
        this.zzlsa = zzem;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzem zzem = this.zzlsa;
        zzbgo.zza(parcel, 2, zzem == null ? null : zzem.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
