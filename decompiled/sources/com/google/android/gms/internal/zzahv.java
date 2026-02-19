package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzahv extends zzahs {
    private Context mContext;

    zzahv(Context context) {
        this.mContext = context;
    }

    public final void onStop() {
    }

    public final void zzdo() {
        boolean z;
        try {
            z = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            zzahw.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            z = false;
        }
        zzaks.zzae(z);
        StringBuilder sb = new StringBuilder(43);
        sb.append("Update ad debug logging enablement as ");
        sb.append(z);
        zzahw.zzcz(sb.toString());
    }
}
