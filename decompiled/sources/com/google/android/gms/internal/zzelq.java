package com.google.android.gms.internal;

import java.util.ArrayList;

final class zzelq implements Runnable {
    private /* synthetic */ ArrayList zznmg;
    private /* synthetic */ zzelp zznmh;

    zzelq(zzelp zzelp, ArrayList arrayList) {
        this.zznmh = zzelp;
        this.zznmg = arrayList;
    }

    public final void run() {
        ArrayList arrayList = this.zznmg;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            zzell zzell = (zzell) obj;
            if (this.zznmh.zzmxz.zzcbu()) {
                zzemm zza = this.zznmh.zzmxz;
                String valueOf = String.valueOf(zzell.toString());
                zza.zzb(valueOf.length() != 0 ? "Raising ".concat(valueOf) : new String("Raising "), (Throwable) null, new Object[0]);
            }
            zzell.zzcal();
        }
    }
}
