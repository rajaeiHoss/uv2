package com.google.android.gms.internal;

import java.util.Map;

final class zzdab implements Runnable {
    private /* synthetic */ String zzcrd;
    private /* synthetic */ long zzkqb;
    private /* synthetic */ zzczz zzkwt;
    private /* synthetic */ String zzkwu;
    private /* synthetic */ String zzkwv;
    private /* synthetic */ Map zzkww;
    private /* synthetic */ String zzkwx;
    private /* synthetic */ zzdaa zzkwy;

    zzdab(zzdaa zzdaa, zzczz zzczz, long j, String str, String str2, String str3, Map map, String str4) {
        this.zzkwy = zzdaa;
        this.zzkwt = zzczz;
        this.zzkqb = j;
        this.zzcrd = str;
        this.zzkwu = str2;
        this.zzkwv = str3;
        this.zzkww = map;
        this.zzkwx = str4;
    }

    public final void run() {
        if (this.zzkwy.zzkws == null) {
            zzdbe zzbje = zzdbe.zzbje();
            zzbje.zza(this.zzkwy.mContext, this.zzkwt);
            zzdac unused = this.zzkwy.zzkws = zzbje.zzbjf();
        }
        this.zzkwy.zzkws.zza(this.zzkqb, this.zzcrd, this.zzkwu, this.zzkwv, this.zzkww, this.zzkwx);
    }
}
