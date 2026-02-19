package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.DataLayer;
import java.util.List;

final class zzap implements zzaq {
    private /* synthetic */ DataLayer zzkor;

    zzap(DataLayer dataLayer) {
        this.zzkor = dataLayer;
    }

    public final void zzal(List<DataLayer.zza> list) {
        for (DataLayer.zza next : list) {
            this.zzkor.zzaa(DataLayer.zzn(next.zzbkr, next.mValue));
        }
        this.zzkor.zzkoq.countDown();
    }
}
