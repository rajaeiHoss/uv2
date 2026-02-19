package com.google.android.gms.internal;

final class zzefa implements zzeer {
    private /* synthetic */ long zznba;
    private /* synthetic */ zzeez zznbb;

    zzefa(zzeez zzeez, long j) {
        this.zznbb = zzeez;
        this.zznba = j;
    }

    public final void onError(String str) {
        if (this.zznba == this.zznbb.zznaz.zznat) {
            zzefi unused = this.zznbb.zznaz.zznah = zzefi.Disconnected;
            zzemm zza = this.zznbb.zznaz.zzmxz;
            String valueOf = String.valueOf(str);
            zza.zzb(valueOf.length() != 0 ? "Error fetching token: ".concat(valueOf) : new String("Error fetching token: "), (Throwable) null, new Object[0]);
            this.zznbb.zznaz.zzbwx();
            return;
        }
        this.zznbb.zznaz.zzmxz.zzb("Ignoring getToken error, because this was not the latest attempt.", (Throwable) null, new Object[0]);
    }

    public final void zzpr(String str) {
        if (this.zznba != this.zznbb.zznaz.zznat) {
            this.zznbb.zznaz.zzmxz.zzb("Ignoring getToken result, because this was not the latest attempt.", (Throwable) null, new Object[0]);
        } else if (this.zznbb.zznaz.zznah == zzefi.GettingToken) {
            this.zznbb.zznaz.zzmxz.zzb("Successfully fetched token, opening connection", (Throwable) null, new Object[0]);
            this.zznbb.zznaz.zzpu(str);
        } else {
            zzeet.zzc(this.zznbb.zznaz.zznah == zzefi.Disconnected, "Expected connection state disconnected, but was %s", this.zznbb.zznaz.zznah);
            this.zznbb.zznaz.zzmxz.zzb("Not opening connection after token refresh, because connection was set to disconnected", (Throwable) null, new Object[0]);
        }
    }
}
