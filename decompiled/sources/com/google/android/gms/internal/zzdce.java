package com.google.android.gms.internal;

final class zzdce implements Runnable {
    private /* synthetic */ zzdcb zzkzn;

    zzdce(zzdcb zzdcb) {
        this.zzkzn = zzdcb;
    }

    public final void run() {
        if (this.zzkzn.zzkzk.isEmpty()) {
            zzdal.zzcz("TagManagerBackend dispatch called without loaded container.");
            return;
        }
        for (zzczg dispatch : this.zzkzn.zzkzk.values()) {
            dispatch.dispatch();
        }
    }
}
