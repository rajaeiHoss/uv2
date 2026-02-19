package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

public final class zzf implements AuthResult {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    private zzk zzmtk;
    private zzd zzmtl;
    private com.google.firebase.auth.zzd zzmtm;

    public zzf(zzk zzk) {
        zzk zzk2 = (zzk) zzbq.checkNotNull(zzk);
        this.zzmtk = zzk2;
        List<zzh> zzbum = zzk2.zzbum();
        this.zzmtl = null;
        for (int i = 0; i < zzbum.size(); i++) {
            if (!TextUtils.isEmpty(zzbum.get(i).getRawUserInfo())) {
                this.zzmtl = new zzd(zzbum.get(i).getProviderId(), zzbum.get(i).getRawUserInfo(), zzk.isNewUser());
            }
        }
        if (this.zzmtl == null) {
            this.zzmtl = new zzd(zzk.isNewUser());
        }
        this.zzmtm = zzk.zzbud();
    }

    zzf(zzk zzk, zzd zzd, com.google.firebase.auth.zzd zzd2) {
        this.zzmtk = zzk;
        this.zzmtl = zzd;
        this.zzmtm = zzd2;
    }

    public final int describeContents() {
        return 0;
    }

    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzmtl;
    }

    public final FirebaseUser getUser() {
        return this.zzmtk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getUser(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getAdditionalUserInfo(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzmtm, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
