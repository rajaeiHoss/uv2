package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class CardInfo extends zzbgl {
    public static final Parcelable.Creator<CardInfo> CREATOR = new zzc();
    private String zzljj;
    private String zzljk;
    private String zzljl;
    private int zzljm;
    private UserAddress zzljn;

    private CardInfo() {
    }

    CardInfo(String str, String str2, String str3, int i, UserAddress userAddress) {
        this.zzljj = str;
        this.zzljk = str2;
        this.zzljl = str3;
        this.zzljm = i;
        this.zzljn = userAddress;
    }

    public final UserAddress getBillingAddress() {
        return this.zzljn;
    }

    public final int getCardClass() {
        int i = this.zzljm;
        if (i == 1 || i == 2 || i == 3) {
            return i;
        }
        return 0;
    }

    public final String getCardDescription() {
        return this.zzljj;
    }

    public final String getCardDetails() {
        return this.zzljl;
    }

    public final String getCardNetwork() {
        return this.zzljk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzljj, false);
        zzbgo.zza(parcel, 2, this.zzljk, false);
        zzbgo.zza(parcel, 3, this.zzljl, false);
        zzbgo.zzc(parcel, 4, this.zzljm);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzljn, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
