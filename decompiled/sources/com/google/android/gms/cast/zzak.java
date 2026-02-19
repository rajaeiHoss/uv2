package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzak implements Parcelable.Creator<MediaTrack> {
    public final /* synthetic */ MediaTrack createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 3:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 4:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 5:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    str4 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 8:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 9:
                    str5 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new MediaTrack(j, i, str, str2, str3, str4, i2, str5);
    }

    public final /* synthetic */ MediaTrack[] newArray(int i) {
        return new MediaTrack[i];
    }
}
