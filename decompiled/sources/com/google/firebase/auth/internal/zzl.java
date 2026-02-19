package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzebw;
import com.google.firebase.auth.zzd;
import java.util.List;

public final class zzl implements Parcelable.Creator<zzk> {
    public final zzk createFromParcel(Parcel parcel) {
        int end = zzbgm.zzd(parcel);
        zzebw zzebw = null;
        zzh zzh = null;
        String str = null;
        String str2 = null;
        List<zzh> list = null;
        List<String> list2 = null;
        String str3 = null;
        boolean z = false;
        zzm zzm = null;
        boolean z2 = false;
        zzd zzd = null;
        while (parcel.dataPosition() < end) {
            int readInt = parcel.readInt();
            int fieldId = 65535 & readInt;
            if (fieldId == 1) {
                zzebw = (zzebw) zzbgm.zza(parcel, readInt, zzebw.CREATOR);
            } else if (fieldId == 2) {
                zzh = (zzh) zzbgm.zza(parcel, readInt, zzh.CREATOR);
            } else if (fieldId == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 4) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 5) {
                list = zzbgm.zzc(parcel, readInt, zzh.CREATOR);
            } else if (fieldId == 6) {
                list2 = zzbgm.zzac(parcel, readInt);
            } else if (fieldId == 7) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (fieldId == 8) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (fieldId == 9) {
                zzm = (zzm) zzbgm.zza(parcel, readInt, zzm.CREATOR);
            } else if (fieldId == 10) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (fieldId == 11) {
                zzd = (zzd) zzbgm.zza(parcel, readInt, zzd.CREATOR);
            } else {
                zzbgm.zzb(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, end);
        return new zzk(zzebw, zzh, str, str2, list, list2, str3, z, zzm, z2, zzd);
    }

    public final zzk[] newArray(int i) {
        return new zzk[i];
    }
}
