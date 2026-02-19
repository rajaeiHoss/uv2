package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;

public final class zzau extends zzbgl {
    public static final Parcelable.Creator<zzau> CREATOR = new zzav();
    private static zzau zziyx = new zzau("com.google.android.gms", Locale.getDefault(), (String) null);
    private String zzgog;
    private String zziwk;
    private String zziyy;
    private String zziyz;
    private int zziza;
    private int zzizb;

    public zzau(String str, String str2, String str3, String str4, int i, int i2) {
        this.zziyy = str;
        this.zziyz = str2;
        this.zzgog = str3;
        this.zziwk = str4;
        this.zziza = i;
        this.zzizb = i2;
    }

    private zzau(String str, Locale locale, String str2) {
        this(str, locale.toString(), (String) null, (String) null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
    }

    public zzau(String str, Locale locale, String str2, String str3, int i) {
        this(str, locale.toString(), str2, str3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzau)) {
            zzau zzau = (zzau) obj;
            return this.zziza == zzau.zziza && this.zzizb == zzau.zzizb && this.zziyz.equals(zzau.zziyz) && this.zziyy.equals(zzau.zziyy) && zzbg.equal(this.zzgog, zzau.zzgog) && zzbg.equal(this.zziwk, zzau.zziwk);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zziyy, this.zziyz, this.zzgog, this.zziwk, Integer.valueOf(this.zziza), Integer.valueOf(this.zzizb)});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("clientPackageName", this.zziyy).zzg("locale", this.zziyz).zzg("accountName", this.zzgog).zzg("gCoreClientName", this.zziwk).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zziyy, false);
        zzbgo.zza(parcel, 2, this.zziyz, false);
        zzbgo.zza(parcel, 3, this.zzgog, false);
        zzbgo.zza(parcel, 4, this.zziwk, false);
        zzbgo.zzc(parcel, 6, this.zziza);
        zzbgo.zzc(parcel, 7, this.zzizb);
        zzbgo.zzai(parcel, zze);
    }
}
