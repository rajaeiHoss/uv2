package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzy;
import com.google.android.gms.internal.zzbgo;

public final class zzo extends zzy implements DriveEvent {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private DataHolder zzfxb;
    private boolean zzgsg;
    private int zzgsh;

    public zzo(DataHolder dataHolder, boolean z, int i) {
        this.zzfxb = dataHolder;
        this.zzgsg = z;
        this.zzgsh = i;
    }

    public final int getType() {
        return 3;
    }

    public final void zzaj(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzfxb, i, false);
        zzbgo.zza(parcel, 3, this.zzgsg);
        zzbgo.zzc(parcel, 4, this.zzgsh);
        zzbgo.zzai(parcel, zze);
    }

    public final DataHolder zzaqe() {
        return this.zzfxb;
    }

    public final boolean zzaqf() {
        return this.zzgsg;
    }

    public final int zzaqg() {
        return this.zzgsh;
    }
}
