package com.google.firebase.auth.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgq;
import java.util.HashMap;
import java.util.Map;

public final class zzac {
    private static String EXTRA_STATUS = "com.google.firebase.auth.internal.STATUS";
    private static Map<String, String> zzmul;

    static {
        HashMap hashMap = new HashMap();
        zzmul = hashMap;
        hashMap.put("auth/no-such-provider", "NO_SUCH_PROVIDER");
        zzmul.put("auth/invalid-cert-hash", "INVALID_CERT_HASH");
        zzmul.put("auth/network-request-failed", "WEB_NETWORK_REQUEST_FAILED");
        zzmul.put("auth/web-storage-unsupported", "WEB_STORAGE_UNSUPPORTED");
    }

    public static Status getStatusFromIntent(Intent intent) {
        zzbq.checkNotNull(intent);
        zzbq.checkArgument(zzo(intent));
        return (Status) zzbgq.zza(intent, EXTRA_STATUS, Status.CREATOR);
    }

    public static void zza(Intent intent, Status status) {
        zzbgq.zza(status, intent, EXTRA_STATUS);
    }

    public static boolean zzo(Intent intent) {
        zzbq.checkNotNull(intent);
        return intent.hasExtra(EXTRA_STATUS);
    }
}
