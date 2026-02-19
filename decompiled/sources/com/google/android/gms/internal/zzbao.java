package com.google.android.gms.internal;

import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzh;

final class zzbao implements zzh {
    final /* synthetic */ zzban zzfbs;

    zzbao(zzban zzban) {
        this.zzfbs = zzban;
    }

    public final void dismiss() {
        if (this.zzfbs.zzfbr) {
            IntroductoryOverlay.zza.zzbw(this.zzfbs.mActivity);
            this.zzfbs.zzfbq.zzf(new zzbaq(this));
        }
    }

    public final void zzaey() {
        if (this.zzfbs.zzfbr) {
            IntroductoryOverlay.zza.zzbw(this.zzfbs.mActivity);
            this.zzfbs.zzfbq.zzg(new zzbap(this));
        }
    }
}
