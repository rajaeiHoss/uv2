package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaqu;
import com.google.android.gms.internal.zzaqy;
import com.google.android.gms.internal.zzark;
import java.util.ListIterator;

public class zza extends zzj<zza> {
    private final zzark zzdtw;
    private boolean zzdtx;

    public zza(zzark zzark) {
        super(zzark.zzya(), zzark.zzxx());
        this.zzdtw = zzark;
    }

    public final void enableAdvertisingIdCollection(boolean z) {
        this.zzdtx = z;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzg zzg) {
        zzaqu zzaqu = (zzaqu) zzg.zzb(zzaqu.class);
        if (TextUtils.isEmpty(zzaqu.zzxe())) {
            zzaqu.setClientId(this.zzdtw.zzyq().zzzp());
        }
        if (this.zzdtx && TextUtils.isEmpty(zzaqu.zzxf())) {
            zzaqy zzyp = this.zzdtw.zzyp();
            zzaqu.zzdw(zzyp.zzxn());
            zzaqu.zzam(zzyp.zzxg());
        }
    }

    public final void zzdk(String str) {
        zzbq.zzgv(str);
        Uri zzdl = zzb.zzdl(str);
        ListIterator<zzo> listIterator = this.zzdvi.getTransports().listIterator();
        while (listIterator.hasNext()) {
            if (zzdl.equals(listIterator.next().zzvu())) {
                listIterator.remove();
            }
        }
        this.zzdvi.getTransports().add(new zzb(this.zzdtw, str));
    }

    /* access modifiers changed from: package-private */
    public final zzark zzvr() {
        return this.zzdtw;
    }

    public final zzg zzvs() {
        zzg zzvx = this.zzdvi.zzvx();
        zzvx.zza((zzi) this.zzdtw.zzyi().zzzd());
        zzvx.zza((zzi) this.zzdtw.zzyj().zzaah());
        zzd(zzvx);
        return zzvx;
    }
}
