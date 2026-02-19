package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;

public class PhoneAuthCredential extends AuthCredential implements Cloneable {
    public static final Parcelable.Creator<PhoneAuthCredential> CREATOR = new zzt();
    private String zziva;
    private String zzmpu;
    private String zzmpv;
    private boolean zzmpw;
    private boolean zzmpx;
    private String zzmpy;

    PhoneAuthCredential(String str, String str2, boolean z, String str3, boolean z2, String str4) {
        zzbq.checkArgument((z && !TextUtils.isEmpty(str3)) || (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)), "Cannot create PhoneAuthCredential without either verificationProof, sessionInfo, ortemprary proof.");
        this.zzmpu = str;
        this.zzmpv = str2;
        this.zzmpw = z;
        this.zziva = str3;
        this.zzmpx = z2;
        this.zzmpy = str4;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new PhoneAuthCredential(this.zzmpu, getSmsCode(), this.zzmpw, this.zziva, this.zzmpx, this.zzmpy);
    }

    public String getProvider() {
        return "phone";
    }

    public String getSignInMethod() {
        return "phone";
    }

    public String getSmsCode() {
        return this.zzmpv;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzmpu, false);
        zzbgo.zza(parcel, 2, getSmsCode(), false);
        zzbgo.zza(parcel, 3, this.zzmpw);
        zzbgo.zza(parcel, 4, this.zziva, false);
        zzbgo.zza(parcel, 5, this.zzmpx);
        zzbgo.zza(parcel, 6, this.zzmpy, false);
        zzbgo.zzai(parcel, zze);
    }

    public final PhoneAuthCredential zzcl(boolean z) {
        this.zzmpx = false;
        return this;
    }
}
