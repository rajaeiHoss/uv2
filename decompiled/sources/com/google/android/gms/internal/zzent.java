package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;

public final class zzent {
    public static zzenn zzc(zzegu zzegu, Object obj) {
        String str;
        zzenn zza = zzenq.zza(obj, zzene.zzcco());
        if (zza instanceof zzenl) {
            zza = new zzend(Double.valueOf((double) ((Long) zza.getValue()).longValue()), zzene.zzcco());
        }
        if (zzl(zza)) {
            return zza;
        }
        if (zzegu != null) {
            String valueOf = String.valueOf(zzegu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
            sb.append("Path '");
            sb.append(valueOf);
            sb.append("'");
            str = sb.toString();
        } else {
            str = "Node";
        }
        throw new DatabaseException(String.valueOf(str).concat(" contains invalid priority: Must be a string, double, ServerValue, or null"));
    }

    public static boolean zzl(zzenn zzenn) {
        if (zzenn.zzcce().isEmpty()) {
            return zzenn.isEmpty() || (zzenn instanceof zzend) || (zzenn instanceof zzenv) || (zzenn instanceof zzenc);
        }
        return false;
    }
}
