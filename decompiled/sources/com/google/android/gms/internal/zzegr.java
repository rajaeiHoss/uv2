package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class zzegr {
    private AtomicBoolean zzner = new AtomicBoolean(false);
    private zzegs zznes;
    private boolean zznet = false;

    public abstract zzegr zza(zzelu zzelu);

    public abstract zzelk zza(zzelj zzelj, zzelu zzelu);

    public final void zza(zzegs zzegs) {
        this.zznes = zzegs;
    }

    public abstract void zza(zzelk zzelk);

    public abstract void zza(DatabaseError databaseError);

    public abstract boolean zza(zzelm zzelm);

    public abstract zzelu zzbxy();

    public final void zzbyl() {
        zzegs zzegs;
        if (this.zzner.compareAndSet(false, true) && (zzegs = this.zznes) != null) {
            zzegs.zzd(this);
            this.zznes = null;
        }
    }

    public final boolean zzbym() {
        return this.zzner.get();
    }

    public abstract boolean zzc(zzegr zzegr);

    public final void zzcv(boolean z) {
        this.zznet = true;
    }
}
