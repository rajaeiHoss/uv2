package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zza implements Parcelable.Creator<CastMediaOptions> {
    public final /* synthetic */ CastMediaOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        NotificationOptions notificationOptions = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 4) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                notificationOptions = (NotificationOptions) zzbgm.zza(parcel, readInt, NotificationOptions.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new CastMediaOptions(str, str2, iBinder, notificationOptions);
    }

    public final /* synthetic */ CastMediaOptions[] newArray(int i) {
        return new CastMediaOptions[i];
    }
}
