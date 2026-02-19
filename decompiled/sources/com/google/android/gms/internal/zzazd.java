package com.google.android.gms.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.ActivityRecognitionResult;

public final class zzazd extends zzbgl {
    public static final Parcelable.Creator<zzazd> CREATOR = new zzazv();
    private final ActivityRecognitionResult zzerj;
    private final zzayo zzerk;
    private final zzays zzerl;
    private final zzayu zzerm;
    private final DataHolder zzern;
    private final zzayz zzero;
    private final zzazb zzerp;
    private final zzbac zzerq;
    private final zzazz zzerr;
    private final zzbjp zzers;
    private final Location zzhl;

    public zzazd(ActivityRecognitionResult activityRecognitionResult, zzayo zzayo, zzays zzays, Location location, zzayu zzayu, DataHolder dataHolder, zzayz zzayz, zzazb zzazb, zzbac zzbac, zzazz zzazz, zzbjp zzbjp) {
        this.zzerj = activityRecognitionResult;
        this.zzerk = zzayo;
        this.zzerl = zzays;
        this.zzhl = location;
        this.zzerm = zzayu;
        this.zzern = dataHolder;
        this.zzero = zzayz;
        this.zzerp = zzazb;
        this.zzerq = zzbac;
        this.zzerr = zzazz;
        this.zzers = zzbjp;
    }

    public final ActivityRecognitionResult getActivityRecognitionResult() {
        return this.zzerj;
    }

    public final Location getLocation() {
        return this.zzhl;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzerj, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzerk, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzerl, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzhl, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzerm, i, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzern, i, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzero, i, false);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzerp, i, false);
        zzbgo.zza(parcel, 10, (Parcelable) this.zzerq, i, false);
        zzbgo.zza(parcel, 11, (Parcelable) this.zzerr, i, false);
        zzbgo.zza(parcel, 12, (Parcelable) this.zzers, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzayo zzadg() {
        return this.zzerk;
    }

    public final zzays zzadh() {
        return this.zzerl;
    }

    public final DataHolder zzadi() {
        return this.zzern;
    }

    public final zzbac zzadj() {
        return this.zzerq;
    }

    public final zzazz zzadk() {
        return this.zzerr;
    }
}
