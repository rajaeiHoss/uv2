package com.google.firebase.database.connection.idl;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzees;
import com.google.android.gms.internal.zzeev;
import com.google.android.gms.internal.zzeew;
import com.google.android.gms.internal.zzeex;
import com.google.android.gms.internal.zzefo;
import java.util.List;
import java.util.Map;

public final class zzf implements zzeew {
    private final zzt zzncu;

    private zzf(zzt zzt) {
        this.zzncu = zzt;
    }

    private static zzah zza(zzefo zzefo) {
        return new zzh(zzefo);
    }

    public static zzf zza(Context context, zzc zzc, zzees zzees, zzeex zzeex) {
        return new zzf(IPersistentConnectionImpl.loadDynamic(context, zzc, zzees.zzbwl(), zzees.zzbwm(), zzeex));
    }

    public final void initialize() {
        try {
            this.zzncu.initialize();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void interrupt(String str) {
        try {
            this.zzncu.interrupt(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean isInterrupted(String str) {
        try {
            return this.zzncu.isInterrupted(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void purgeOutstandingWrites() {
        try {
            this.zzncu.purgeOutstandingWrites();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void refreshAuthToken() {
        try {
            this.zzncu.refreshAuthToken();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void resume(String str) {
        try {
            this.zzncu.resume(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void shutdown() {
        try {
            this.zzncu.shutdown();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, zzefo zzefo) {
        try {
            this.zzncu.onDisconnectCancel(list, zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, Object obj, zzefo zzefo) {
        try {
            this.zzncu.put(list, zzn.zzz(obj), zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, Object obj, String str, zzefo zzefo) {
        try {
            this.zzncu.compareAndPut(list, zzn.zzz(obj), str, zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, Map<String, Object> map) {
        try {
            this.zzncu.unlisten(list, zzn.zzz(map));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, Map<String, Object> map, zzeev zzeev, Long l, zzefo zzefo) {
        long j;
        zzg zzg = new zzg(this, zzeev);
        if (l != null) {
            j = l.longValue();
        } else {
            j = -1;
        }
        List<String> list2 = list;
        try {
            this.zzncu.listen(list2, zzn.zzz(map), zzg, j, zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zza(List<String> list, Map<String, Object> map, zzefo zzefo) {
        try {
            this.zzncu.merge(list, zzn.zzz(map), zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzb(List<String> list, Object obj, zzefo zzefo) {
        try {
            this.zzncu.onDisconnectPut(list, zzn.zzz(obj), zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzb(List<String> list, Map<String, Object> map, zzefo zzefo) {
        try {
            this.zzncu.onDisconnectMerge(list, zzn.zzz(map), zza(zzefo));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzpt(String str) {
        try {
            this.zzncu.refreshAuthToken2(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
