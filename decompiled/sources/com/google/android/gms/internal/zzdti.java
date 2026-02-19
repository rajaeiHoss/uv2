package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzdti {
    private static final CopyOnWriteArrayList<zzdth> zzmeu = new CopyOnWriteArrayList<>();

    public static zzdth zzol(String str) throws GeneralSecurityException {
        Iterator<zzdth> it = zzmeu.iterator();
        while (it.hasNext()) {
            zzdth next = it.next();
            if (next.zzoj(str)) {
                return next;
            }
        }
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? "No KMS client does support: ".concat(valueOf) : new String("No KMS client does support: "));
    }
}
