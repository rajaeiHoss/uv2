package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public final class zzfb {
    private static zzfb zzald = new zzfb("@@ContextManagerNullAccount@@");
    private static zzfc zzale = null;
    private final String mName;

    public zzfb(String str) {
        this.mName = zzbq.zzgv(str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfb)) {
            return false;
        }
        return TextUtils.equals(this.mName, ((zzfb) obj).mName);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.mName});
    }

    public final String toString() {
        return "#account#";
    }
}
