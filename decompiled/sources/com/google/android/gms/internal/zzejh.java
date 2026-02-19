package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;

final class zzejh extends zzegr {
    private zzelu zzndx;

    public zzejh(zzelu zzelu) {
        this.zzndx = zzelu;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzejh) && ((zzejh) obj).zzndx.equals(this.zzndx);
    }

    public final int hashCode() {
        return this.zzndx.hashCode();
    }

    public final zzegr zza(zzelu zzelu) {
        return new zzejh(zzelu);
    }

    public final zzelk zza(zzelj zzelj, zzelu zzelu) {
        return null;
    }

    public final void zza(zzelk zzelk) {
    }

    public final void zza(DatabaseError databaseError) {
    }

    public final boolean zza(zzelm zzelm) {
        return false;
    }

    public final zzelu zzbxy() {
        return this.zzndx;
    }

    public final boolean zzc(zzegr zzegr) {
        return zzegr instanceof zzejh;
    }
}
