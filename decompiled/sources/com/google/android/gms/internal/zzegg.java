package com.google.android.gms.internal;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.zzh;

public final class zzegg extends zzegr {
    private final zzegx zzmwt;
    private final ChildEventListener zzndw;
    private final zzelu zzndx;

    public zzegg(zzegx zzegx, ChildEventListener childEventListener, zzelu zzelu) {
        this.zzmwt = zzegx;
        this.zzndw = childEventListener;
        this.zzndx = zzelu;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzegg)) {
            return false;
        }
        zzegg zzegg = (zzegg) obj;
        return zzegg.zzndw.equals(this.zzndw) && zzegg.zzmwt.equals(this.zzmwt) && zzegg.zzndx.equals(this.zzndx);
    }

    public final int hashCode() {
        return (((this.zzndw.hashCode() * 31) + this.zzmwt.hashCode()) * 31) + this.zzndx.hashCode();
    }

    public final String toString() {
        return "ChildEventRegistration";
    }

    public final zzegr zza(zzelu zzelu) {
        return new zzegg(this.zzmwt, this.zzndw, zzelu);
    }

    public final zzelk zza(zzelj zzelj, zzelu zzelu) {
        return new zzelk(zzelj.zzcan(), this, zzh.zza(zzh.zza(this.zzmwt, zzelu.zzbvh().zza(zzelj.zzcam())), zzelj.zzcak()), zzelj.zzcao() != null ? zzelj.zzcao().asString() : null);
    }

    public final void zza(zzelk zzelk) {
        if (!zzbym()) {
            int i = zzegh.zzndy[zzelk.zzcan().ordinal()];
            if (i == 1) {
                this.zzndw.onChildAdded(zzelk.zzcaq(), zzelk.zzcar());
            } else if (i == 2) {
                this.zzndw.onChildChanged(zzelk.zzcaq(), zzelk.zzcar());
            } else if (i == 3) {
                this.zzndw.onChildMoved(zzelk.zzcaq(), zzelk.zzcar());
            } else if (i == 4) {
                this.zzndw.onChildRemoved(zzelk.zzcaq());
            }
        }
    }

    public final void zza(DatabaseError databaseError) {
        this.zzndw.onCancelled(databaseError);
    }

    public final boolean zza(zzelm zzelm) {
        return zzelm != zzelm.VALUE;
    }

    public final zzelu zzbxy() {
        return this.zzndx;
    }

    public final boolean zzc(zzegr zzegr) {
        return (zzegr instanceof zzegg) && ((zzegg) zzegr).zzndw.equals(this.zzndw);
    }
}
