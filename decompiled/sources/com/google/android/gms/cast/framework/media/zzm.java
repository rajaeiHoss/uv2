package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzm implements Parcelable.Creator<NotificationOptions> {
    public final /* synthetic */ NotificationOptions createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        ArrayList<String> arrayList = null;
        int[] iArr = null;
        String str = null;
        IBinder iBinder = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    arrayList = zzbgm.zzac(parcel2, readInt);
                    break;
                case 3:
                    iArr = zzbgm.zzw(parcel2, readInt);
                    break;
                case 4:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 5:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 7:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 8:
                    i3 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 9:
                    i4 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 10:
                    i5 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 11:
                    i6 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 12:
                    i7 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 13:
                    i8 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 14:
                    i9 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 15:
                    i10 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 16:
                    i11 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 17:
                    i12 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 18:
                    i13 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 19:
                    i14 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 20:
                    i15 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 21:
                    i16 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 22:
                    i17 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 23:
                    i18 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 24:
                    i19 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 25:
                    i20 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 26:
                    i21 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 27:
                    i22 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 28:
                    i23 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 29:
                    i24 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 30:
                    i25 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 31:
                    i26 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 32:
                    i27 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 33:
                    iBinder = zzbgm.zzr(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new NotificationOptions(arrayList, iArr, j, str, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, iBinder);
    }

    public final /* synthetic */ NotificationOptions[] newArray(int i) {
        return new NotificationOptions[i];
    }
}
