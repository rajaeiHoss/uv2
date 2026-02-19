package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.nearby.messages.internal.zzg;
import com.google.android.gms.nearby.messages.internal.zzl;
import java.util.UUID;

public final class zzcuz extends zzbgl {
    public static final Parcelable.Creator<zzcuz> CREATOR = new zzcva();
    private int zzehz;
    private int zzkcn;
    private byte[] zzkco;
    private boolean zzkcp;

    zzcuz(int i, int i2, byte[] bArr, boolean z) {
        this.zzehz = i;
        this.zzkcn = i2;
        this.zzkco = bArr;
        this.zzkcp = z;
    }

    private zzcuz(int i, byte[] bArr) {
        this(1, i, bArr, false);
    }

    public static zzcuz zza(UUID uuid, Short sh, Short sh2) {
        return new zzcuz(3, new zzl(uuid, sh, sh2).getBytes());
    }

    public static zzcuz zzau(String str, String str2) {
        String valueOf = String.valueOf(str);
        if (str2 == null) {
            str2 = "";
        }
        String valueOf2 = String.valueOf(str2);
        return new zzcuz(2, new zzg(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).getBytes());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzkcn);
        zzbgo.zza(parcel, 2, this.zzkco, false);
        zzbgo.zza(parcel, 3, this.zzkcp);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }
}
