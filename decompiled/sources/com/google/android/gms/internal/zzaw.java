package com.google.android.gms.internal;

import java.io.IOException;

public final class zzaw extends zzflm<zzaw> {
    private String stackTrace = null;
    public String zzcm = null;
    public Long zzcn = null;
    private String zzco = null;
    private String zzcp = null;
    private Long zzcq = null;
    private Long zzcr = null;
    private String zzcs = null;
    private Long zzct = null;
    private String zzcu = null;

    public zzaw() {
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 10:
                    this.zzcm = zzflj.readString();
                    break;
                case 16:
                    this.zzcn = Long.valueOf(zzflj.zzcyr());
                    break;
                case 26:
                    this.stackTrace = zzflj.readString();
                    break;
                case 34:
                    this.zzco = zzflj.readString();
                    break;
                case 42:
                    this.zzcp = zzflj.readString();
                    break;
                case 48:
                    this.zzcq = Long.valueOf(zzflj.zzcyr());
                    break;
                case 56:
                    this.zzcr = Long.valueOf(zzflj.zzcyr());
                    break;
                case 66:
                    this.zzcs = zzflj.readString();
                    break;
                case 72:
                    this.zzct = Long.valueOf(zzflj.zzcyr());
                    break;
                case 82:
                    this.zzcu = zzflj.readString();
                    break;
                default:
                    if (super.zza(zzflj, zzcxx)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzcm;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        Long l = this.zzcn;
        if (l != null) {
            zzflk.zzf(2, l.longValue());
        }
        String str2 = this.stackTrace;
        if (str2 != null) {
            zzflk.zzp(3, str2);
        }
        String str3 = this.zzco;
        if (str3 != null) {
            zzflk.zzp(4, str3);
        }
        String str4 = this.zzcp;
        if (str4 != null) {
            zzflk.zzp(5, str4);
        }
        Long l2 = this.zzcq;
        if (l2 != null) {
            zzflk.zzf(6, l2.longValue());
        }
        Long l3 = this.zzcr;
        if (l3 != null) {
            zzflk.zzf(7, l3.longValue());
        }
        String str5 = this.zzcs;
        if (str5 != null) {
            zzflk.zzp(8, str5);
        }
        Long l4 = this.zzct;
        if (l4 != null) {
            zzflk.zzf(9, l4.longValue());
        }
        String str6 = this.zzcu;
        if (str6 != null) {
            zzflk.zzp(10, str6);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzcm;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        Long l = this.zzcn;
        if (l != null) {
            zzq += zzflk.zzc(2, l.longValue());
        }
        String str2 = this.stackTrace;
        if (str2 != null) {
            zzq += zzflk.zzq(3, str2);
        }
        String str3 = this.zzco;
        if (str3 != null) {
            zzq += zzflk.zzq(4, str3);
        }
        String str4 = this.zzcp;
        if (str4 != null) {
            zzq += zzflk.zzq(5, str4);
        }
        Long l2 = this.zzcq;
        if (l2 != null) {
            zzq += zzflk.zzc(6, l2.longValue());
        }
        Long l3 = this.zzcr;
        if (l3 != null) {
            zzq += zzflk.zzc(7, l3.longValue());
        }
        String str5 = this.zzcs;
        if (str5 != null) {
            zzq += zzflk.zzq(8, str5);
        }
        Long l4 = this.zzct;
        if (l4 != null) {
            zzq += zzflk.zzc(9, l4.longValue());
        }
        String str6 = this.zzcu;
        return str6 != null ? zzq + zzflk.zzq(10, str6) : zzq;
    }
}
