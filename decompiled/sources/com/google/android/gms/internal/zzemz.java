package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

final class zzemz {
    private StringBuilder zznoh = null;
    private Stack<zzemq> zznoi = new Stack<>();
    private int zznoj = -1;
    private int zznok;
    private boolean zznol = true;
    /* access modifiers changed from: private */
    public final List<zzegu> zznom = new ArrayList();
    /* access modifiers changed from: private */
    public final List<String> zznon = new ArrayList();
    private final zzenb zznoo;

    public zzemz(zzenb zzenb) {
        this.zznoo = zzenb;
    }

    private static void zza(StringBuilder sb, zzemq zzemq) {
        sb.append(zzepd.zzql(zzemq.asString()));
    }

    /* access modifiers changed from: private */
    public final void zzb(zzeni<?> zzeni) {
        zzcck();
        this.zznoj = this.zznok;
        this.zznoh.append(zzeni.zza(zzenp.V2));
        this.zznol = true;
        if (this.zznoo.zze(this)) {
            zzccn();
        }
    }

    private final boolean zzcch() {
        return this.zznoh != null;
    }

    private final void zzcck() {
        if (!zzcch()) {
            StringBuilder sb = new StringBuilder();
            this.zznoh = sb;
            sb.append("(");
            Iterator<zzemq> it = zzhk(this.zznok).iterator();
            while (it.hasNext()) {
                zza(this.zznoh, it.next());
                this.zznoh.append(":(");
            }
            this.zznol = false;
        }
    }

    /* access modifiers changed from: private */
    public final void zzccl() {
        this.zznok--;
        if (zzcch()) {
            this.zznoh.append(")");
        }
        this.zznol = true;
    }

    /* access modifiers changed from: private */
    public final void zzccm() {
        zzepd.zzb(this.zznok == 0, "Can't finish hashing in the middle processing a child");
        if (zzcch()) {
            zzccn();
        }
        this.zznon.add("");
    }

    private final void zzccn() {
        zzepd.zzb(zzcch(), "Can't end range without starting a range!");
        for (int i = 0; i < this.zznok; i++) {
            this.zznoh.append(")");
        }
        this.zznoh.append(")");
        zzegu zzhk = zzhk(this.zznoj);
        this.zznon.add(zzepd.zzqk(this.zznoh.toString()));
        this.zznom.add(zzhk);
        this.zznoh = null;
    }

    private final zzegu zzhk(int i) {
        zzemq[] zzemqArr = new zzemq[i];
        for (int i2 = 0; i2 < i; i2++) {
            zzemqArr[i2] = (zzemq) this.zznoi.get(i2);
        }
        return new zzegu(zzemqArr);
    }

    /* access modifiers changed from: private */
    public final void zzn(zzemq zzemq) {
        zzcck();
        if (this.zznol) {
            this.zznoh.append(",");
        }
        zza(this.zznoh, zzemq);
        this.zznoh.append(":(");
        if (this.zznok == this.zznoi.size()) {
            this.zznoi.add(zzemq);
        } else {
            this.zznoi.set(this.zznok, zzemq);
        }
        this.zznok++;
        this.zznol = false;
    }

    public final int zzcci() {
        return this.zznoh.length();
    }

    public final zzegu zzccj() {
        return zzhk(this.zznok);
    }
}
