package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

@zzabh
public final class zzacf extends zzbgl {
    public static final Parcelable.Creator<zzacf> CREATOR = new zzach();
    public final ApplicationInfo applicationInfo;
    public final int versionCode;
    public final String zzatw;
    public final String zzatx;
    public final zzala zzatz;
    public final zzko zzaud;
    public final zzqh zzauq;
    public final zzms zzaus;
    public final List<Integer> zzauu;
    public final List<String> zzauy;
    public final float zzaxz;
    public final boolean zzcia;
    public final Bundle zzcru;
    public final zzkk zzcrv;
    public final PackageInfo zzcrw;
    public final String zzcrx;
    public final String zzcry;
    public final String zzcrz;
    public final Bundle zzcsa;
    public final int zzcsb;
    public final Bundle zzcsc;
    public final boolean zzcsd;
    public final int zzcse;
    public final int zzcsf;
    public final String zzcsg;
    public final long zzcsh;
    public final String zzcsi;
    public final List<String> zzcsj;
    public final List<String> zzcsk;
    public final long zzcsl;
    public final String zzcsm;
    public final float zzcsn;
    public final int zzcso;
    public final int zzcsp;
    public final boolean zzcsq;
    public final boolean zzcsr;
    public final String zzcss;
    public final boolean zzcst;
    public final String zzcsu;
    public final int zzcsv;
    public final Bundle zzcsw;
    public final String zzcsx;
    public final boolean zzcsy;
    public final Bundle zzcsz;
    public final String zzcta;
    public final String zzctb;
    public final String zzctc;
    public final boolean zzctd;
    public final String zzcte;
    public final List<String> zzctf;
    public final int zzctg;
    public final boolean zzcth;
    public final boolean zzcti;
    public final boolean zzctj;

    zzacf(int i, Bundle bundle, zzkk zzkk, zzko zzko, String str, ApplicationInfo applicationInfo2, PackageInfo packageInfo, String str2, String str3, String str4, zzala zzala, Bundle bundle2, int i2, List<String> list, Bundle bundle3, boolean z, int i3, int i4, float f, String str5, long j, String str6, List<String> list2, String str7, zzqh zzqh, List<String> list3, long j2, String str8, float f2, boolean z2, int i5, int i6, boolean z3, boolean z4, String str9, String str10, boolean z5, int i7, Bundle bundle4, String str11, zzms zzms, boolean z6, Bundle bundle5, String str12, String str13, String str14, boolean z7, List<Integer> list4, String str15, List<String> list5, int i8, boolean z8, boolean z9, boolean z10) {
        this.versionCode = i;
        this.zzcru = bundle;
        this.zzcrv = zzkk;
        this.zzaud = zzko;
        this.zzatx = str;
        this.applicationInfo = applicationInfo2;
        this.zzcrw = packageInfo;
        this.zzcrx = str2;
        this.zzcry = str3;
        this.zzcrz = str4;
        this.zzatz = zzala;
        this.zzcsa = bundle2;
        this.zzcsb = i2;
        this.zzauy = list;
        this.zzcsk = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzcsc = bundle3;
        this.zzcsd = z;
        this.zzcse = i3;
        this.zzcsf = i4;
        this.zzaxz = f;
        this.zzcsg = str5;
        this.zzcsh = j;
        this.zzcsi = str6;
        this.zzcsj = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzatw = str7;
        this.zzauq = zzqh;
        this.zzcsl = j2;
        this.zzcsm = str8;
        this.zzcsn = f2;
        this.zzcst = z2;
        this.zzcso = i5;
        this.zzcsp = i6;
        this.zzcsq = z3;
        this.zzcsr = z4;
        this.zzcss = str9;
        this.zzcsu = str10;
        this.zzcia = z5;
        this.zzcsv = i7;
        this.zzcsw = bundle4;
        this.zzcsx = str11;
        this.zzaus = zzms;
        this.zzcsy = z6;
        this.zzcsz = bundle5;
        this.zzcta = str12;
        this.zzctb = str13;
        this.zzctc = str14;
        this.zzctd = z7;
        this.zzauu = list4;
        this.zzcte = str15;
        this.zzctf = list5;
        this.zzctg = i8;
        this.zzcth = z8;
        this.zzcti = z9;
        this.zzctj = z10;
    }

    private zzacf(Bundle bundle, zzkk zzkk, zzko zzko, String str, ApplicationInfo applicationInfo2, PackageInfo packageInfo, String str2, String str3, String str4, zzala zzala, Bundle bundle2, int i, List<String> list, List<String> list2, Bundle bundle3, boolean z, int i2, int i3, float f, String str5, long j, String str6, List<String> list3, String str7, zzqh zzqh, long j2, String str8, float f2, boolean z2, int i4, int i5, boolean z3, boolean z4, String str9, String str10, boolean z5, int i6, Bundle bundle4, String str11, zzms zzms, boolean z6, Bundle bundle5, String str12, String str13, String str14, boolean z7, List<Integer> list4, String str15, List<String> list5, int i7, boolean z8, boolean z9, boolean z10) {
        this(24, bundle, zzkk, zzko, str, applicationInfo2, packageInfo, str2, str3, str4, zzala, bundle2, i, list, bundle3, z, i2, i3, f, str5, j, str6, list3, str7, zzqh, list2, j2, str8, f2, z2, i4, i5, z3, z4, str9, str10, z5, i6, bundle4, str11, zzms, z6, bundle5, str12, str13, str14, z7, list4, str15, list5, i7, z8, z9, z10);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzacf(com.google.android.gms.internal.zzacg r59, long r60, java.lang.String r62, java.lang.String r63, java.lang.String r64) {
        /*
            r58 = this;
            r0 = r59
            r1 = r58
            r28 = r60
            r46 = r62
            r47 = r63
            r48 = r64
            android.os.Bundle r2 = r0.zzcru
            com.google.android.gms.internal.zzkk r3 = r0.zzcrv
            com.google.android.gms.internal.zzko r4 = r0.zzaud
            java.lang.String r5 = r0.zzatx
            android.content.pm.ApplicationInfo r6 = r0.applicationInfo
            android.content.pm.PackageInfo r7 = r0.zzcrw
            java.util.concurrent.Future<java.lang.String> r8 = r0.zzctl
            java.lang.String r15 = ""
            java.lang.Object r8 = com.google.android.gms.internal.zzali.zza(r8, r15)
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r9 = r0.zzcry
            java.lang.String r10 = r0.zzcrz
            com.google.android.gms.internal.zzala r11 = r0.zzatz
            android.os.Bundle r12 = r0.zzcsa
            int r13 = r0.zzcsb
            java.util.List<java.lang.String> r14 = r0.zzauy
            r60 = r15
            java.util.List<java.lang.String> r15 = r0.zzcsk
            r57 = r1
            r1 = r60
            r60 = r2
            android.os.Bundle r2 = r0.zzcsc
            r16 = r2
            boolean r2 = r0.zzcsd
            r17 = r2
            int r2 = r0.zzcse
            r18 = r2
            int r2 = r0.zzcsf
            r19 = r2
            float r2 = r0.zzaxz
            r20 = r2
            java.lang.String r2 = r0.zzcsg
            r21 = r2
            r61 = r3
            long r2 = r0.zzcsh
            r22 = r2
            java.lang.String r2 = r0.zzcsi
            r24 = r2
            java.util.List<java.lang.String> r2 = r0.zzcsj
            r25 = r2
            java.lang.String r2 = r0.zzatw
            r26 = r2
            com.google.android.gms.internal.zzqh r2 = r0.zzauq
            r27 = r2
            java.lang.String r2 = r0.zzcsm
            r30 = r2
            float r2 = r0.zzcsn
            r31 = r2
            boolean r2 = r0.zzcst
            r32 = r2
            int r2 = r0.zzcso
            r33 = r2
            int r2 = r0.zzcsp
            r34 = r2
            boolean r2 = r0.zzcsq
            r35 = r2
            boolean r2 = r0.zzcsr
            r36 = r2
            java.util.concurrent.Future<java.lang.String> r2 = r0.zzctk
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS
            r62 = r4
            r63 = r5
            r4 = 1
            java.lang.Object r1 = com.google.android.gms.internal.zzali.zza(r2, r1, (long) r4, (java.util.concurrent.TimeUnit) r3)
            r37 = r1
            java.lang.String r37 = (java.lang.String) r37
            java.lang.String r1 = r0.zzcsu
            r38 = r1
            boolean r1 = r0.zzcia
            r39 = r1
            int r1 = r0.zzcsv
            r40 = r1
            android.os.Bundle r1 = r0.zzcsw
            r41 = r1
            java.lang.String r1 = r0.zzcsx
            r42 = r1
            com.google.android.gms.internal.zzms r1 = r0.zzaus
            r43 = r1
            boolean r1 = r0.zzcsy
            r44 = r1
            android.os.Bundle r1 = r0.zzcsz
            r45 = r1
            boolean r1 = r0.zzctd
            r49 = r1
            java.util.List<java.lang.Integer> r1 = r0.zzauu
            r50 = r1
            java.lang.String r1 = r0.zzcte
            r51 = r1
            java.util.List<java.lang.String> r1 = r0.zzctf
            r52 = r1
            int r1 = r0.zzctg
            r53 = r1
            boolean r1 = r0.zzcth
            r54 = r1
            boolean r1 = r0.zzcti
            r55 = r1
            boolean r0 = r0.zzctj
            r56 = r0
            r2 = r60
            r3 = r61
            r4 = r62
            r5 = r63
            r1 = r57
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r24, r25, r26, r27, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzacf.<init>(com.google.android.gms.internal.zzacg, long, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, this.zzcru, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzcrv, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzaud, i, false);
        zzbgo.zza(parcel, 5, this.zzatx, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.applicationInfo, i, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzcrw, i, false);
        zzbgo.zza(parcel, 8, this.zzcrx, false);
        zzbgo.zza(parcel, 9, this.zzcry, false);
        zzbgo.zza(parcel, 10, this.zzcrz, false);
        zzbgo.zza(parcel, 11, (Parcelable) this.zzatz, i, false);
        zzbgo.zza(parcel, 12, this.zzcsa, false);
        zzbgo.zzc(parcel, 13, this.zzcsb);
        zzbgo.zzb(parcel, 14, this.zzauy, false);
        zzbgo.zza(parcel, 15, this.zzcsc, false);
        zzbgo.zza(parcel, 16, this.zzcsd);
        zzbgo.zzc(parcel, 18, this.zzcse);
        zzbgo.zzc(parcel, 19, this.zzcsf);
        zzbgo.zza(parcel, 20, this.zzaxz);
        zzbgo.zza(parcel, 21, this.zzcsg, false);
        zzbgo.zza(parcel, 25, this.zzcsh);
        zzbgo.zza(parcel, 26, this.zzcsi, false);
        zzbgo.zzb(parcel, 27, this.zzcsj, false);
        zzbgo.zza(parcel, 28, this.zzatw, false);
        zzbgo.zza(parcel, 29, (Parcelable) this.zzauq, i, false);
        zzbgo.zzb(parcel, 30, this.zzcsk, false);
        zzbgo.zza(parcel, 31, this.zzcsl);
        zzbgo.zza(parcel, 33, this.zzcsm, false);
        zzbgo.zza(parcel, 34, this.zzcsn);
        zzbgo.zzc(parcel, 35, this.zzcso);
        zzbgo.zzc(parcel, 36, this.zzcsp);
        zzbgo.zza(parcel, 37, this.zzcsq);
        zzbgo.zza(parcel, 38, this.zzcsr);
        zzbgo.zza(parcel, 39, this.zzcss, false);
        zzbgo.zza(parcel, 40, this.zzcst);
        zzbgo.zza(parcel, 41, this.zzcsu, false);
        zzbgo.zza(parcel, 42, this.zzcia);
        zzbgo.zzc(parcel, 43, this.zzcsv);
        zzbgo.zza(parcel, 44, this.zzcsw, false);
        zzbgo.zza(parcel, 45, this.zzcsx, false);
        zzbgo.zza(parcel, 46, (Parcelable) this.zzaus, i, false);
        zzbgo.zza(parcel, 47, this.zzcsy);
        zzbgo.zza(parcel, 48, this.zzcsz, false);
        zzbgo.zza(parcel, 49, this.zzcta, false);
        zzbgo.zza(parcel, 50, this.zzctb, false);
        zzbgo.zza(parcel, 51, this.zzctc, false);
        zzbgo.zza(parcel, 52, this.zzctd);
        zzbgo.zza(parcel, 53, this.zzauu, false);
        zzbgo.zza(parcel, 54, this.zzcte, false);
        zzbgo.zzb(parcel, 55, this.zzctf, false);
        zzbgo.zzc(parcel, 56, this.zzctg);
        zzbgo.zza(parcel, 57, this.zzcth);
        zzbgo.zza(parcel, 58, this.zzcti);
        zzbgo.zza(parcel, 59, this.zzctj);
        zzbgo.zzai(parcel, zze);
    }
}
