package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public final class zzayq extends BeaconState.TypeFilter {
    public static final Parcelable.Creator<zzayq> CREATOR = new zzbab();
    private final zzffh zzerb;

    public zzayq(String str, String str2) {
        zzffh zzffh = new zzffh();
        this.zzerb = zzffh;
        zzffh.zzkal = zzbq.zzgv(str);
        zzffh.type = zzbq.zzgv(str2);
    }

    public zzayq(String str, String str2, byte[] bArr) {
        zzffh zzffh = new zzffh();
        this.zzerb = zzffh;
        zzffh.zzkal = zzbq.zzgv(str);
        zzffh.type = zzbq.zzgv(str2);
        zzffh.content = (byte[]) zzbq.checkNotNull(bArr);
    }

    zzayq(byte[] bArr) {
        zzffh zzffh;
        try {
            zzffh = (zzffh) zzfls.zza(new zzffh(), bArr);
        } catch (zzflr unused) {
            zzfi.zzb("BeaconStateImpl", "Could not deserialize BeaconFence.BeaconTypeFilter");
            zzffh = null;
        }
        this.zzerb = zzffh;
    }

    private final byte[] getContent() {
        zzffh zzffh = this.zzerb;
        if (zzffh == null || zzffh.content == null || this.zzerb.content.length == 0) {
            return null;
        }
        return this.zzerb.content;
    }

    private final String getNamespace() {
        zzffh zzffh = this.zzerb;
        if (zzffh == null) {
            return null;
        }
        return zzffh.zzkal;
    }

    private final String getType() {
        zzffh zzffh = this.zzerb;
        if (zzffh == null) {
            return null;
        }
        return zzffh.type;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzayq)) {
            return false;
        }
        zzayq zzayq = (zzayq) obj;
        return TextUtils.equals(getNamespace(), zzayq.getNamespace()) && TextUtils.equals(getType(), zzayq.getType()) && Arrays.equals(getContent(), zzayq.getContent());
    }

    public final int hashCode() {
        Object[] objArr = new Object[3];
        int i = 0;
        objArr[0] = getNamespace();
        objArr[1] = getType();
        if (getContent() != null) {
            i = Arrays.hashCode(getContent());
        }
        objArr[2] = Integer.valueOf(i);
        return Arrays.hashCode(objArr);
    }

    public final String toString() {
        String namespace = getNamespace();
        String type = getType();
        String str = getContent() == null ? "null" : new String(getContent());
        StringBuilder sb = new StringBuilder(String.valueOf(namespace).length() + 4 + String.valueOf(type).length() + str.length());
        sb.append("(");
        sb.append(namespace);
        sb.append(",");
        sb.append(type);
        sb.append(",");
        sb.append(str);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, zzfls.zzc(this.zzerb), false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzffh zzadf() {
        return this.zzerb;
    }
}
