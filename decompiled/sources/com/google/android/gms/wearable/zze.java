package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<Asset> {
    public final Asset createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        byte[] bArr = null;
        String str = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        Uri uri = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                parcelFileDescriptor = (ParcelFileDescriptor) zzbgm.zza(parcel, readInt, ParcelFileDescriptor.CREATOR);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Asset(bArr, str, parcelFileDescriptor, uri);
    }

    public final Asset[] newArray(int i) {
        return new Asset[i];
    }
}
