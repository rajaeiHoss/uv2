package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import java.util.List;

final class zzdig extends zzdid {
    private final zzdif zzlbd;
    private final List<Integer> zzlbe;
    private final int zzlbf;
    private /* synthetic */ zzdie zzlbg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdig(zzdie zzdie, int i, zzdim zzdim, zzdii zzdii, List<Integer> list, int i2, zzdif zzdif, zzczp zzczp) {
        super(i, zzdim, zzdii, zzczp);
        this.zzlbg = zzdie;
        this.zzlbd = zzdif;
        this.zzlbe = list;
        this.zzlbf = i2;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdin zzdin) {
        boolean z = false;
        if (zzdin.getStatus() == Status.zzftq) {
            String valueOf = String.valueOf(zzdin.zzbjw());
            zzdal.v(valueOf.length() != 0 ? "Container resource successfully loaded from ".concat(valueOf) : new String("Container resource successfully loaded from "));
            if (zzdin.getSource() == 0) {
                zzdio zzbju = zzdin.zzbju();
                if (!zzbju.zzbjy().zzbjp()) {
                    this.zzlbg.zza(zzdin.getStatus(), zzbju);
                    if (zzbju.zzbjx() != null && zzbju.zzbjx().length > 0) {
                        this.zzlbg.zzlba.zzd(zzbju.zzbjy().zzbjo(), zzbju.zzbjx());
                    }
                }
            }
            z = true;
        }
        if (z) {
            this.zzlbd.zza(zzdin);
            return;
        }
        String zzbjw = zzdin.zzbjw();
        String str = zzdin.getStatus().isSuccess() ? "SUCCESS" : "FAILURE";
        StringBuilder sb = new StringBuilder(String.valueOf(zzbjw).length() + 54 + str.length());
        sb.append("Cannot fetch a valid resource from ");
        sb.append(zzbjw);
        sb.append(". Response status: ");
        sb.append(str);
        zzdal.v(sb.toString());
        if (zzdin.getStatus().isSuccess()) {
            String valueOf2 = String.valueOf(zzdin.zzbjw());
            zzdal.v(valueOf2.length() != 0 ? "Response source: ".concat(valueOf2) : new String("Response source: "));
            int length = zzdin.zzbju().zzbjx().length;
            StringBuilder sb2 = new StringBuilder(26);
            sb2.append("Response size: ");
            sb2.append(length);
            zzdal.v(sb2.toString());
        }
        this.zzlbg.zza(this.zzlay, this.zzlbe, this.zzlbf + 1, this.zzlbd, this.zzkvu);
    }
}
