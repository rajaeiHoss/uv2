package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public final class zzeib {
    private static final zzeib zznhe = new zzeib();
    /* access modifiers changed from: private */
    public final Map<zzegm, Map<String, zzegx>> zznhf = new HashMap();

    public static zzegx zza(zzegm zzegm, zzeia zzeia, FirebaseDatabase firebaseDatabase) throws DatabaseException {
        return zznhe.zzb(zzegm, zzeia, firebaseDatabase);
    }

    private final zzegx zzb(zzegm zzegm, zzeia zzeia, FirebaseDatabase firebaseDatabase) throws DatabaseException {
        zzegx zzegx;
        zzegm.zzbxu();
        String str = zzeia.host;
        String str2 = zzeia.zzkal;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 9 + String.valueOf(str2).length());
        sb.append("https://");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        String sb2 = sb.toString();
        synchronized (this.zznhf) {
            if (!this.zznhf.containsKey(zzegm)) {
                this.zznhf.put(zzegm, new HashMap());
            }
            Map map = this.zznhf.get(zzegm);
            if (!map.containsKey(sb2)) {
                zzegx = new zzegx(zzeia, zzegm, firebaseDatabase);
                map.put(sb2, zzegx);
            } else {
                throw new IllegalStateException("createLocalRepo() called for existing repo.");
            }
        }
        return zzegx;
    }

    public static void zzd(zzegm zzegm) {
        zzeib zzeib = zznhe;
        zzeig zzeig = zzegm.zznek;
        if (zzeig != null) {
            zzeig.zzp(new zzeie(zzeib, zzegm));
        }
    }

    public static void zze(zzegm zzegm) {
        zzeib zzeib = zznhe;
        zzeig zzeig = zzegm.zznek;
        if (zzeig != null) {
            zzeig.zzp(new zzeif(zzeib, zzegm));
        }
    }

    public static void zzk(zzegx zzegx) {
        zzegx.zzp(new zzeic(zzegx));
    }

    public static void zzl(zzegx zzegx) {
        zzegx.zzp(new zzeid(zzegx));
    }
}
