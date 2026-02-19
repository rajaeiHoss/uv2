package com.google.android.gms.internal;

final class zzena implements zzenb {
    private final long zznop;

    public zzena(zzenn zzenn) {
        this.zznop = Math.max(512, (long) Math.sqrt((double) (zzeoy.zzn(zzenn) * 100)));
    }

    public final boolean zze(zzemz zzemz) {
        if (((long) zzemz.zzcci()) > this.zznop) {
            return zzemz.zzccj().isEmpty() || !zzemz.zzccj().zzbyt().equals(zzemq.zzcby());
        }
        return false;
    }
}
