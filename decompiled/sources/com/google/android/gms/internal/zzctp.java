package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctp extends zzbgl {
    public static final Parcelable.Creator<zzctp> CREATOR = new zzctq();
    private String zzjxf;
    private zzcub zzjzj;
    private boolean zzjzk;

    private zzctp() {
    }

    public zzctp(String str, zzcub zzcub, boolean z) {
        this.zzjxf = str;
        this.zzjzj = zzcub;
        this.zzjzk = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctp) {
            zzctp zzctp = (zzctp) obj;
            return zzbg.equal(this.zzjxf, zzctp.zzjxf) && zzbg.equal(this.zzjzj, zzctp.zzjzj) && zzbg.equal(Boolean.valueOf(this.zzjzk), Boolean.valueOf(zzctp.zzjzk));
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf, this.zzjzj, Boolean.valueOf(this.zzjzk)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzjzj, i, false);
        zzbgo.zza(parcel, 3, this.zzjzk);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbde() {
        return this.zzjxf;
    }

    public final zzcub zzbdk() {
        return this.zzjzj;
    }

    public final boolean zzbdl() {
        return this.zzjzk;
    }
}
