package com.google.android.gms.internal;

import android.content.ComponentName;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public final class zzfxe extends CustomTabsServiceConnection {
    private WeakReference<zzfxf> zzrni;

    public zzfxe(zzfxf zzfxf) {
        this.zzrni = new WeakReference<>(zzfxf);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzfxf zzfxf = (zzfxf) this.zzrni.get();
        if (zzfxf != null) {
            zzfxf.zza(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzfxf zzfxf = (zzfxf) this.zzrni.get();
        if (zzfxf != null) {
            zzfxf.zzjt();
        }
    }
}
