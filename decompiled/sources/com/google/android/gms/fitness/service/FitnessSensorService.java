package com.google.android.gms.fitness.service;

import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.zzbyn;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzcby;
import com.google.android.gms.internal.zzcca;
import com.google.android.gms.internal.zzccd;
import java.util.List;

public abstract class FitnessSensorService extends Service {
    public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
    private zza zzhqa;

    static class zza extends zzccd {
        private final FitnessSensorService zzhqb;

        private zza(FitnessSensorService fitnessSensorService) {
            this.zzhqb = fitnessSensorService;
        }

        public final void zza(FitnessSensorServiceRequest fitnessSensorServiceRequest, zzbzt zzbzt) throws RemoteException {
            this.zzhqb.zzasl();
            zzbzt.zzn(this.zzhqb.onRegister(fitnessSensorServiceRequest) ? Status.zzftq : new Status(13));
        }

        public final void zza(zzcby zzcby, zzbyn zzbyn) throws RemoteException {
            this.zzhqb.zzasl();
            zzbyn.zza(new DataSourcesResult(this.zzhqb.onFindDataSources(zzcby.getDataTypes()), Status.zzftq));
        }

        public final void zza(zzcca zzcca, zzbzt zzbzt) throws RemoteException {
            this.zzhqb.zzasl();
            zzbzt.zzn(this.zzhqb.onUnregister(zzcca.getDataSource()) ? Status.zzftq : new Status(13));
        }
    }

    public IBinder onBind(Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        if (Log.isLoggable("FitnessSensorService", 3)) {
            String valueOf = String.valueOf(intent);
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20 + String.valueOf(name).length());
            sb.append("Intent ");
            sb.append(valueOf);
            sb.append(" received by ");
            sb.append(name);
            Log.d("FitnessSensorService", sb.toString());
        }
        return this.zzhqa.asBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.zzhqa = new zza(this);
    }

    public abstract List<DataSource> onFindDataSources(List<DataType> list);

    public abstract boolean onRegister(FitnessSensorServiceRequest fitnessSensorServiceRequest);

    public abstract boolean onUnregister(DataSource dataSource);

    /* access modifiers changed from: protected */
    public final void zzasl() throws SecurityException {
        int callingUid = Binder.getCallingUid();
        if (zzs.zzanv()) {
            ((AppOpsManager) getSystemService("appops")).checkPackage(callingUid, "com.google.android.gms");
            return;
        }
        String[] packagesForUid = getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            int length = packagesForUid.length;
            int i = 0;
            while (i < length) {
                if (!packagesForUid[i].equals("com.google.android.gms")) {
                    i++;
                } else {
                    return;
                }
            }
        }
        throw new SecurityException("Unauthorized caller");
    }
}
