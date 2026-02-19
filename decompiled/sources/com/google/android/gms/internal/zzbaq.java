package com.google.android.gms.internal;

import android.view.ViewGroup;

final class zzbaq implements Runnable {
    private /* synthetic */ zzbao zzfbt;

    zzbaq(zzbao zzbao) {
        this.zzfbt = zzbao;
    }

    public final void run() {
        if (this.zzfbt.zzfbs.zzfbr) {
            ((ViewGroup) this.zzfbt.zzfbs.mActivity.getWindow().getDecorView()).removeView(this.zzfbt.zzfbs);
            if (this.zzfbt.zzfbs.zzfba != null) {
                this.zzfbt.zzfbs.zzfba.onOverlayDismissed();
            }
            this.zzfbt.zzfbs.reset();
        }
    }
}
