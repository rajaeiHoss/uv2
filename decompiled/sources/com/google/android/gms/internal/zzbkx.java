package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.TimeZone;

public final class zzbkx {
    private final zzfgb zzgnw;

    private zzbkx(zzfgb zzfgb) {
        this.zzgnw = (zzfgb) zzbq.checkNotNull(zzfgb);
    }

    public static zzbkx zza(int i, TimeZone timeZone, long j, long j2) {
        boolean z = false;
        zzbq.checkArgument(i != 1);
        zzbq.checkArgument(j >= 0);
        zzbq.checkArgument(j <= 86400000);
        zzbq.checkArgument(j2 >= 0);
        zzbq.checkArgument(j2 <= 86400000);
        if (j <= j2) {
            z = true;
        }
        zzbq.checkArgument(z);
        return new zzbkx(zzc(i, timeZone, j, j2));
    }

    public static zzbkx zzb(int i, TimeZone timeZone, long j, long j2) {
        int i2 = 6;
        boolean z = false;
        zzbq.checkArgument(i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7);
        zzbq.checkArgument(j >= 0);
        zzbq.checkArgument(j <= 86400000);
        zzbq.checkArgument(j2 >= 0);
        zzbq.checkArgument(j2 <= 86400000);
        if (j <= j2) {
            z = true;
        }
        zzbq.checkArgument(z);
        switch (i) {
            case 1:
                i2 = 5;
                break;
            case 2:
                break;
            case 3:
                i2 = 7;
                break;
            case 4:
                i2 = 8;
                break;
            case 5:
                i2 = 9;
                break;
            case 6:
                i2 = 10;
                break;
            case 7:
                i2 = 11;
                break;
            default:
                return null;
        }
        return new zzbkx(zzc(i2, timeZone, j, j2));
    }

    private static zzfgb zzc(int i, TimeZone timeZone, long j, long j2) {
        zzfgb zzfgb = new zzfgb();
        zzfgb.zzpkl = i;
        if (i != 1) {
            if (timeZone == null || TextUtils.isEmpty(timeZone.getID())) {
                zzfgb.zzpmz = true;
                zzfgb.zzhhl = j;
                zzfgb.zzpmx = j2;
                return zzfgb;
            }
            zzfgb.zzpmw = timeZone.getID();
        }
        zzfgb.zzpmz = false;
        zzfgb.zzhhl = j;
        zzfgb.zzpmx = j2;
        return zzfgb;
    }

    public static zzbkx zze(long j, long j2) {
        boolean z = true;
        zzbq.checkArgument(j >= 0);
        zzbq.checkArgument(j2 >= 0);
        if (j > j2) {
            z = false;
        }
        zzbq.checkArgument(z);
        return new zzbkx(zzc(1, (TimeZone) null, j, j2));
    }

    public final zzfgb zzapb() {
        return this.zzgnw;
    }
}
