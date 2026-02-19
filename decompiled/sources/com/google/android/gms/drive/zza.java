package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbug;
import com.google.android.gms.internal.zzfls;

public class zza extends zzbgl {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private long zzgoy;
    private long zzgoz;
    private long zzgpa;
    private volatile String zzgpb = null;

    public zza(long j, long j2, long j3) {
        boolean z = true;
        zzbq.checkArgument(j != -1);
        zzbq.checkArgument(j2 != -1);
        zzbq.checkArgument(j3 == -1 ? false : z);
        this.zzgoy = j;
        this.zzgoz = j2;
        this.zzgpa = j3;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == zza.class) {
            zza zza = (zza) obj;
            return zza.zzgoz == this.zzgoz && zza.zzgpa == this.zzgpa && zza.zzgoy == this.zzgoy;
        }
        return false;
    }

    public int hashCode() {
        String valueOf = String.valueOf(this.zzgoy);
        String valueOf2 = String.valueOf(this.zzgoz);
        String valueOf3 = String.valueOf(this.zzgpa);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append(valueOf);
        sb.append(valueOf2);
        sb.append(valueOf3);
        return sb.toString().hashCode();
    }

    public String toString() {
        if (this.zzgpb == null) {
            zzbug zzbug = new zzbug();
            zzbug.versionCode = 1;
            zzbug.sequenceNumber = this.zzgoy;
            zzbug.zzgyd = this.zzgoz;
            zzbug.zzgye = this.zzgpa;
            String valueOf = String.valueOf(Base64.encodeToString(zzfls.zzc(zzbug), 10));
            this.zzgpb = valueOf.length() != 0 ? "ChangeSequenceNumber:".concat(valueOf) : new String("ChangeSequenceNumber:");
        }
        return this.zzgpb;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgoy);
        zzbgo.zza(parcel, 3, this.zzgoz);
        zzbgo.zza(parcel, 4, this.zzgpa);
        zzbgo.zzai(parcel, zze);
    }
}
