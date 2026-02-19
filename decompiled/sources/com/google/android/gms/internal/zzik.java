package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.InputStream;

@zzabh
public final class zzik extends zzbgl {
    public static final Parcelable.Creator<zzik> CREATOR = new zzil();
    private ParcelFileDescriptor zzbaz;

    public zzik() {
        this((ParcelFileDescriptor) null);
    }

    public zzik(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzbaz = parcelFileDescriptor;
    }

    private synchronized ParcelFileDescriptor zzhl() {
        return this.zzbaz;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) zzhl(), i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final synchronized boolean zzhj() {
        return this.zzbaz != null;
    }

    public final synchronized InputStream zzhk() {
        if (this.zzbaz == null) {
            return null;
        }
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.zzbaz);
        this.zzbaz = null;
        return autoCloseInputStream;
    }
}
