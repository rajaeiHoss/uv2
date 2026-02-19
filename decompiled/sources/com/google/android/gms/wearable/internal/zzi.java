package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.zzb;

public final class zzi extends zzbgl implements zzb {
    public static final Parcelable.Creator<zzi> CREATOR = new zzj();
    private final String mValue;
    private byte zzlse;
    private final byte zzlsf;

    public zzi(byte b, byte b2, String str) {
        this.zzlse = b;
        this.zzlsf = b2;
        this.mValue = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzi zzi = (zzi) obj;
        return this.zzlse == zzi.zzlse && this.zzlsf == zzi.zzlsf && this.mValue.equals(zzi.mValue);
    }

    public final int hashCode() {
        return ((((this.zzlse + 31) * 31) + this.zzlsf) * 31) + this.mValue.hashCode();
    }

    public final String toString() {
        byte b = this.zzlse;
        byte b2 = this.zzlsf;
        String str = this.mValue;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 73);
        sb.append("AmsEntityUpdateParcelable{, mEntityId=");
        sb.append(b);
        sb.append(", mAttributeId=");
        sb.append(b2);
        sb.append(", mValue='");
        sb.append(str);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlse);
        zzbgo.zza(parcel, 3, this.zzlsf);
        zzbgo.zza(parcel, 4, this.mValue, false);
        zzbgo.zzai(parcel, zze);
    }
}
