package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.tagmanager.zzci;

final class zzdbs extends zzci {
    final /* synthetic */ zzdbo zzkyv;

    zzdbs(zzdbo zzdbo) {
        this.zzkyv = zzdbo;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        String str3 = str;
        if (!str.endsWith("+gtm")) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4);
            sb.append(str);
            sb.append("+gtm");
            this.zzkyv.zzkvr.execute(new zzdbt(this, str2, bundle, sb.toString(), j, str));
            return;
        }
    }
}
