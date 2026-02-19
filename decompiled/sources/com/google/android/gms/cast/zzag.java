package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzag implements Parcelable.Creator<MediaMetadata> {
    public final /* synthetic */ MediaMetadata createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<WebImage> arrayList = null;
        Bundle bundle = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                arrayList = zzbgm.zzc(parcel, readInt, WebImage.CREATOR);
            } else if (i2 == 3) {
                bundle = zzbgm.zzs(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new MediaMetadata(arrayList, bundle, i);
    }

    public final /* synthetic */ MediaMetadata[] newArray(int i) {
        return new MediaMetadata[i];
    }
}
