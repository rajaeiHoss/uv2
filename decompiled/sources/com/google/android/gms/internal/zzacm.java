package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzacm implements Parcelable.Creator<zzacl> {
    public final zzacl createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        ArrayList<String> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzac(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzacl(z, arrayList);
    }

    public final zzacl[] newArray(int i) {
        return new zzacl[i];
    }
}
