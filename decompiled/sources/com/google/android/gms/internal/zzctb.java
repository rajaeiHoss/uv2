package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctb extends zzbgl {
    public static final Parcelable.Creator<zzctb> CREATOR = new zzctc();
    private String zzjwf;
    private boolean zzjwg;
    private String zzjxf;
    private byte[] zzjxg;
    private String zzjzh;

    private zzctb() {
    }

    public zzctb(String str, String str2, String str3, boolean z, byte[] bArr) {
        this.zzjxf = str;
        this.zzjzh = str2;
        this.zzjwf = str3;
        this.zzjwg = z;
        this.zzjxg = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctb) {
            zzctb zzctb = (zzctb) obj;
            return zzbg.equal(this.zzjxf, zzctb.zzjxf) && zzbg.equal(this.zzjzh, zzctb.zzjzh) && zzbg.equal(this.zzjwf, zzctb.zzjwf) && zzbg.equal(Boolean.valueOf(this.zzjwg), Boolean.valueOf(zzctb.zzjwg)) && Arrays.equals(this.zzjxg, zzctb.zzjxg);
        }
        return false;
    }

    public final String getAuthenticationToken() {
        return this.zzjwf;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf, this.zzjzh, this.zzjwf, Boolean.valueOf(this.zzjwg), Integer.valueOf(Arrays.hashCode(this.zzjxg))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zza(parcel, 2, this.zzjzh, false);
        zzbgo.zza(parcel, 3, this.zzjwf, false);
        zzbgo.zza(parcel, 4, this.zzjwg);
        zzbgo.zza(parcel, 5, this.zzjxg, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbde() {
        return this.zzjxf;
    }

    public final String zzbdf() {
        return this.zzjzh;
    }

    public final boolean zzbdg() {
        return this.zzjwg;
    }
}
