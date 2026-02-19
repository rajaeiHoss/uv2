package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzf implements Parcelable.Creator<zze> {
    public final /* synthetic */ zze createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ArrayList<DriveSpace> arrayList = null;
        int i2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, DriveSpace.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zze(i, i2, z, arrayList);
    }

    public final /* synthetic */ zze[] newArray(int i) {
        return new zze[i];
    }
}
