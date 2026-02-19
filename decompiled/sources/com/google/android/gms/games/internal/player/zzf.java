package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzf implements Parcelable.Creator<StockProfileImageEntity> {
    public final /* synthetic */ StockProfileImageEntity createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Uri uri = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new StockProfileImageEntity(str, uri);
    }

    public final /* synthetic */ StockProfileImageEntity[] newArray(int i) {
        return new StockProfileImageEntity[i];
    }
}
