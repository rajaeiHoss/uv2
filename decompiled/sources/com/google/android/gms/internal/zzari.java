package com.google.android.gms.internal;

public abstract class zzari extends zzarh {
    private boolean zzdyq;

    protected zzari(zzark zzark) {
        super(zzark);
    }

    public final void initialize() {
        zzwk();
        this.zzdyq = true;
    }

    public final boolean isInitialized() {
        return this.zzdyq;
    }

    /* access modifiers changed from: protected */
    public abstract void zzwk();

    /* access modifiers changed from: protected */
    public final void zzyk() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
