package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zzau extends zzay {
    private /* synthetic */ zzao zzfxt;
    private final ArrayList<Api.zze> zzfxz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzau(zzao zzao, ArrayList<Api.zze> arrayList) {
        super(zzao, (zzap) null);
        this.zzfxt = zzao;
        this.zzfxz = arrayList;
    }

    public final void zzajj() {
        this.zzfxt.zzfxd.zzfvq.zzfyk = this.zzfxt.zzajp();
        ArrayList arrayList = this.zzfxz;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Api.zze) obj).zza(this.zzfxt.zzfxp, this.zzfxt.zzfxd.zzfvq.zzfyk);
        }
    }
}
