package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzauc extends zzbgl {
    public static final Parcelable.Creator<zzauc> CREATOR = new zzaud();
    private static int zzefl = Integer.parseInt("-1");
    private static final zzauj zzefm = new zzauk("SsbContext").zzaq(true).zzeu("blob").zzabq();
    private String zzefn;
    private zzauj zzefo;
    public final int zzefp;
    private byte[] zzefq;

    public zzauc(String str, zzauj zzauj) {
        this(str, zzauj, zzefl, (byte[]) null);
    }

    zzauc(String str, zzauj zzauj, int i, byte[] bArr) {
        String str2;
        boolean z = i == zzefl || zzaui.zzaw(i) != null;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Invalid section type ");
        sb.append(i);
        zzbq.checkArgument(z, sb.toString());
        this.zzefn = str;
        this.zzefo = zzauj;
        this.zzefp = i;
        this.zzefq = bArr;
        if (i == zzefl || zzaui.zzaw(i) != null) {
            str2 = (this.zzefn == null || this.zzefq == null) ? null : "Both content and blobContent set";
        } else {
            StringBuilder sb2 = new StringBuilder(32);
            sb2.append("Invalid section type ");
            sb2.append(i);
            str2 = sb2.toString();
        }
        if (str2 != null) {
            throw new IllegalArgumentException(str2);
        }
    }

    public zzauc(String str, zzauj zzauj, String str2) {
        this(str, zzauj, zzaui.zzet(str2), (byte[]) null);
    }

    public zzauc(byte[] bArr, zzauj zzauj) {
        this((String) null, zzauj, zzefl, bArr);
    }

    public static zzauc zzg(byte[] bArr) {
        return new zzauc(bArr, zzefm);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzefn, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzefo, i, false);
        zzbgo.zzc(parcel, 4, this.zzefp);
        zzbgo.zza(parcel, 5, this.zzefq, false);
        zzbgo.zzai(parcel, zze);
    }
}
