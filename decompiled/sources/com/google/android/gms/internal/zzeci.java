package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;

public final class zzeci extends zzbgl {
    public static final Parcelable.Creator<zzeci> CREATOR = new zzecj();
    private String mSessionId;
    private String zzccf;
    private String zzelh;
    private String zzemh;
    private String zzmov;
    private String zzmow;
    private boolean zzmpx;
    private String zzmtc;
    private String zzmtd;
    private String zzmte;
    private boolean zzmtf;
    private String zzmtg;
    private String zzmth;

    public zzeci() {
        this.zzmtf = true;
        this.zzmpx = true;
    }

    public zzeci(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, (String) null, str5, (String) null);
    }

    public zzeci(String str, String str2, String str3, String str4, String str5, String str6) {
        this.zzmtc = "http://localhost";
        this.zzelh = str;
        this.zzmow = str2;
        this.zzmte = str5;
        this.zzmtg = str6;
        this.zzmtf = true;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.zzmow) || !TextUtils.isEmpty(this.zzmtg)) {
            this.zzmov = zzbq.zzgv(str3);
            this.zzemh = null;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.zzelh)) {
                sb.append("id_token=");
                sb.append(this.zzelh);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzmow)) {
                sb.append("access_token=");
                sb.append(this.zzmow);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzemh)) {
                sb.append("identifier=");
                sb.append(this.zzemh);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzmte)) {
                sb.append("oauth_token_secret=");
                sb.append(this.zzmte);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzmtg)) {
                sb.append("code=");
                sb.append(this.zzmtg);
                sb.append("&");
            }
            sb.append("providerId=");
            sb.append(this.zzmov);
            this.zzccf = sb.toString();
            this.zzmpx = true;
            return;
        }
        throw new IllegalArgumentException("idToken, accessToken and authCode cannot all be null");
    }

    zzeci(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, String str9, String str10, String str11) {
        this.zzmtc = str;
        this.zzmtd = str2;
        this.zzelh = str3;
        this.zzmow = str4;
        this.zzmov = str5;
        this.zzemh = str6;
        this.zzccf = str7;
        this.zzmte = str8;
        this.zzmtf = z;
        this.zzmpx = z2;
        this.zzmtg = str9;
        this.mSessionId = str10;
        this.zzmth = str11;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzmtc, false);
        zzbgo.zza(parcel, 3, this.zzmtd, false);
        zzbgo.zza(parcel, 4, this.zzelh, false);
        zzbgo.zza(parcel, 5, this.zzmow, false);
        zzbgo.zza(parcel, 6, this.zzmov, false);
        zzbgo.zza(parcel, 7, this.zzemh, false);
        zzbgo.zza(parcel, 8, this.zzccf, false);
        zzbgo.zza(parcel, 9, this.zzmte, false);
        zzbgo.zza(parcel, 10, this.zzmtf);
        zzbgo.zza(parcel, 11, this.zzmpx);
        zzbgo.zza(parcel, 12, this.zzmtg, false);
        zzbgo.zza(parcel, 13, this.mSessionId, false);
        zzbgo.zza(parcel, 14, this.zzmth, false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzeci zzcn(boolean z) {
        this.zzmpx = false;
        return this;
    }
}
