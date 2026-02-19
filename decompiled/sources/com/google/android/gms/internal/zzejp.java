package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.zzh;

public final class zzejp extends zzegr {
    private final zzegx zzmwt;
    private final zzelu zzndx;
    private final ValueEventListener zznja;

    public zzejp(zzegx zzegx, ValueEventListener valueEventListener, zzelu zzelu) {
        this.zzmwt = zzegx;
        this.zznja = valueEventListener;
        this.zzndx = zzelu;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzejp)) {
            return false;
        }
        zzejp zzejp = (zzejp) obj;
        return zzejp.zznja.equals(this.zznja) && zzejp.zzmwt.equals(this.zzmwt) && zzejp.zzndx.equals(this.zzndx);
    }

    public final int hashCode() {
        return (((this.zznja.hashCode() * 31) + this.zzmwt.hashCode()) * 31) + this.zzndx.hashCode();
    }

    public final String toString() {
        return "ValueEventRegistration";
    }

    public final zzegr zza(zzelu zzelu) {
        return new zzejp(this.zzmwt, this.zznja, zzelu);
    }

    public final zzelk zza(zzelj zzelj, zzelu zzelu) {
        return new zzelk(zzelm.VALUE, this, zzh.zza(zzh.zza(this.zzmwt, zzelu.zzbvh()), zzelj.zzcak()), (String) null);
    }

    public final void zza(zzelk zzelk) {
        if (!zzbym()) {
            this.zznja.onDataChange(zzelk.zzcaq());
        }
    }

    public final void zza(DatabaseError databaseError) {
        this.zznja.onCancelled(databaseError);
    }

    public final boolean zza(zzelm zzelm) {
        return zzelm == zzelm.VALUE;
    }

    public final zzelu zzbxy() {
        return this.zzndx;
    }

    public final boolean zzc(zzegr zzegr) {
        return (zzegr instanceof zzejp) && ((zzejp) zzegr).zznja.equals(this.zznja);
    }
}
