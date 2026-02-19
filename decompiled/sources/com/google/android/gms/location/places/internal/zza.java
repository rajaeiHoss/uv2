package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zza extends zzbgl implements AutocompletePrediction {
    public static final Parcelable.Creator<zza> CREATOR = new zzc();
    private static final List<zzb> zziws = Collections.emptyList();
    private List<Integer> zziuz;
    private String zzivz;
    private String zziwt;
    private List<zzb> zziwu;
    private int zziwv;
    private String zziww;
    private List<zzb> zziwx;
    private String zziwy;
    private List<zzb> zziwz;

    zza(String str, List<Integer> list, int i, String str2, List<zzb> list2, String str3, List<zzb> list3, String str4, List<zzb> list4) {
        this.zzivz = str;
        this.zziuz = list;
        this.zziwv = i;
        this.zziwt = str2;
        this.zziwu = list2;
        this.zziww = str3;
        this.zziwx = list3;
        this.zziwy = str4;
        this.zziwz = list4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        zza zza = (zza) obj;
        return zzbg.equal(this.zzivz, zza.zzivz) && zzbg.equal(this.zziuz, zza.zziuz) && zzbg.equal(Integer.valueOf(this.zziwv), Integer.valueOf(zza.zziwv)) && zzbg.equal(this.zziwt, zza.zziwt) && zzbg.equal(this.zziwu, zza.zziwu) && zzbg.equal(this.zziww, zza.zziww) && zzbg.equal(this.zziwx, zza.zziwx) && zzbg.equal(this.zziwy, zza.zziwy) && zzbg.equal(this.zziwz, zza.zziwz);
    }

    public final /* bridge */ /* synthetic */ AutocompletePrediction freeze() {
        return this;
    }

    public final CharSequence getFullText(CharacterStyle characterStyle) {
        return zzg.zza(this.zziwt, this.zziwu, characterStyle);
    }

    public final String getPlaceId() {
        return this.zzivz;
    }

    public final List<Integer> getPlaceTypes() {
        return this.zziuz;
    }

    public final CharSequence getPrimaryText(CharacterStyle characterStyle) {
        return zzg.zza(this.zziww, this.zziwx, characterStyle);
    }

    public final CharSequence getSecondaryText(CharacterStyle characterStyle) {
        return zzg.zza(this.zziwy, this.zziwz, characterStyle);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzivz, this.zziuz, Integer.valueOf(this.zziwv), this.zziwt, this.zziwu, this.zziww, this.zziwx, this.zziwy, this.zziwz});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("placeId", this.zzivz).zzg("placeTypes", this.zziuz).zzg("fullText", this.zziwt).zzg("fullTextMatchedSubstrings", this.zziwu).zzg("primaryText", this.zziww).zzg("primaryTextMatchedSubstrings", this.zziwx).zzg("secondaryText", this.zziwy).zzg("secondaryTextMatchedSubstrings", this.zziwz).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zziwt, false);
        zzbgo.zza(parcel, 2, this.zzivz, false);
        zzbgo.zza(parcel, 3, this.zziuz, false);
        zzbgo.zzc(parcel, 4, this.zziwu, false);
        zzbgo.zzc(parcel, 5, this.zziwv);
        zzbgo.zza(parcel, 6, this.zziww, false);
        zzbgo.zzc(parcel, 7, this.zziwx, false);
        zzbgo.zza(parcel, 8, this.zziwy, false);
        zzbgo.zzc(parcel, 9, this.zziwz, false);
        zzbgo.zzai(parcel, zze);
    }
}
