package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzdcc implements Runnable {
    private /* synthetic */ String zzkzc;
    private /* synthetic */ String zzkzd;
    private /* synthetic */ String zzkze;
    private /* synthetic */ zzdae zzkzm;
    private /* synthetic */ zzdcb zzkzn;

    zzdcc(zzdcb zzdcb, String str, String str2, String str3, zzdae zzdae) {
        this.zzkzn = zzdcb;
        this.zzkzc = str;
        this.zzkzd = str2;
        this.zzkze = str3;
        this.zzkzm = zzdae;
    }

    public final void run() {
        boolean z = true;
        try {
            if (!this.zzkzn.zzkzk.containsKey(this.zzkzc)) {
                this.zzkzn.zzkzk.put(this.zzkzc, this.zzkzn.zzkzl.zzm(this.zzkzc, this.zzkzd, this.zzkze));
            }
        } catch (Throwable th) {
            zzczq.zza("Fail to load container: ", th, this.zzkzn.mContext);
            z = false;
        }
        try {
            zzdae zzdae = this.zzkzm;
            if (zzdae != null) {
                zzdae.zza(z, this.zzkzc);
            }
        } catch (RemoteException e) {
            zzczq.zza("Error relaying callback: ", e, this.zzkzn.mContext);
        }
    }
}
