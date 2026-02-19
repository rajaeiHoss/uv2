package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<WebImage> {
    public final /* synthetic */ WebImage createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 2) {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            } else if (i4 == 3) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i4 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i3 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new WebImage(i, uri, i2, i3);
    }

    public final /* synthetic */ WebImage[] newArray(int i) {
        return new WebImage[i];
    }
}
