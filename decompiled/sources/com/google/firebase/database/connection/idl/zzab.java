package com.google.firebase.database.connection.idl;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzeex;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class zzab extends zzx {
    private /* synthetic */ zzeex zznda;

    zzab(zzeex zzeex) {
        this.zznda = zzeex;
    }

    public final void onDisconnect() {
        this.zznda.onDisconnect();
    }

    public final void zza(List<String> list, IObjectWrapper iObjectWrapper, boolean z, long j) {
        this.zznda.zza(list, zzn.zzy(iObjectWrapper), z, IPersistentConnectionImpl.zzbr(j));
    }

    public final void zza(List<String> list, List<zzak> list2, IObjectWrapper iObjectWrapper, long j) {
        List list3 = (List) zzn.zzy(iObjectWrapper);
        ArrayList arrayList = new ArrayList(list2.size());
        for (int i = 0; i < list2.size(); i++) {
            arrayList.add(zzak.zza(list2.get(i), list3.get(i)));
        }
        this.zznda.zza(list, arrayList, IPersistentConnectionImpl.zzbr(j));
    }

    public final void zzah(IObjectWrapper iObjectWrapper) {
        this.zznda.zzak((Map) zzn.zzy(iObjectWrapper));
    }

    public final void zzbwt() {
        this.zznda.zzbwt();
    }

    public final void zzcs(boolean z) {
        this.zznda.zzcs(z);
    }
}
