package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzs implements Parcelable.Creator<zzr> {
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzx zzx = null;
        ArrayList<FilterHolder> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                zzx = (zzx) zzbgm.zza(parcel, readInt, zzx.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, FilterHolder.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzr(zzx, (List<FilterHolder>) arrayList);
    }

    public final /* synthetic */ zzr[] newArray(int i) {
        return new zzr[i];
    }
}
