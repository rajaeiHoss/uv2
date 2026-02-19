package com.google.android.gms.internal;

import java.util.List;

final class zzedm implements zzekz<Void, Void> {
    private /* synthetic */ zzekw zzmyc;
    private /* synthetic */ List zzmyd;
    private /* synthetic */ zzegu zzmye;
    private /* synthetic */ zzenn zzmyf;

    zzedm(zzedk zzedk, zzekw zzekw, List list, zzegu zzegu, zzenn zzenn) {
        this.zzmyc = zzekw;
        this.zzmyd = list;
        this.zzmye = zzegu;
        this.zzmyf = zzenn;
    }

    public final Void zza(zzegu zzegu, Void r2, Void r3) {
        if (this.zzmyc.zzaj(zzegu) != null) {
            return null;
        }
        this.zzmyd.add(new zzepa(this.zzmye.zzh(zzegu), this.zzmyf.zzan(zzegu)));
        return null;
    }
}
