package com.google.android.gms.instantapps;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.internal.zzbgm;

public final class zzh implements Parcelable.Creator<LaunchData> {
    public final /* synthetic */ LaunchData createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Intent intent = null;
        String str = null;
        String str2 = null;
        BitmapTeleporter bitmapTeleporter = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                intent = (Intent) zzbgm.zza(parcel, readInt, Intent.CREATOR);
            } else if (i == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bitmapTeleporter = (BitmapTeleporter) zzbgm.zza(parcel, readInt, BitmapTeleporter.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new LaunchData(intent, str, str2, bitmapTeleporter);
    }

    public final /* synthetic */ LaunchData[] newArray(int i) {
        return new LaunchData[i];
    }
}
