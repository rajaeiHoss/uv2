package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzl implements Parcelable.Creator<PublicKeyCredentialDescriptor> {
    public final /* synthetic */ PublicKeyCredentialDescriptor createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        byte[] bArr = null;
        ArrayList<Transport> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                bArr = zzbgm.zzt(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, Transport.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PublicKeyCredentialDescriptor(str, bArr, arrayList);
    }

    public final /* synthetic */ PublicKeyCredentialDescriptor[] newArray(int i) {
        return new PublicKeyCredentialDescriptor[i];
    }
}
