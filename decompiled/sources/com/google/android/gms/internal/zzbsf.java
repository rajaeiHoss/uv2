package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzo;
import com.google.android.gms.drive.events.zzr;
import com.google.android.gms.drive.events.zzv;

public final class zzbsf extends zzbgl {
    public static final Parcelable.Creator<zzbsf> CREATOR = new zzbsg();
    private int zzgjw;
    private ChangeEvent zzgxi;
    private CompletionEvent zzgxj;
    private zzo zzgxk;
    private zzb zzgxl;
    private zzv zzgxm;
    private zzr zzgxn;

    zzbsf(int i, ChangeEvent changeEvent, CompletionEvent completionEvent, zzo zzo, zzb zzb, zzv zzv, zzr zzr) {
        this.zzgjw = i;
        this.zzgxi = changeEvent;
        this.zzgxj = completionEvent;
        this.zzgxk = zzo;
        this.zzgxl = zzb;
        this.zzgxm = zzv;
        this.zzgxn = zzr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgjw);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgxi, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgxj, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzgxk, i, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzgxl, i, false);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzgxm, i, false);
        zzbgo.zza(parcel, 10, (Parcelable) this.zzgxn, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final DriveEvent zzaqq() {
        int i = this.zzgjw;
        if (i == 1) {
            return this.zzgxi;
        }
        if (i == 2) {
            return this.zzgxj;
        }
        if (i == 3) {
            return this.zzgxk;
        }
        if (i == 4) {
            return this.zzgxl;
        }
        if (i == 7) {
            return this.zzgxm;
        }
        if (i == 8) {
            return this.zzgxn;
        }
        int i2 = this.zzgjw;
        StringBuilder sb = new StringBuilder(33);
        sb.append("Unexpected event type ");
        sb.append(i2);
        throw new IllegalStateException(sb.toString());
    }
}
