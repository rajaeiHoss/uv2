package com.google.android.gms.internal;

@zzabh
public abstract class zzahs implements zzajb<zzalt> {
    /* access modifiers changed from: private */
    public volatile Thread zzdeq;
    private boolean zzder = false;
    private final Runnable zzy = new zzaht(this);

    public zzahs() {
    }

    public zzahs(boolean z) {
    }

    public final void cancel() {
        onStop();
        if (this.zzdeq != null) {
            this.zzdeq.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzdo();

    public final zzalt zzns() {
        return this.zzder ? zzaid.zza(1, this.zzy) : zzaid.zzb(this.zzy);
    }

    public final zzalt zzqj() {
        return this.zzder ? zzaid.zza(1, this.zzy) : zzaid.zzb(this.zzy);
    }
}
