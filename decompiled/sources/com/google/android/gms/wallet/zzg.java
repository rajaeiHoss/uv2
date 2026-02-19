package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzg implements Parcelable.Creator<Cart> {
    public final /* synthetic */ Cart createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<LineItem> arrayList = new ArrayList<>();
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, LineItem.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Cart(str, str2, arrayList);
    }

    public final /* synthetic */ Cart[] newArray(int i) {
        return new Cart[i];
    }
}
