package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.firebase.auth.zzd;
import java.util.List;

public final class zzebu extends zzbgl {
    public static final Parcelable.Creator<zzebu> CREATOR = new zzebv();
    private long mCreationTimestamp;
    private String zzekn;
    private String zzemh;
    private String zzemi;
    private String zziva;
    private String zzmqb;
    private String zzmsk;
    private boolean zzmsl;
    private zzeca zzmsm;
    private long zzmsn;
    private boolean zzmso;
    private zzd zzmsp;

    public zzebu() {
        this.zzmsm = new zzeca();
    }

    public zzebu(String str, String str2, boolean z, String str3, String str4, zzeca zzeca, String str5, String str6, long j, long j2, boolean z2, zzd zzd) {
        this.zzmsk = str;
        this.zzemh = str2;
        this.zzmsl = z;
        this.zzemi = str3;
        this.zzmqb = str4;
        this.zzmsm = zzeca == null ? new zzeca() : zzeca.zza(zzeca);
        this.zzekn = str5;
        this.zziva = str6;
        this.mCreationTimestamp = j;
        this.zzmsn = j2;
        this.zzmso = z2;
        this.zzmsp = zzd;
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    public final String getDisplayName() {
        return this.zzemi;
    }

    public final String getEmail() {
        return this.zzemh;
    }

    public final long getLastSignInTimestamp() {
        return this.zzmsn;
    }

    public final String getLocalId() {
        return this.zzmsk;
    }

    public final String getPhoneNumber() {
        return this.zziva;
    }

    public final Uri getPhotoUri() {
        if (!TextUtils.isEmpty(this.zzmqb)) {
            return Uri.parse(this.zzmqb);
        }
        return null;
    }

    public final boolean isEmailVerified() {
        return this.zzmsl;
    }

    public final boolean isNewUser() {
        return this.zzmso;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzmsk, false);
        zzbgo.zza(parcel, 3, this.zzemh, false);
        zzbgo.zza(parcel, 4, this.zzmsl);
        zzbgo.zza(parcel, 5, this.zzemi, false);
        zzbgo.zza(parcel, 6, this.zzmqb, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzmsm, i, false);
        zzbgo.zza(parcel, 8, this.zzekn, false);
        zzbgo.zza(parcel, 9, this.zziva, false);
        zzbgo.zza(parcel, 10, this.mCreationTimestamp);
        zzbgo.zza(parcel, 11, this.zzmsn);
        zzbgo.zza(parcel, 12, this.zzmso);
        zzbgo.zza(parcel, 13, (Parcelable) this.zzmsp, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final List<zzeby> zzbuc() {
        return this.zzmsm.zzbuc();
    }

    public final zzd zzbud() {
        return this.zzmsp;
    }
}
