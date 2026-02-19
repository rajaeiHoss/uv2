package com.google.android.gms.internal;

final class zzagj implements zzalf<Void> {
    private /* synthetic */ zzalt zzdbl;

    zzagj(zzagf zzagf, zzalt zzalt) {
        this.zzdbl = zzalt;
    }

    public final void onSuccess(Void unused) {
        zzagf.zzday.remove(this.zzdbl);
    }

    public final void zzb(Throwable th) {
        zzagf.zzday.remove(this.zzdbl);
    }
}
