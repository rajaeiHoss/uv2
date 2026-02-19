package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzi implements Parcelable.Creator<BrowserPublicKeyCredentialRequestOptions> {
    public final /* synthetic */ BrowserPublicKeyCredentialRequestOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = null;
        Uri uri = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                publicKeyCredentialRequestOptions = (PublicKeyCredentialRequestOptions) zzbgm.zza(parcel, readInt, PublicKeyCredentialRequestOptions.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                uri = (Uri) zzbgm.zza(parcel, readInt, Uri.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new BrowserPublicKeyCredentialRequestOptions(publicKeyCredentialRequestOptions, uri);
    }

    public final /* synthetic */ BrowserPublicKeyCredentialRequestOptions[] newArray(int i) {
        return new BrowserPublicKeyCredentialRequestOptions[i];
    }
}
