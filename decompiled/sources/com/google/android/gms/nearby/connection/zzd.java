package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzd implements Parcelable.Creator<AppMetadata> {
    public final /* synthetic */ AppMetadata createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<AppIdentifier> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, AppIdentifier.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AppMetadata(arrayList);
    }

    public final /* synthetic */ AppMetadata[] newArray(int i) {
        return new AppMetadata[i];
    }
}
