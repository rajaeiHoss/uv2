package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzeex;
import com.google.android.gms.internal.zzefn;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class zzac implements zzeex {
    private /* synthetic */ zzw zzndb;

    zzac(zzw zzw) {
        this.zzndb = zzw;
    }

    public final void onDisconnect() {
        try {
            this.zzndb.onDisconnect();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, Object obj, boolean z, Long l) {
        try {
            this.zzndb.zza(list, zzn.zzz(obj), z, IPersistentConnectionImpl.zza(l));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, List<zzefn> list2, Long l) {
        ArrayList arrayList = new ArrayList(list2.size());
        ArrayList arrayList2 = new ArrayList(list2.size());
        for (zzefn next : list2) {
            arrayList.add(new zzak(next.zzbxj(), next.zzbxk()));
            arrayList2.add(next.zzbxl());
        }
        try {
            this.zzndb.zza(list, (List<zzak>) arrayList, zzn.zzz(arrayList2), IPersistentConnectionImpl.zza(l));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzak(Map<String, Object> map) {
        try {
            this.zzndb.zzah(zzn.zzz(map));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzbwt() {
        try {
            this.zzndb.zzbwt();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzcs(boolean z) {
        try {
            this.zzndb.zzcs(z);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
