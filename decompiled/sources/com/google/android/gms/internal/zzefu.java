package com.google.android.gms.internal;

final class zzefu implements zzeft, zzeoj {
    final /* synthetic */ zzefp zznck;
    private zzeoe zzncl;

    private zzefu(zzefp zzefp, zzeoe zzeoe) {
        this.zznck = zzefp;
        this.zzncl = zzeoe;
        zzeoe.zza((zzeoj) this);
    }

    /* synthetic */ zzefu(zzefp zzefp, zzeoe zzeoe, zzefq zzefq) {
        this(zzefp, zzeoe);
    }

    public final void close() {
        this.zzncl.close();
    }

    public final void connect() {
        try {
            this.zzncl.connect();
        } catch (zzeok e) {
            if (this.zznck.zzmxz.zzcbu()) {
                this.zznck.zzmxz.zzb("Error connecting", e, new Object[0]);
            }
            this.zzncl.close();
            try {
                this.zzncl.zzcdf();
            } catch (InterruptedException e2) {
                this.zznck.zzmxz.zze("Interrupted while shutting down websocket threads", e2);
            }
        }
    }

    public final void onClose() {
        this.zznck.zzmxn.execute(new zzefx(this));
    }

    public final void zza(zzeok zzeok) {
        this.zznck.zzmxn.execute(new zzefy(this, zzeok));
    }

    public final void zza(zzeom zzeom) {
        String text = zzeom.getText();
        if (this.zznck.zzmxz.zzcbu()) {
            zzemm zzb = this.zznck.zzmxz;
            String valueOf = String.valueOf(text);
            zzb.zzb(valueOf.length() != 0 ? "ws message: ".concat(valueOf) : new String("ws message: "), (Throwable) null, new Object[0]);
        }
        this.zznck.zzmxn.execute(new zzefw(this, text));
    }

    public final void zzbxp() {
        this.zznck.zzmxn.execute(new zzefv(this));
    }

    public final void zzpy(String str) {
        this.zzncl.zzpy(str);
    }
}
