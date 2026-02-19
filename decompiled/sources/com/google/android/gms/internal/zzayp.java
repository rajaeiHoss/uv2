package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbq;

public final class zzayp extends zzbgl implements BeaconState.BeaconInfo {
    public static final Parcelable.Creator<zzayp> CREATOR = new zzayn();
    private final String zzczr;
    private final String zzeqz;
    private final byte[] zzera;

    public zzayp(String str, String str2, byte[] bArr) {
        this.zzeqz = zzbq.zzgv(str);
        this.zzczr = zzbq.zzgv(str2);
        this.zzera = bArr;
    }

    public final byte[] getContent() {
        return this.zzera;
    }

    public final String getNamespace() {
        return this.zzeqz;
    }

    public final String getType() {
        return this.zzczr;
    }

    public final String toString() {
        String str = this.zzera == null ? "<null>" : new String(this.zzera);
        String str2 = this.zzeqz;
        String str3 = this.zzczr;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 6 + String.valueOf(str3).length() + str.length());
        sb.append("(");
        sb.append(str2);
        sb.append(", ");
        sb.append(str3);
        sb.append(", ");
        sb.append(str);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getNamespace(), false);
        zzbgo.zza(parcel, 3, getType(), false);
        zzbgo.zza(parcel, 4, getContent(), false);
        zzbgo.zzai(parcel, zze);
    }
}
