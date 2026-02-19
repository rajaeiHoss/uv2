package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.zzs;

@zzabh
public final class zzala extends zzbgl {
    public static final Parcelable.Creator<zzala> CREATOR = new zzalb();
    public String zzcu;
    public int zzdiz;
    public int zzdja;
    public boolean zzdjb;
    public boolean zzdjc;

    public zzala(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzala(int i, int i2, boolean z, boolean z2) {
        this((int) zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE, i2, true, false, z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzala(int r9, int r10, boolean r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            if (r11 == 0) goto L_0x0005
            java.lang.String r12 = "0"
            goto L_0x0007
        L_0x0005:
            java.lang.String r12 = "1"
        L_0x0007:
            int r0 = r12.length()
            int r0 = r0 + 36
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "afma-sdk-a-v"
            r1.append(r0)
            r1.append(r9)
            java.lang.String r0 = "."
            r1.append(r0)
            r1.append(r10)
            r1.append(r0)
            r1.append(r12)
            java.lang.String r3 = r1.toString()
            r2 = r8
            r4 = r9
            r5 = r10
            r6 = r11
            r7 = r13
            r2.<init>((java.lang.String) r3, (int) r4, (int) r5, (boolean) r6, (boolean) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzala.<init>(int, int, boolean, boolean, boolean):void");
    }

    zzala(String str, int i, int i2, boolean z, boolean z2) {
        this.zzcu = str;
        this.zzdiz = i;
        this.zzdja = i2;
        this.zzdjb = z;
        this.zzdjc = z2;
    }

    public static zzala zzse() {
        return new zzala(12211278, 12211278, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzcu, false);
        zzbgo.zzc(parcel, 3, this.zzdiz);
        zzbgo.zzc(parcel, 4, this.zzdja);
        zzbgo.zza(parcel, 5, this.zzdjb);
        zzbgo.zza(parcel, 6, this.zzdjc);
        zzbgo.zzai(parcel, zze);
    }
}
