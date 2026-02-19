package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzbju {
    private final zzfff zzgmy;

    private zzbju(zzfff zzfff) {
        this.zzgmy = (zzfff) zzbq.checkNotNull(zzfff);
    }

    private static zzfff zza(int i, long j, int i2) {
        zzfff zzfff = new zzfff();
        zzfff.zzpkl = i;
        zzfff.zzpkm = j;
        switch (i) {
            case 1:
            case 2:
            case 3:
                zzfff.zzpka = i2;
                break;
            case 4:
            case 5:
            case 6:
                zzfff.zzpkb = i2;
                break;
            case 7:
            case 8:
            case 9:
                zzfff.zzpkc = i2;
                break;
            case 10:
            case 11:
            case 12:
                zzfff.zzpkd = i2;
                break;
            case 13:
            case 14:
            case 15:
                zzfff.zzpke = i2;
                break;
            case 16:
            case 17:
            case 18:
                zzfff.zzpkf = i2;
                break;
            default:
                zzfi.zza("AudioStateFenceStub", "Unknown trigger type=%s", (Object) Integer.valueOf(i));
                break;
        }
        return zzfff;
    }

    public static zzbju zzaov() {
        return new zzbju(zza(2, 3000, 0));
    }

    public static zzbju zzaow() {
        return new zzbju(zza(3, 3000, 0));
    }

    public static zzbju zzco(int i) {
        return new zzbju(zza(1, 0, i));
    }

    public final zzfff zzaox() {
        return this.zzgmy;
    }
}
