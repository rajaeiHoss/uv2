package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.maps.model.internal.zzj;
import com.google.android.gms.maps.model.internal.zzm;
import com.google.android.gms.maps.model.internal.zzn;
import java.util.ArrayList;
import java.util.List;

public final class IndoorBuilding {
    private final zzj zzjdl;
    private final zza zzjdm;

    static class zza {
        public static final zza zzjdn = new zza();

        private zza() {
        }

        public static IndoorLevel zza(zzm zzm) {
            return new IndoorLevel(zzm);
        }

        public static zzm zzbg(IBinder iBinder) {
            return zzn.zzbl(iBinder);
        }
    }

    public IndoorBuilding(zzj zzj) {
        this(zzj, zza.zzjdn);
    }

    private IndoorBuilding(zzj zzj, zza zza2) {
        this.zzjdl = (zzj) zzbq.checkNotNull(zzj, "delegate");
        this.zzjdm = (zza) zzbq.checkNotNull(zza2, "shim");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzjdl.zzb(((IndoorBuilding) obj).zzjdl);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getActiveLevelIndex() {
        try {
            return this.zzjdl.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getDefaultLevelIndex() {
        try {
            return this.zzjdl.getDefaultLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final List<IndoorLevel> getLevels() {
        try {
            List<IBinder> levels = this.zzjdl.getLevels();
            ArrayList arrayList = new ArrayList(levels.size());
            for (IBinder zzbg : levels) {
                arrayList.add(zza.zza(zza.zzbg(zzbg)));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.zzjdl.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isUnderground() {
        try {
            return this.zzjdl.isUnderground();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
