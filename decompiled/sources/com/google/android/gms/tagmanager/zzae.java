package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbs;

final class zzae implements zzdi<zzbs> {
    private /* synthetic */ zzy zzkod;

    private zzae(zzy zzy) {
        this.zzkod = zzy;
    }

    /* synthetic */ zzae(zzy zzy, zzz zzz) {
        this(zzy);
    }

    @Override
    public final void onSuccess(zzbs zzbs) {
        this.zzkod.zzknu.zzbfz();
        synchronized (this.zzkod) {
            if (zzbs.zzyi == null) {
                if (this.zzkod.zzknz.zzyi == null) {
                    zzdj.e("Current resource is null; network resource is also null");
                    this.zzkod.zzbg(this.zzkod.zzknu.zzbfx());
                    return;
                }
                zzbs.zzyi = this.zzkod.zzknz.zzyi;
            }
            this.zzkod.zza(zzbs, this.zzkod.zzata.currentTimeMillis(), false);
            long j = this.zzkod.zzknh;
            StringBuilder sb = new StringBuilder(58);
            sb.append("setting refresh time to current time: ");
            sb.append(j);
            zzdj.v(sb.toString());
            if (!this.zzkod.zzbft()) {
                this.zzkod.zza(zzbs);
            }
        }
    }

    public final void zzbfu() {
    }

    public final void zzex(int i) {
        zzy zzy;
        ContainerHolder zzau;
        if (i == zzda.zzkqq) {
            this.zzkod.zzknu.zzbfy();
        }
        synchronized (this.zzkod) {
            if (!this.zzkod.isReady()) {
                if (this.zzkod.zzknx != null) {
                    zzy = this.zzkod;
                    zzau = zzy.zzknx;
                } else {
                    zzy = this.zzkod;
                    zzau = zzy.zzb(Status.zzftt);
                }
                zzy.setResult(zzau);
            }
        }
        this.zzkod.zzbg(this.zzkod.zzknu.zzbfx());
    }
}
