package com.google.android.gms.internal;

final class zzarc implements Runnable {
    private /* synthetic */ zzaqz zzdyj;
    private /* synthetic */ String zzdyl;
    private /* synthetic */ Runnable zzdym;

    zzarc(zzaqz zzaqz, String str, Runnable runnable) {
        this.zzdyj = zzaqz;
        this.zzdyl = str;
        this.zzdym = runnable;
    }

    public final void run() {
        this.zzdyj.zzdyh.zzei(this.zzdyl);
        Runnable runnable = this.zzdym;
        if (runnable != null) {
            runnable.run();
        }
    }
}
