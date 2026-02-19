package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.zza;
import java.util.ArrayList;

public final class zzbrw implements Parcelable.Creator<zzbrv> {
    public final zzbrv createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataHolder dataHolder = null;
        ArrayList<DriveId> arrayList = null;
        zza zza = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                dataHolder = (DataHolder) zzbgm.zza(parcel, readInt, DataHolder.CREATOR);
            } else if (i == 3) {
                arrayList = zzbgm.zzc(parcel, readInt, DriveId.CREATOR);
            } else if (i == 4) {
                zza = (zza) zzbgm.zza(parcel, readInt, zza.CREATOR);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbrv(dataHolder, arrayList, zza, z);
    }

    public final zzbrv[] newArray(int i) {
        return new zzbrv[i];
    }
}
