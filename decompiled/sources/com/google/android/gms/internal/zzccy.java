package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzccy {
    private int zzcc = 0;
    private HashMap<String, Integer> zzidt = new HashMap<>();

    public final zzccw zzavo() {
        return new zzccw(this.zzcc, this.zzidt);
    }

    public final zzccy zzdx(int i) {
        this.zzcc = i;
        return this;
    }

    public final zzccy zzw(String str, int i) {
        boolean z = true;
        if (!(i == 0 || i == 1 || i == 2 || i == 3)) {
            z = false;
        }
        if (z) {
            this.zzidt.put(str, Integer.valueOf(i));
        }
        return this;
    }
}
