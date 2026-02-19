package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbso extends zzbgl {
    public static final Parcelable.Creator<zzbso> CREATOR = new zzbsp();
    final MetadataBundle zzgtf;

    public zzbso(MetadataBundle metadataBundle) {
        this.zzgtf = metadataBundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgtf, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final MetadataBundle zzaqs() {
        return this.zzgtf;
    }
}
