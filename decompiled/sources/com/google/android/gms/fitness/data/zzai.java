package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzai implements Parcelable.Creator<Value> {
    public final /* synthetic */ Value createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Bundle bundle = null;
        int[] iArr = null;
        float[] fArr = null;
        byte[] bArr = null;
        int i = 0;
        boolean z = false;
        float f = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    z = zzbgm.zzc(parcel, readInt);
                    break;
                case 3:
                    f = zzbgm.zzl(parcel, readInt);
                    break;
                case 4:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 5:
                    bundle = zzbgm.zzs(parcel, readInt);
                    break;
                case 6:
                    iArr = zzbgm.zzw(parcel, readInt);
                    break;
                case 7:
                    fArr = zzbgm.zzy(parcel, readInt);
                    break;
                case 8:
                    bArr = zzbgm.zzt(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Value(i, z, f, str, bundle, iArr, fArr, bArr);
    }

    public final /* synthetic */ Value[] newArray(int i) {
        return new Value[i];
    }
}
