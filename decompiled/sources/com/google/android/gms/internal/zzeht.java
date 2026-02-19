package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;

final class zzeht implements zzefo {
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ zzejn zzngh;

    zzeht(zzegx zzegx, zzejn zzejn) {
        this.zznfo = zzegx;
        this.zzngh = zzejn;
    }

    public final void zzbc(String str, String str2) {
        DatabaseError zzbe = zzegx.zzbd(str, str2);
        this.zznfo.zza("Persisted write", this.zzngh.zzbvh(), zzbe);
        this.zznfo.zza(this.zzngh.zzbzh(), this.zzngh.zzbvh(), zzbe);
    }
}
