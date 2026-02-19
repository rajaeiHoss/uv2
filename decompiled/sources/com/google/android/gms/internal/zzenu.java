package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class zzenu {
    private final zzegu zznpl;
    private final zzegu zznpm;
    private final zzenn zznpn;

    public zzenu(zzefn zzefn) {
        List<String> zzbxj = zzefn.zzbxj();
        zzegu zzegu = null;
        this.zznpl = zzbxj != null ? new zzegu(zzbxj) : null;
        List<String> zzbxk = zzefn.zzbxk();
        this.zznpm = zzbxk != null ? new zzegu(zzbxk) : zzegu;
        this.zznpn = zzenq.zza(zzefn.zzbxl(), zzene.zzcco());
    }

    private final zzenn zzb(zzegu zzegu, zzenn zzenn, zzenn zzenn2) {
        zzegu zzegu2 = this.zznpl;
        int zzj = zzegu2 == null ? 1 : zzegu.compareTo(zzegu2);
        zzegu zzegu3 = this.zznpm;
        int zzj2 = zzegu3 == null ? -1 : zzegu.compareTo(zzegu3);
        zzegu zzegu4 = this.zznpl;
        int i = 0;
        boolean z = zzegu4 != null && zzegu.zzi(zzegu4);
        zzegu zzegu5 = this.zznpm;
        boolean z2 = zzegu5 != null && zzegu.zzi(zzegu5);
        if (zzj > 0 && zzj2 < 0 && !z2) {
            return zzenn2;
        }
        if (zzj > 0 && z2 && zzenn2.zzccd()) {
            return zzenn2;
        }
        if (zzj > 0 && zzj2 == 0) {
            return zzenn.zzccd() ? zzene.zzcco() : zzenn;
        }
        if (!z && !z2) {
            return zzenn;
        }
        HashSet hashSet = new HashSet();
        Iterator it = zzenn.iterator();
        while (it.hasNext()) {
            hashSet.add(((zzenm) it.next()).zzccx());
        }
        Iterator it2 = zzenn2.iterator();
        while (it2.hasNext()) {
            hashSet.add(((zzenm) it2.next()).zzccx());
        }
        ArrayList arrayList = new ArrayList(hashSet.size() + 1);
        arrayList.addAll(hashSet);
        if (!zzenn2.zzcce().isEmpty() || !zzenn.zzcce().isEmpty()) {
            arrayList.add(zzemq.zzcby());
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        zzenn zzenn3 = zzenn;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzemq zzemq = (zzemq) obj;
            zzenn zzm = zzenn.zzm(zzemq);
            zzenn zzb = zzb(zzegu.zza(zzemq), zzenn.zzm(zzemq), zzenn2.zzm(zzemq));
            if (zzb != zzm) {
                zzenn3 = zzenn3.zze(zzemq, zzb);
            }
        }
        return zzenn3;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zznpl);
        String valueOf2 = String.valueOf(this.zznpm);
        String valueOf3 = String.valueOf(this.zznpn);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 55 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("RangeMerge{optExclusiveStart=");
        sb.append(valueOf);
        sb.append(", optInclusiveEnd=");
        sb.append(valueOf2);
        sb.append(", snap=");
        sb.append(valueOf3);
        sb.append('}');
        return sb.toString();
    }

    public final zzenn zzm(zzenn zzenn) {
        return zzb(zzegu.zzbyn(), zzenn, this.zznpn);
    }
}
