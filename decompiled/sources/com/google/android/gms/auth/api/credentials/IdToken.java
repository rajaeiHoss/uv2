package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class IdToken extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<IdToken> CREATOR = new zzi();
    private final String zzekb;
    private final String zzelh;

    public IdToken(String str, String str2) {
        zzbq.checkArgument(!TextUtils.isEmpty(str), "account type string cannot be null or empty");
        zzbq.checkArgument(!TextUtils.isEmpty(str2), "id token string cannot be null or empty");
        this.zzekb = str;
        this.zzelh = str2;
    }

    public final String getAccountType() {
        return this.zzekb;
    }

    public final String getIdToken() {
        return this.zzelh;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getAccountType(), false);
        zzbgo.zza(parcel, 2, getIdToken(), false);
        zzbgo.zzai(parcel, zze);
    }
}
