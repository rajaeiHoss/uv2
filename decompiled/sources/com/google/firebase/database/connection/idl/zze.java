package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.List;

public final class zze implements Parcelable.Creator<zzc> {
    public final zzc createFromParcel(Parcel parcel) {
        int end = zzbgm.zzd(parcel);
        zzi zzi = null;
        int i = 0;
        List<String> list = null;
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < end) {
            int readInt = parcel.readInt();
            int fieldId = 65535 & readInt;
            if (fieldId == 2) {
                zzi = (zzi) zzbgm.zza(parcel, readInt, zzi.CREATOR);
            } else if (fieldId == 3) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (fieldId == 4) {
                list = zzbgm.zzac(parcel, readInt);
            } else if (fieldId == 5) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (fieldId == 6) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 7) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 8) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else {
                zzbgm.zzb(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, end);
        return new zzc(zzi, i, list, z, str, str2, str3);
    }

    public final zzc[] newArray(int i) {
        return new zzc[i];
    }
}
