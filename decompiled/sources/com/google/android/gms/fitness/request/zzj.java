package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzj implements Parcelable.Creator<DataDeleteRequest> {
    public final /* synthetic */ DataDeleteRequest createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        ArrayList<DataSource> arrayList = null;
        ArrayList<DataType> arrayList2 = null;
        ArrayList<Session> arrayList3 = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 2:
                    j2 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 3:
                    arrayList = zzbgm.zzc(parcel2, readInt, DataSource.CREATOR);
                    break;
                case 4:
                    arrayList2 = zzbgm.zzc(parcel2, readInt, DataType.CREATOR);
                    break;
                case 5:
                    arrayList3 = zzbgm.zzc(parcel2, readInt, Session.CREATOR);
                    break;
                case 6:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 7:
                    z2 = zzbgm.zzc(parcel2, readInt);
                    break;
                case 8:
                    iBinder = zzbgm.zzr(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new DataDeleteRequest(j, j2, (List<DataSource>) arrayList, (List<DataType>) arrayList2, (List<Session>) arrayList3, z, z2, iBinder);
    }

    public final /* synthetic */ DataDeleteRequest[] newArray(int i) {
        return new DataDeleteRequest[i];
    }
}
