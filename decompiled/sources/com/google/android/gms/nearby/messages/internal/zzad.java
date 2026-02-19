package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzad extends zzbgl {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    private String type;
    private int zzehz;
    private String zzkal;

    zzad(int i, String str, String str2) {
        this.zzehz = i;
        this.zzkal = str;
        this.type = str2;
    }

    public zzad(String str, String str2) {
        this(1, str, str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof zzad) && hashCode() == obj.hashCode()) {
            zzad zzad = (zzad) obj;
            return zzbg.equal(this.zzkal, zzad.zzkal) && zzbg.equal(this.type, zzad.type);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkal, this.type});
    }

    public final String toString() {
        String str = this.zzkal;
        String str2 = this.type;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 17 + String.valueOf(str2).length());
        sb.append("namespace=");
        sb.append(str);
        sb.append(", type=");
        sb.append(str2);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzkal, false);
        zzbgo.zza(parcel, 2, this.type, false);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }
}
