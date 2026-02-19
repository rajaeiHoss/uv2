package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzcuu;
import com.google.android.gms.internal.zzcuz;
import com.google.android.gms.nearby.messages.internal.zzad;
import java.util.ArrayList;
import java.util.List;

public final class zzc implements Parcelable.Creator<MessageFilter> {
    public final /* synthetic */ MessageFilter createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzad> arrayList = null;
        ArrayList<zzcuz> arrayList2 = null;
        ArrayList<zzcuu> arrayList3 = null;
        int i = 0;
        boolean z = false;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, zzad.CREATOR);
            } else if (i3 == 2) {
                arrayList2 = zzbgm.zzc(parcel, readInt, zzcuz.CREATOR);
            } else if (i3 == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i3 == 4) {
                arrayList3 = zzbgm.zzc(parcel, readInt, zzcuu.CREATOR);
            } else if (i3 == 5) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new MessageFilter(i, (List<zzad>) arrayList, (List<zzcuz>) arrayList2, z, (List<zzcuu>) arrayList3, i2);
    }

    public final /* synthetic */ MessageFilter[] newArray(int i) {
        return new MessageFilter[i];
    }
}
