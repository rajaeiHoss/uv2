package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.games.GamesStatusCodes;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

public final class zzbis implements zzbik {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Pattern zzgmb = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    private static final Pattern zzgmc = Pattern.compile("^(0|false|f|no|n|off|)$", 2);

    /* access modifiers changed from: private */
    public static HashMap<String, TreeMap<String, byte[]>> zza(zzbjh zzbjh) {
        DataHolder zzaon;
        if (zzbjh == null || (zzaon = zzbjh.zzaon()) == null) {
            return null;
        }
        zzbjn zzbjnVar = (zzbjn) new zzd(zzaon, zzbjn.CREATOR).get(0);
        zzbjh.zzaop();
        HashMap<String, TreeMap<String, byte[]>> hashMap = new HashMap<>();
        for (String str : zzbjnVar.zzaor().keySet()) {
            TreeMap treeMap = new TreeMap();
            hashMap.put(str, treeMap);
            Bundle bundle = zzbjnVar.zzaor().getBundle(str);
            for (String str2 : bundle.keySet()) {
                treeMap.put(str2, bundle.getByteArray(str2));
            }
        }
        return hashMap;
    }

    static List<byte[]> zzb(zzbjh zzbjh) {
        DataHolder zzaoo;
        if (zzbjh == null || (zzaoo = zzbjh.zzaoo()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (zzbjb payload : (Iterable<zzbjb>) (Iterable) new zzd(zzaoo, zzbjb.CREATOR)) {
            arrayList.add(payload.getPayload());
        }
        zzbjh.zzaoq();
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static Status zzcm(int i) {
        String str;
        if (i == -6508) {
            str = "SUCCESS_CACHE_STALE";
        } else if (i == 6507) {
            str = "FETCH_THROTTLED_STALE";
        } else if (i == -6506) {
            str = "SUCCESS_CACHE";
        } else if (i != -6505) {
            switch (i) {
                case GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_PARTICIPANT_STATE:
                    str = "NOT_AUTHORIZED_TO_FETCH";
                    break;
                case GamesStatusCodes.STATUS_MATCH_ERROR_INACTIVE_MATCH:
                    str = "ANOTHER_FETCH_INFLIGHT";
                    break;
                case GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_STATE:
                    str = "FETCH_THROTTLED";
                    break;
                case GamesStatusCodes.STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION:
                    str = "NOT_AVAILABLE";
                    break;
                case GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS:
                    str = "FAILURE_CACHE";
                    break;
                default:
                    str = CommonStatusCodes.getStatusCodeString(i);
                    break;
            }
        } else {
            str = "SUCCESS_FRESH";
        }
        return new Status(i, str);
    }

    public final PendingResult<zzbio> zza(GoogleApiClient googleApiClient, zzbim zzbim) {
        if (googleApiClient == null || zzbim == null) {
            return null;
        }
        return googleApiClient.zzd(new zzbit(this, googleApiClient, zzbim));
    }
}
