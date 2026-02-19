package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;

public final class zzelk implements zzell {
    private final zzegr zznlp;
    private final zzelm zznlr;
    private final DataSnapshot zznlv;
    private final String zznlw;

    public zzelk(zzelm zzelm, zzegr zzegr, DataSnapshot dataSnapshot, String str) {
        this.zznlr = zzelm;
        this.zznlp = zzegr;
        this.zznlv = dataSnapshot;
        this.zznlw = str;
    }

    private final zzegu zzbvh() {
        zzegu zzbvh = this.zznlv.getRef().zzbvh();
        return this.zznlr == zzelm.VALUE ? zzbvh : zzbvh.zzbys();
    }

    public final String toString() {
        if (this.zznlr == zzelm.VALUE) {
            String valueOf = String.valueOf(zzbvh());
            String valueOf2 = String.valueOf(this.zznlr);
            String valueOf3 = String.valueOf(this.zznlv.getValue(true));
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
            sb.append(valueOf);
            sb.append(": ");
            sb.append(valueOf2);
            sb.append(": ");
            sb.append(valueOf3);
            return sb.toString();
        }
        String valueOf4 = String.valueOf(zzbvh());
        String valueOf5 = String.valueOf(this.zznlr);
        String key = this.zznlv.getKey();
        String valueOf6 = String.valueOf(this.zznlv.getValue(true));
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf4).length() + 10 + String.valueOf(valueOf5).length() + String.valueOf(key).length() + String.valueOf(valueOf6).length());
        sb2.append(valueOf4);
        sb2.append(": ");
        sb2.append(valueOf5);
        sb2.append(": { ");
        sb2.append(key);
        sb2.append(": ");
        sb2.append(valueOf6);
        sb2.append(" }");
        return sb2.toString();
    }

    public final void zzcal() {
        this.zznlp.zza(this);
    }

    public final zzelm zzcan() {
        return this.zznlr;
    }

    public final DataSnapshot zzcaq() {
        return this.zznlv;
    }

    public final String zzcar() {
        return this.zznlw;
    }
}
