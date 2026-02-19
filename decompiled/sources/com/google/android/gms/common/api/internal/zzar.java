package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.zzf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class zzar extends zzay {
    final /* synthetic */ zzao zzfxt;
    private final Map<Api.zze, zzaq> zzfxv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzar(zzao zzao, Map<Api.zze, zzaq> map) {
        super(zzao, (zzap) null);
        this.zzfxt = zzao;
        this.zzfxv = map;
    }

    private final int zza(Api.zze zze, Map<Api.zze, Integer> map) {
        int i;
        zzbq.checkNotNull(zze);
        zzbq.checkNotNull(map);
        if (!zze.zzahn()) {
            return 0;
        }
        if (map.containsKey(zze)) {
            return map.get(zze).intValue();
        }
        Iterator<Api.zze> it = map.keySet().iterator();
        if (it.hasNext()) {
            Api.zze next = it.next();
            next.zzahq();
            zze.zzahq();
            i = map.get(next).intValue();
        } else {
            i = -1;
        }
        if (i == -1) {
            i = zzf.zzc(this.zzfxt.mContext, zze.zzahq());
        }
        map.put(zze, Integer.valueOf(i));
        return i;
    }

    public final void zzajj() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.zze next : this.zzfxv.keySet()) {
            if (!next.zzahn() || this.zzfxv.get(next).zzfvo) {
                arrayList2.add(next);
            } else {
                arrayList.add(next);
            }
        }
        HashMap hashMap = new HashMap(this.zzfxv.size());
        int i = -1;
        int i2 = 0;
        if (!arrayList.isEmpty()) {
            ArrayList arrayList3 = arrayList;
            int size = arrayList3.size();
            while (i2 < size) {
                Object obj = arrayList3.get(i2);
                i2++;
                i = zza((Api.zze) obj, hashMap);
                if (i != 0) {
                    break;
                }
            }
        } else {
            ArrayList arrayList4 = arrayList2;
            int size2 = arrayList4.size();
            while (i2 < size2) {
                Object obj2 = arrayList4.get(i2);
                i2++;
                i = zza((Api.zze) obj2, hashMap);
                if (i == 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            this.zzfxt.zzfxd.zza((zzbj) new zzas(this, this.zzfxt, new ConnectionResult(i, (PendingIntent) null)));
            return;
        }
        if (this.zzfxt.zzfxn) {
            this.zzfxt.zzfxl.connect();
        }
        for (Api.zze next2 : this.zzfxv.keySet()) {
            zzj zzj = this.zzfxv.get(next2);
            if (!next2.zzahn() || zza(next2, hashMap) == 0) {
                next2.zza(zzj);
            } else {
                this.zzfxt.zzfxd.zza((zzbj) new zzat(this, this.zzfxt, zzj));
            }
        }
    }
}
