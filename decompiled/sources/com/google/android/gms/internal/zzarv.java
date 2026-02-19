package com.google.android.gms.internal;

public final class zzarv extends zzari {
    private final zzaql zzdvo = new zzaql();

    zzarv(zzark zzark) {
        super(zzark);
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
        zzya().zzwh().zzb(this.zzdvo);
        zzatu zzye = zzye();
        String zzwn = zzye.zzwn();
        if (zzwn != null) {
            this.zzdvo.setAppName(zzwn);
        }
        String zzwo = zzye.zzwo();
        if (zzwo != null) {
            this.zzdvo.setAppVersion(zzwo);
        }
    }

    public final zzaql zzzd() {
        zzyk();
        return this.zzdvo;
    }
}
