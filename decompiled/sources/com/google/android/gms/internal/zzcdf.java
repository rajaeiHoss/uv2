package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcdf extends zzbgl {
    public static final Parcelable.Creator<zzcdf> CREATOR = new zzcdg();
    private int versionCode;
    private zzba zzijg = null;
    private byte[] zzijh;

    zzcdf(int i, byte[] bArr) {
        this.versionCode = i;
        this.zzijh = bArr;
        zzaot();
    }

    private final void zzaot() {
        zzba zzba = this.zzijg;
        if (zzba == null && this.zzijh != null) {
            return;
        }
        if (zzba != null && this.zzijh == null) {
            return;
        }
        if (zzba != null && this.zzijh != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (zzba == null && this.zzijh == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        byte[] bArr = this.zzijh;
        if (bArr == null) {
            bArr = zzfls.zzc(this.zzijg);
        }
        zzbgo.zza(parcel, 2, bArr, false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzba zzawd() {
        if (!(this.zzijg != null)) {
            try {
                this.zzijg = (zzba) zzfls.zza(new zzba(), this.zzijh);
                this.zzijh = null;
            } catch (zzflr e) {
                throw new IllegalStateException(e);
            }
        }
        zzaot();
        return this.zzijg;
    }
}
