package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zza {
    private static String[] zzids = {"key", FirebaseAnalytics.Param.VALUE};
    private static final ConcurrentHashMap<Uri, zza> zzkft = new ConcurrentHashMap<>();
    private final Uri uri;
    private final ContentResolver zzkfu;
    private final ContentObserver zzkfv;
    private final Object zzkfw = new Object();
    private volatile Map<String, String> zzkfx;

    private zza(ContentResolver contentResolver, Uri uri2) {
        this.zzkfu = contentResolver;
        this.uri = uri2;
        this.zzkfv = new zzb(this, (Handler) null);
    }

    public static zza zza(ContentResolver contentResolver, Uri uri2) {
        ConcurrentHashMap<Uri, zza> concurrentHashMap = zzkft;
        zza zza = concurrentHashMap.get(uri2);
        if (zza != null) {
            return zza;
        }
        zza zza2 = new zza(contentResolver, uri2);
        zza putIfAbsent = concurrentHashMap.putIfAbsent(uri2, zza2);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        zza2.zzkfu.registerContentObserver(zza2.uri, false, zza2.zzkfv);
        return zza2;
    }

    private final Map<String, String> zzbeg() {
        HashMap hashMap = new HashMap();
        Cursor query = this.zzkfu.query(this.uri, zzids, (String) null, (String[]) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    hashMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return hashMap;
    }

    public final Map<String, String> zzbee() {
        Map<String, String> zzbeg = PhenotypeFlag.zzh("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? zzbeg() : this.zzkfx;
        if (zzbeg == null) {
            synchronized (this.zzkfw) {
                Map<String, String> map = this.zzkfx;
                if (map == null) {
                    map = zzbeg();
                    this.zzkfx = map;
                }
            }
        }
        return zzbeg;
    }

    public final void zzbef() {
        synchronized (this.zzkfw) {
            this.zzkfx = null;
        }
    }
}
