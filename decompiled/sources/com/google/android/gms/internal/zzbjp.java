package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public final class zzbjp extends zzbgl {
    public static final Parcelable.Creator<zzbjp> CREATOR = new zzbjr();
    private static zzfj zzgmt = new zzbjq();
    private static int[] zzgmu = {0, 1};
    private zzffi zzgmv = null;
    private byte[] zzgmw;

    public zzbjp(byte[] bArr) {
        this.zzgmw = (byte[]) zzbq.checkNotNull(bArr);
        zzaot();
    }

    private final String getId() {
        zzaos();
        return this.zzgmv.zzpks;
    }

    private final void zzaos() {
        if (!(this.zzgmv != null)) {
            try {
                this.zzgmv = (zzffi) zzfls.zza(new zzffi(), this.zzgmw);
                this.zzgmw = null;
            } catch (zzflr e) {
                Log.e("ContextData", "Could not deserialize context bytes.", e);
                throw new IllegalStateException(e);
            }
        }
        zzaot();
    }

    private final void zzaot() {
        zzffi zzffi = this.zzgmv;
        if (zzffi == null && this.zzgmw != null) {
            return;
        }
        if (zzffi != null && this.zzgmw == null) {
            return;
        }
        if (zzffi != null && this.zzgmw != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (zzffi == null && this.zzgmw == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbjp)) {
            return false;
        }
        zzbjp zzbjp = (zzbjp) obj;
        zzaos();
        zzbjp.zzaos();
        return getId().equals(zzbjp.getId()) && this.zzgmv.zzpkt.version == zzbjp.zzgmv.zzpkt.version;
    }

    public final int hashCode() {
        zzaos();
        return Arrays.hashCode(new Object[]{getId(), Integer.valueOf(this.zzgmv.zzpkt.version)});
    }

    public final String toString() {
        zzaos();
        String valueOf = String.valueOf(this.zzgmv.toString());
        String valueOf2 = String.valueOf(zzgmt.zza(this));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        byte[] bArr = this.zzgmw;
        if (bArr == null) {
            bArr = zzfls.zzc(this.zzgmv);
        }
        zzbgo.zza(parcel, 2, bArr, false);
        zzbgo.zzai(parcel, zze);
    }
}
