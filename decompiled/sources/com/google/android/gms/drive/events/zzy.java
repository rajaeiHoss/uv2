package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzy implements Parcelable.Creator<zzx> {
    public final /* synthetic */ zzx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<DriveSpace> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, DriveSpace.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzx(arrayList);
    }

    public final /* synthetic */ zzx[] newArray(int i) {
        return new zzx[i];
    }
}
