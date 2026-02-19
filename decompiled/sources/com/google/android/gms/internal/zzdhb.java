package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Locale;

public final class zzdhb implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return new zzdkc("");
        }
        String language = locale.getLanguage();
        return language == null ? new zzdkc("") : new zzdkc(language.toLowerCase());
    }
}
