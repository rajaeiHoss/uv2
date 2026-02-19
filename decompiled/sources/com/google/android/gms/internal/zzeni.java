package com.google.android.gms.internal;

import com.google.android.gms.internal.zzeni;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public abstract class zzeni<T extends zzeni> implements zzenn {
    protected final zzenn zznob;
    private String zznoc;

    zzeni(zzenn zzenn) {
        this.zznob = zzenn;
    }

    private static int zza(zzenl zzenl, zzend zzend) {
        return Double.valueOf((double) ((Long) zzenl.getValue()).longValue()).compareTo((Double) zzend.getValue());
    }

    public final int compareTo(zzenn zzenn) {
        if (zzenn.isEmpty()) {
            return 1;
        }
        if (zzenn instanceof zzems) {
            return -1;
        }
        if ((this instanceof zzenl) && (zzenn instanceof zzend)) {
            return zza((zzenl) this, (zzend) zzenn);
        }
        if ((this instanceof zzend) && (zzenn instanceof zzenl)) {
            return zza((zzenl) zzenn, (zzend) this) * -1;
        }
        zzeni zzeni = (zzeni) zzenn;
        zzenk zzcbv = zzcbv();
        zzenk zzcbv2 = zzeni.zzcbv();
        return zzcbv.equals(zzcbv2) ? zza((T) zzeni) : zzcbv.compareTo(zzcbv2);
    }

    public final int getChildCount() {
        return 0;
    }

    public final Object getValue(boolean z) {
        if (!z || this.zznob.isEmpty()) {
            return getValue();
        }
        HashMap hashMap = new HashMap();
        hashMap.put(".value", getValue());
        hashMap.put(".priority", this.zznob.getValue());
        return hashMap;
    }

    public final boolean isEmpty() {
        return false;
    }

    public Iterator<zzenm> iterator() {
        return Collections.<zzenm>emptyList().iterator();
    }

    public String toString() {
        String obj = getValue(true).toString();
        return obj.length() <= 100 ? obj : String.valueOf(obj.substring(0, 100)).concat("...");
    }

    /* access modifiers changed from: protected */
    public abstract int zza(T t);

    public final zzenn zzan(zzegu zzegu) {
        return zzegu.isEmpty() ? this : zzegu.zzbyq().zzcca() ? this.zznob : zzene.zzcco();
    }

    /* access modifiers changed from: protected */
    public final String zzb(zzenp zzenp) {
        int i = zzenj.zznox[zzenp.ordinal()];
        if (i != 1 && i != 2) {
            String valueOf = String.valueOf(zzenp);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("Unknown hash version: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.zznob.isEmpty()) {
            return "";
        } else {
            String zza = this.zznob.zza(zzenp);
            StringBuilder sb2 = new StringBuilder(String.valueOf(zza).length() + 10);
            sb2.append("priority:");
            sb2.append(zza);
            sb2.append(":");
            return sb2.toString();
        }
    }

    public final Iterator<zzenm> zzbvr() {
        return Collections.<zzenm>emptyList().iterator();
    }

    /* access modifiers changed from: protected */
    public abstract zzenk zzcbv();

    public final String zzccc() {
        if (this.zznoc == null) {
            this.zznoc = zzepd.zzqk(zza(zzenp.V1));
        }
        return this.zznoc;
    }

    public final boolean zzccd() {
        return true;
    }

    public final zzenn zzcce() {
        return this.zznob;
    }

    public final zzenn zze(zzemq zzemq, zzenn zzenn) {
        return zzemq.zzcca() ? zzf(zzenn) : zzenn.isEmpty() ? this : zzene.zzcco().zze(zzemq, zzenn).zzf(this.zznob);
    }

    public final boolean zzk(zzemq zzemq) {
        return false;
    }

    public final zzemq zzl(zzemq zzemq) {
        return null;
    }

    public final zzenn zzl(zzegu zzegu, zzenn zzenn) {
        zzemq zzbyq = zzegu.zzbyq();
        return zzbyq == null ? zzenn : (!zzenn.isEmpty() || zzbyq.zzcca()) ? zze(zzbyq, zzene.zzcco().zzl(zzegu.zzbyr(), zzenn)) : this;
    }

    public final zzenn zzm(zzemq zzemq) {
        return zzemq.zzcca() ? this.zznob : zzene.zzcco();
    }
}
