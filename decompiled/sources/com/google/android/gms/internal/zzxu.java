package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zzb;
import java.util.Date;
import java.util.HashSet;

@zzabh
public final class zzxu {
    public static int zza(AdRequest.ErrorCode errorCode) {
        int i = zzxv.zzckn[errorCode.ordinal()];
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 3;
        }
        return 2;
    }

    public static MediationAdRequest zza(zzkk zzkk, boolean z) {
        HashSet hashSet = zzkk.zzbgx != null ? new HashSet(zzkk.zzbgx) : null;
        Date date = new Date(zzkk.zzbgv);
        int i = zzkk.zzbgw;
        return new MediationAdRequest(date, i != 1 ? i != 2 ? AdRequest.Gender.UNKNOWN : AdRequest.Gender.FEMALE : AdRequest.Gender.MALE, hashSet, z, zzkk.zzbhd);
    }

    public static AdSize zzb(zzko zzko) {
        AdSize[] adSizeArr = {AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        for (int i = 0; i < 6; i++) {
            if (adSizeArr[i].getWidth() == zzko.width && adSizeArr[i].getHeight() == zzko.height) {
                return adSizeArr[i];
            }
        }
        return new AdSize(zzb.zza(zzko.width, zzko.height, zzko.zzbia));
    }
}
