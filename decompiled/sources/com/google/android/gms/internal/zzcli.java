package com.google.android.gms.internal;

abstract class zzcli extends zzclh {
    private boolean initialized;

    zzcli(zzckj zzckj) {
        super(zzckj);
        this.zzjev.zzb(this);
    }

    public final void initialize() {
        if (this.initialized) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzazq()) {
            this.zzjev.zzbcb();
            this.initialized = true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isInitialized() {
        return this.initialized;
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzazq();

    /* access modifiers changed from: protected */
    public void zzbap() {
    }

    public final void zzbcf() {
        if (!this.initialized) {
            zzbap();
            this.zzjev.zzbcb();
            this.initialized = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: protected */
    public final void zzyk() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
