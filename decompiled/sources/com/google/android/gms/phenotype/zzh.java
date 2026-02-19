package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzh implements Parcelable.Creator<ExperimentTokens> {
    public final /* synthetic */ ExperimentTokens createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        byte[] bArr = null;
        byte[][] bArr2 = null;
        byte[][] bArr3 = null;
        byte[][] bArr4 = null;
        byte[][] bArr5 = null;
        int[] iArr = null;
        byte[][] bArr6 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 3:
                    bArr = zzbgm.zzt(parcel, readInt);
                    break;
                case 4:
                    bArr2 = zzbgm.zzu(parcel, readInt);
                    break;
                case 5:
                    bArr3 = zzbgm.zzu(parcel, readInt);
                    break;
                case 6:
                    bArr4 = zzbgm.zzu(parcel, readInt);
                    break;
                case 7:
                    bArr5 = zzbgm.zzu(parcel, readInt);
                    break;
                case 8:
                    iArr = zzbgm.zzw(parcel, readInt);
                    break;
                case 9:
                    bArr6 = zzbgm.zzu(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ExperimentTokens(str, bArr, bArr2, bArr3, bArr4, bArr5, iArr, bArr6);
    }

    public final /* synthetic */ ExperimentTokens[] newArray(int i) {
        return new ExperimentTokens[i];
    }
}
