package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzd implements Parcelable.Creator<ParticipantResult> {
    public final /* synthetic */ ParticipantResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ParticipantResult(str, i, i2);
    }

    public final /* synthetic */ ParticipantResult[] newArray(int i) {
        return new ParticipantResult[i];
    }
}
