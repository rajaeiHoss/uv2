package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Random;

final class zzum extends zzlj {
    private final zzli zzcdx;

    zzum(zzli zzli) {
        this.zzcdx = zzli;
    }

    public final void onAdClicked() throws RemoteException {
        this.zzcdx.onAdClicked();
    }

    public final void onAdClosed() throws RemoteException {
        if (zzuv.zzlx()) {
            int intValue = ((Integer) zzlc.zzio().zzd(zzoi.zzbpz)).intValue();
            int intValue2 = ((Integer) zzlc.zzio().zzd(zzoi.zzbqa)).intValue();
            if (intValue <= 0 || intValue2 < 0) {
                zzbt.zzey().zzlg();
            } else {
                zzaij.zzdfn.postDelayed(zzun.zzcdy, (long) (intValue + new Random().nextInt(intValue2 + 1)));
            }
        }
        this.zzcdx.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzcdx.onAdFailedToLoad(i);
    }

    public final void onAdImpression() throws RemoteException {
        this.zzcdx.onAdImpression();
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.zzcdx.onAdLeftApplication();
    }

    public final void onAdLoaded() throws RemoteException {
        this.zzcdx.onAdLoaded();
    }

    public final void onAdOpened() throws RemoteException {
        this.zzcdx.onAdOpened();
    }
}
