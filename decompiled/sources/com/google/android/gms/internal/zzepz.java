package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzepz implements Parcelable.Creator<zzepx> {
    public final zzepx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Uri uri = null;
        Uri uri2 = null;
        ArrayList<zzepy> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            } else if (i == 2) {
                uri2 = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzepy.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzepx(uri, uri2, arrayList);
    }

    public final zzepx[] newArray(int i) {
        return new zzepx[i];
    }
}
