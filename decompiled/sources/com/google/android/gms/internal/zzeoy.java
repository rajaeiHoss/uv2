package com.google.android.gms.internal;

import java.util.Iterator;

public final class zzeoy {
    private static long zzc(zzeni<?> zzeni) {
        long j = 8;
        if (!(zzeni instanceof zzend) && !(zzeni instanceof zzenl)) {
            if (zzeni instanceof zzemp) {
                j = 4;
            } else if (zzeni instanceof zzenv) {
                j = (long) (((String) zzeni.getValue()).length() + 2);
            } else {
                String valueOf = String.valueOf(zzeni.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append("Unknown leaf node type: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return zzeni.zzcce().isEmpty() ? j : j + 24 + zzc((zzeni) zzeni.zzcce());
    }

    public static long zzn(zzenn zzenn) {
        if (zzenn.isEmpty()) {
            return 4;
        }
        if (zzenn.zzccd()) {
            return zzc((zzeni) zzenn);
        }
        long j = 1;
        Iterator it = zzenn.iterator();
        while (it.hasNext()) {
            zzenm zzenm = (zzenm) it.next();
            j = j + ((long) zzenm.zzccx().asString().length()) + 4 + zzn(zzenm.zzbve());
        }
        return !zzenn.zzcce().isEmpty() ? j + 12 + zzc((zzeni) zzenn.zzcce()) : j;
    }

    public static int zzo(zzenn zzenn) {
        int i = 0;
        if (zzenn.isEmpty()) {
            return 0;
        }
        if (zzenn.zzccd()) {
            return 1;
        }
        Iterator it = zzenn.iterator();
        while (it.hasNext()) {
            i += zzo(((zzenm) it.next()).zzbve());
        }
        return i;
    }
}
