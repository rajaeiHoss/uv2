package com.google.firebase.database.connection.idl;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.firebase_database.ModuleDescriptor;
import com.google.android.gms.internal.zzeeq;
import com.google.android.gms.internal.zzees;
import com.google.android.gms.internal.zzeeu;
import com.google.android.gms.internal.zzeew;
import com.google.android.gms.internal.zzeex;
import com.google.android.gms.internal.zzeey;
import com.google.android.gms.internal.zzefo;
import com.google.android.gms.internal.zzemj;
import com.google.android.gms.internal.zzemo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

public class IPersistentConnectionImpl extends zzu {
    private zzeew zzncx;

    public static zzt loadDynamic(Context context, zzc zzc, zzeeq zzeeq, ScheduledExecutorService scheduledExecutorService, zzeex zzeex) {
        try {
            zzt asInterface = zzu.asInterface(DynamiteModule.zza(context, DynamiteModule.zzhdm, ModuleDescriptor.MODULE_ID).zzhk("com.google.firebase.database.connection.idl.IPersistentConnectionImpl"));
            asInterface.setup(zzc, new zzad(zzeeq), zzn.zzz(scheduledExecutorService), new zzab(zzeex));
            return asInterface;
        } catch (DynamiteModule.zzc e) {
            throw new RuntimeException(e);
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* access modifiers changed from: private */
    public static long zza(Long l) {
        if (l == null) {
            return -1;
        }
        if (l.longValue() != -1) {
            return l.longValue();
        }
        throw new IllegalArgumentException("Tag parameter clashed with NO_TAG value");
    }

    private static zzefo zza(zzah zzah) {
        return new zzaa(zzah);
    }

    /* access modifiers changed from: private */
    public static Long zzbr(long j) {
        if (j == -1) {
            return null;
        }
        return Long.valueOf(j);
    }

    public void compareAndPut(List<String> list, IObjectWrapper iObjectWrapper, String str, zzah zzah) {
        this.zzncx.zza(list, zzn.zzy(iObjectWrapper), str, zza(zzah));
    }

    public void initialize() {
        this.zzncx.initialize();
    }

    public void interrupt(String str) {
        this.zzncx.interrupt(str);
    }

    public boolean isInterrupted(String str) {
        return this.zzncx.isInterrupted(str);
    }

    public void listen(List<String> list, IObjectWrapper iObjectWrapper, zzq zzq, long j, zzah zzah) {
        Long zzbr = zzbr(j);
        zzz zzz = new zzz(this, zzq);
        this.zzncx.zza(list, (Map) zzn.zzy(iObjectWrapper), zzz, zzbr, zza(zzah));
    }

    public void merge(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) {
        this.zzncx.zza(list, (Map<String, Object>) (Map) zzn.zzy(iObjectWrapper), zza(zzah));
    }

    public void onDisconnectCancel(List<String> list, zzah zzah) {
        this.zzncx.zza(list, zza(zzah));
    }

    public void onDisconnectMerge(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) {
        this.zzncx.zzb(list, (Map<String, Object>) (Map) zzn.zzy(iObjectWrapper), zza(zzah));
    }

    public void onDisconnectPut(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) {
        this.zzncx.zzb(list, zzn.zzy(iObjectWrapper), zza(zzah));
    }

    public void purgeOutstandingWrites() {
        this.zzncx.purgeOutstandingWrites();
    }

    public void put(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) {
        this.zzncx.zza(list, zzn.zzy(iObjectWrapper), zza(zzah));
    }

    public void refreshAuthToken() {
        this.zzncx.refreshAuthToken();
    }

    public void refreshAuthToken2(String str) {
        this.zzncx.zzpt(str);
    }

    public void resume(String str) {
        this.zzncx.resume(str);
    }

    public void setup(zzc zzc, zzk zzk, IObjectWrapper iObjectWrapper, zzw zzw) {
        zzeeu zza = zzi.zza(zzc.zzncp);
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) zzn.zzy(iObjectWrapper);
        zzac zzac = new zzac(zzw);
        zzemo zzemoLevel = zzemo.NONE;
        int i = zzc.zzncq;
        if (i == 1) {
            zzemoLevel = zzemo.DEBUG;
        } else if (i == 2) {
            zzemoLevel = zzemo.INFO;
        } else if (i == 3) {
            zzemoLevel = zzemo.WARN;
        } else if (i == 4) {
            zzemoLevel = zzemo.ERROR;
        }
        this.zzncx = new zzeey(new zzees(new zzemj(zzemoLevel, zzc.zzncr), new zzaf(zzk), scheduledExecutorService, zzc.zzmzx, zzc.zzncs, zzc.zzmzz, zzc.zznaa), zza, zzac);
    }

    public void shutdown() {
        this.zzncx.shutdown();
    }

    public void unlisten(List<String> list, IObjectWrapper iObjectWrapper) {
        this.zzncx.zza(list, (Map<String, Object>) (Map) zzn.zzy(iObjectWrapper));
    }
}
