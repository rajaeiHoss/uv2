package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzbg implements Parcelable.Creator<StartBleScanRequest> {
    public final /* synthetic */ StartBleScanRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<DataType> arrayList = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, DataType.CREATOR);
            } else if (i2 == 2) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i2 == 3) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder2 = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new StartBleScanRequest((List<DataType>) arrayList, iBinder, i, iBinder2);
    }

    public final /* synthetic */ StartBleScanRequest[] newArray(int i) {
        return new StartBleScanRequest[i];
    }
}
