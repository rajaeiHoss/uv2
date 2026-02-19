package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;

@zzabh
public class zzko extends zzbgl {
    public static final Parcelable.Creator<zzko> CREATOR = new zzkp();
    public final int height;
    public final int heightPixels;
    public final int width;
    public final int widthPixels;
    public final String zzbia;
    public final boolean zzbib;
    public final zzko[] zzbic;
    public final boolean zzbid;
    public final boolean zzbie;
    public boolean zzbif;

    public zzko() {
        this("interstitial_mb", 0, 0, true, 0, 0, (zzko[]) null, false, false, false);
    }

    public zzko(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzko(android.content.Context r14, com.google.android.gms.ads.AdSize[] r15) {
        /*
            r13 = this;
            r13.<init>()
            r0 = 0
            r1 = r15[r0]
            r13.zzbib = r0
            boolean r2 = r1.isFluid()
            r13.zzbie = r2
            if (r2 == 0) goto L_0x001f
            com.google.android.gms.ads.AdSize r3 = com.google.android.gms.ads.AdSize.BANNER
            int r3 = r3.getWidth()
            r13.width = r3
            com.google.android.gms.ads.AdSize r3 = com.google.android.gms.ads.AdSize.BANNER
            int r3 = r3.getHeight()
            goto L_0x0029
        L_0x001f:
            int r3 = r1.getWidth()
            r13.width = r3
            int r3 = r1.getHeight()
        L_0x0029:
            r13.height = r3
            int r3 = r13.width
            r4 = -1
            r5 = 1
            if (r3 != r4) goto L_0x0033
            r3 = 1
            goto L_0x0034
        L_0x0033:
            r3 = 0
        L_0x0034:
            int r4 = r13.height
            r6 = -2
            if (r4 != r6) goto L_0x003b
            r4 = 1
            goto L_0x003c
        L_0x003b:
            r4 = 0
        L_0x003c:
            android.content.res.Resources r6 = r14.getResources()
            android.util.DisplayMetrics r6 = r6.getDisplayMetrics()
            if (r3 == 0) goto L_0x007d
            com.google.android.gms.internal.zzlc.zzij()
            boolean r7 = com.google.android.gms.internal.zzako.zzbf(r14)
            if (r7 == 0) goto L_0x0063
            com.google.android.gms.internal.zzlc.zzij()
            boolean r7 = com.google.android.gms.internal.zzako.zzbg(r14)
            if (r7 == 0) goto L_0x0063
            int r7 = r6.widthPixels
            com.google.android.gms.internal.zzlc.zzij()
            int r8 = com.google.android.gms.internal.zzako.zzbh(r14)
            int r7 = r7 - r8
            goto L_0x0065
        L_0x0063:
            int r7 = r6.widthPixels
        L_0x0065:
            r13.widthPixels = r7
            int r7 = r13.widthPixels
            float r7 = (float) r7
            float r8 = r6.density
            float r7 = r7 / r8
            double r7 = (double) r7
            int r9 = (int) r7
            double r10 = (double) r9
            double r7 = r7 - r10
            r10 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r12 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x008a
            int r9 = r9 + 1
            goto L_0x008a
        L_0x007d:
            int r9 = r13.width
            com.google.android.gms.internal.zzlc.zzij()
            int r7 = r13.width
            int r7 = com.google.android.gms.internal.zzako.zza((android.util.DisplayMetrics) r6, (int) r7)
            r13.widthPixels = r7
        L_0x008a:
            if (r4 == 0) goto L_0x0091
            int r7 = zzd(r6)
            goto L_0x0093
        L_0x0091:
            int r7 = r13.height
        L_0x0093:
            com.google.android.gms.internal.zzlc.zzij()
            int r6 = com.google.android.gms.internal.zzako.zza((android.util.DisplayMetrics) r6, (int) r7)
            r13.heightPixels = r6
            if (r3 != 0) goto L_0x00ab
            if (r4 == 0) goto L_0x00a1
            goto L_0x00ab
        L_0x00a1:
            if (r2 == 0) goto L_0x00a6
            java.lang.String r1 = "320x50_mb"
            goto L_0x00c6
        L_0x00a6:
            java.lang.String r1 = r1.toString()
            goto L_0x00c6
        L_0x00ab:
            r1 = 26
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            r2.append(r9)
            java.lang.String r1 = "x"
            r2.append(r1)
            r2.append(r7)
            java.lang.String r1 = "_as"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x00c6:
            r13.zzbia = r1
            int r1 = r15.length
            if (r1 <= r5) goto L_0x00e2
            int r1 = r15.length
            com.google.android.gms.internal.zzko[] r1 = new com.google.android.gms.internal.zzko[r1]
            r13.zzbic = r1
            r1 = 0
        L_0x00d1:
            int r2 = r15.length
            if (r1 >= r2) goto L_0x00e5
            com.google.android.gms.internal.zzko[] r2 = r13.zzbic
            com.google.android.gms.internal.zzko r3 = new com.google.android.gms.internal.zzko
            r4 = r15[r1]
            r3.<init>((android.content.Context) r14, (com.google.android.gms.ads.AdSize) r4)
            r2[r1] = r3
            int r1 = r1 + 1
            goto L_0x00d1
        L_0x00e2:
            r14 = 0
            r13.zzbic = r14
        L_0x00e5:
            r13.zzbid = r0
            r13.zzbif = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzko.<init>(android.content.Context, com.google.android.gms.ads.AdSize[]):void");
    }

    public zzko(zzko zzko, zzko[] zzkoArr) {
        this(zzko.zzbia, zzko.height, zzko.heightPixels, zzko.zzbib, zzko.width, zzko.widthPixels, zzkoArr, zzko.zzbid, zzko.zzbie, zzko.zzbif);
    }

    zzko(String str, int i, int i2, boolean z, int i3, int i4, zzko[] zzkoArr, boolean z2, boolean z3, boolean z4) {
        this.zzbia = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzbib = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzbic = zzkoArr;
        this.zzbid = z2;
        this.zzbie = z3;
        this.zzbif = z4;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzko zzf(Context context) {
        return new zzko("320x50_mb", 0, 0, false, 0, 0, (zzko[]) null, true, false, false);
    }

    public static zzko zzib() {
        return new zzko("reward_mb", 0, 0, true, 0, 0, (zzko[]) null, false, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzbia, false);
        zzbgo.zzc(parcel, 3, this.height);
        zzbgo.zzc(parcel, 4, this.heightPixels);
        zzbgo.zza(parcel, 5, this.zzbib);
        zzbgo.zzc(parcel, 6, this.width);
        zzbgo.zzc(parcel, 7, this.widthPixels);
        zzbgo.zza(parcel, 8, this.zzbic, i, false);
        zzbgo.zza(parcel, 9, this.zzbid);
        zzbgo.zza(parcel, 10, this.zzbie);
        zzbgo.zza(parcel, 11, this.zzbif);
        zzbgo.zzai(parcel, zze);
    }

    public final AdSize zzic() {
        return zzb.zza(this.width, this.height, this.zzbia);
    }
}
