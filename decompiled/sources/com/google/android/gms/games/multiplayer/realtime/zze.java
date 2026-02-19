package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public class zze implements Parcelable.Creator<RoomEntity> {
    public /* synthetic */ RoomEntity[] newArray(int i) {
        return new RoomEntity[i];
    }

    /* renamed from: zzs */
    public RoomEntity createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        Bundle bundle = null;
        ArrayList<ParticipantEntity> arrayList = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 2:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 3:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 4:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 5:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 7:
                    bundle = zzbgm.zzs(parcel2, readInt);
                    break;
                case 8:
                    arrayList = zzbgm.zzc(parcel2, readInt, ParticipantEntity.CREATOR);
                    break;
                case 9:
                    i3 = zzbgm.zzg(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new RoomEntity(str, str2, j, i, str3, i2, bundle, arrayList, i3);
    }
}
