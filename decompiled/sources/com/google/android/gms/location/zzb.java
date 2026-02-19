package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzb implements Parcelable.Creator<ActivityRecognitionResult> {
    public final /* synthetic */ ActivityRecognitionResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        ArrayList<DetectedActivity> arrayList = null;
        Bundle bundle = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, DetectedActivity.CREATOR);
            } else if (i2 == 2) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i2 == 3) {
                j2 = zzbgm.zzi(parcel, readInt);
            } else if (i2 == 4) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                bundle = zzbgm.zzs(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ActivityRecognitionResult((List<DetectedActivity>) arrayList, j, j2, i, bundle);
    }

    public final /* synthetic */ ActivityRecognitionResult[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
