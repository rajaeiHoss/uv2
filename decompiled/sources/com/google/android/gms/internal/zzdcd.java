package com.google.android.gms.internal;

final class zzdcd implements Runnable {
    private /* synthetic */ zzdcb zzkzn;
    private /* synthetic */ zzczu zzkzo;

    zzdcd(zzdcb zzdcb, zzczu zzczu) {
        this.zzkzn = zzdcb;
        this.zzkzo = zzczu;
    }

    public final void run() {
        if (this.zzkzn.zzkzk.isEmpty()) {
            zzdal.e("TagManagerBackend emit called without loaded container.");
            return;
        }
        for (zzczg zza : this.zzkzn.zzkzk.values()) {
            zza.zza(this.zzkzo);
        }
    }
}
