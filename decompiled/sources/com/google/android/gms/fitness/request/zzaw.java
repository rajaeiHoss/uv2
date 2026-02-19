package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzaw implements Parcelable.Creator<SessionReadRequest> {
    public final /* synthetic */ SessionReadRequest createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        String str = null;
        String str2 = null;
        ArrayList<DataType> arrayList = null;
        ArrayList<DataSource> arrayList2 = null;
        ArrayList<String> arrayList3 = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
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
                    j2 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 5:
                    arrayList = zzbgm.zzc(parcel2, readInt, DataType.CREATOR);
                    break;
                case 6:
                    arrayList2 = zzbgm.zzc(parcel2, readInt, DataSource.CREATOR);
                    break;
                case 7:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 8:
                    z2 = zzbgm.zzc(parcel2, readInt);
                    break;
                case 9:
                    arrayList3 = zzbgm.zzac(parcel2, readInt);
                    break;
                case 10:
                    iBinder = zzbgm.zzr(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new SessionReadRequest(str, str2, j, j2, (List<DataType>) arrayList, (List<DataSource>) arrayList2, z, z2, (List<String>) arrayList3, iBinder);
    }

    public final /* synthetic */ SessionReadRequest[] newArray(int i) {
        return new SessionReadRequest[i];
    }
}
