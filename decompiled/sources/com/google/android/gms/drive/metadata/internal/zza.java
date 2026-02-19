package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zza implements Parcelable.Creator<AppVisibleCustomProperties> {
    public final /* synthetic */ AppVisibleCustomProperties createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzc> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzc.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AppVisibleCustomProperties(arrayList);
    }

    public final /* synthetic */ AppVisibleCustomProperties[] newArray(int i) {
        return new AppVisibleCustomProperties[i];
    }
}
