package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AdditionalUserInfo;
import java.util.Map;

public final class zzd implements AdditionalUserInfo {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();
    private final String zzmov;
    private boolean zzmso;
    private final String zzmsu;
    private Map<String, Object> zzmtj;

    public zzd(String str, String str2, boolean z) {
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        this.zzmov = str;
        this.zzmsu = str2;
        this.zzmtj = zzw.zzpj(str2);
        this.zzmso = z;
    }

    public zzd(boolean z) {
        this.zzmso = z;
        this.zzmsu = null;
        this.zzmov = null;
        this.zzmtj = null;
    }

    public final int describeContents() {
        return 0;
    }

    public final Map<String, Object> getProfile() {
        return this.zzmtj;
    }

    public final String getProviderId() {
        return this.zzmov;
    }

    public final String getUsername() {
        Map<String, Object> map;
        String str;
        if ("github.com".equals(this.zzmov)) {
            map = this.zzmtj;
            str = FirebaseAnalytics.Event.LOGIN;
        } else if (!"twitter.com".equals(this.zzmov)) {
            return null;
        } else {
            map = this.zzmtj;
            str = "screen_name";
        }
        return (String) map.get(str);
    }

    public final boolean isNewUser() {
        return this.zzmso;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getProviderId(), false);
        zzbgo.zza(parcel, 2, this.zzmsu, false);
        zzbgo.zza(parcel, 3, isNewUser());
        zzbgo.zzai(parcel, zze);
    }
}
