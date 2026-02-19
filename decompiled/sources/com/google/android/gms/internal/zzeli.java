package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;

public final class zzeli implements zzell {
    private final zzegu zzmxa;
    private final zzegr zznlp;
    private final DatabaseError zznlq;

    public zzeli(zzegr zzegr, DatabaseError databaseError, zzegu zzegu) {
        this.zznlp = zzegr;
        this.zzmxa = zzegu;
        this.zznlq = databaseError;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzmxa);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
        sb.append(valueOf);
        sb.append(":CANCEL");
        return sb.toString();
    }

    public final void zzcal() {
        this.zznlp.zza(this.zznlq);
    }
}
