package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzh implements Parcelable.Creator<BrowserMakeCredentialOptions> {
    public final /* synthetic */ BrowserMakeCredentialOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        MakeCredentialOptions makeCredentialOptions = null;
        Uri uri = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                makeCredentialOptions = (MakeCredentialOptions) zzbgm.zza(parcel, readInt, MakeCredentialOptions.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new BrowserMakeCredentialOptions(makeCredentialOptions, uri);
    }

    public final /* synthetic */ BrowserMakeCredentialOptions[] newArray(int i) {
        return new BrowserMakeCredentialOptions[i];
    }
}
