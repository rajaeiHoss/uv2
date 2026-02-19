package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.MessageEvent;

public final class zzfe extends zzbgl implements MessageEvent {
    public static final Parcelable.Creator<zzfe> CREATOR = new zzff();
    private final String mPath;
    private final String zzdwr;
    private final int zzgpc;
    private final byte[] zzigl;

    public zzfe(int i, String str, byte[] bArr, String str2) {
        this.zzgpc = i;
        this.mPath = str;
        this.zzigl = bArr;
        this.zzdwr = str2;
    }

    public final byte[] getData() {
        return this.zzigl;
    }

    public final String getPath() {
        return this.mPath;
    }

    public final int getRequestId() {
        return this.zzgpc;
    }

    public final String getSourceNodeId() {
        return this.zzdwr;
    }

    public final String toString() {
        int i = this.zzgpc;
        String str = this.mPath;
        byte[] bArr = this.zzigl;
        String valueOf = String.valueOf(bArr == null ? "null" : Integer.valueOf(bArr.length));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(valueOf).length());
        sb.append("MessageEventParcelable[");
        sb.append(i);
        sb.append(",");
        sb.append(str);
        sb.append(", size=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getRequestId());
        zzbgo.zza(parcel, 3, getPath(), false);
        zzbgo.zza(parcel, 4, getData(), false);
        zzbgo.zza(parcel, 5, getSourceNodeId(), false);
        zzbgo.zzai(parcel, zze);
    }
}
