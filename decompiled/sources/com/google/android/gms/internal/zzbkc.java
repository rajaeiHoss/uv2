package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public final class zzbkc extends zzbgl {
    public static final Parcelable.Creator<zzbkc> CREATOR = new zzbkd();
    private String zzgnf;
    private zzbke zzgng;
    private long zzgnh;

    public zzbkc(String str, long j, zzbke zzbke) {
        this(zzbq.zzgv(str), (zzbke) zzbq.checkNotNull(zzbke), j);
    }

    zzbkc(String str, zzbke zzbke, long j) {
        this.zzgnf = str;
        this.zzgng = zzbke;
        this.zzgnh = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbkc)) {
            return false;
        }
        zzbkc zzbkc = (zzbkc) obj;
        return TextUtils.equals(this.zzgnf, zzbkc.zzgnf) && this.zzgnh == zzbkc.zzgnh;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgnf, Long.valueOf(this.zzgnh)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgnf, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgng, i, false);
        zzbgo.zza(parcel, 4, this.zzgnh);
        zzbgo.zzai(parcel, zze);
    }
}
