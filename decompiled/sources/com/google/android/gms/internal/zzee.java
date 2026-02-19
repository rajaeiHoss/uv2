package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class zzee extends zzeu {
    public zzee(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        super(zzdm, str, str2, zzba, i, 24);
    }

    private final void zzaz() {
        AdvertisingIdClient zzaq = this.zzagq.zzaq();
        if (zzaq != null) {
            try {
                AdvertisingIdClient.Info info = zzaq.getInfo();
                String zzn = zzds.zzn(info.getId());
                if (zzn != null) {
                    synchronized (this.zzakm) {
                        this.zzakm.zzfc = zzn;
                        this.zzakm.zzfe = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.zzakm.zzfd = 5;
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    public final Void call() throws Exception {
        return zzay();
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzagq.zzai()) {
            zzaz();
            return;
        }
        synchronized (this.zzakm) {
            this.zzakm.zzfc = (String) this.zzaku.invoke((Object) null, new Object[]{this.zzagq.getContext()});
        }
    }

    public final Void zzay() throws Exception {
        if (this.zzagq.isInitialized()) {
            return super.call();
        }
        if (!this.zzagq.zzai()) {
            return null;
        }
        zzaz();
        return null;
    }
}
