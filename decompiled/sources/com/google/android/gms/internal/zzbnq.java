package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.zzz;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.DriveEventService;
import com.google.android.gms.drive.events.zzd;
import com.google.android.gms.drive.events.zzj;
import com.google.android.gms.drive.events.zzl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzbnq extends zzab<zzbrk> {
    private final String zzehh;
    private final Bundle zzgpr;
    protected final boolean zzgtt;
    private volatile DriveId zzgtu;
    private volatile DriveId zzgtv;
    private volatile boolean zzgtw = false;
    private GoogleApiClient.ConnectionCallbacks zzgtx;
    private Map<DriveId, Map<ChangeListener, zzbra>> zzgty = new HashMap();
    private Map<zzd, zzbra> zzgtz = new HashMap();
    private Map<DriveId, Map<zzl, zzbra>> zzgua = new HashMap();
    private Map<DriveId, Map<zzl, zzbra>> zzgub = new HashMap();

    public zzbnq(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, Bundle bundle) {
        super(context, looper, 11, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzehh = zzr.zzami();
        this.zzgtx = connectionCallbacks;
        this.zzgpr = bundle;
        Intent intent = new Intent(DriveEventService.ACTION_HANDLE_EVENT);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        int size = queryIntentServices.size();
        if (size == 0) {
            this.zzgtt = false;
        } else if (size == 1) {
            ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
            if (serviceInfo.exported) {
                this.zzgtt = true;
                return;
            }
            String str = serviceInfo.name;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 60);
            sb.append("Drive event service ");
            sb.append(str);
            sb.append(" must be exported in AndroidManifest.xml");
            throw new IllegalStateException(sb.toString());
        } else {
            String action = intent.getAction();
            StringBuilder sb2 = new StringBuilder(String.valueOf(action).length() + 72);
            sb2.append("AndroidManifest.xml can only define one service that handles the ");
            sb2.append(action);
            sb2.append(" action");
            throw new IllegalStateException(sb2.toString());
        }
    }

    public final void disconnect() {
        if (isConnected()) {
            try {
                ((zzbrk) zzalw()).zza(new zzbms());
            } catch (RemoteException unused) {
            }
        }
        super.disconnect();
        synchronized (this.zzgty) {
            this.zzgty.clear();
        }
        synchronized (this.zzgtz) {
            this.zzgtz.clear();
        }
        synchronized (this.zzgua) {
            this.zzgua.clear();
        }
        synchronized (this.zzgub) {
            this.zzgub.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final PendingResult<Status> zza(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        zzbq.checkArgument(zzj.zza(1, driveId));
        zzbq.checkNotNull(changeListener, "listener");
        zzbq.zza(isConnected(), (Object) "Client must be connected");
        synchronized (this.zzgty) {
            Map map = this.zzgty.get(driveId);
            if (map == null) {
                map = new HashMap();
                this.zzgty.put(driveId, map);
            }
            zzbra zzbra = (zzbra) map.get(changeListener);
            if (zzbra == null) {
                zzbra = new zzbra(getLooper(), getContext(), 1, changeListener);
                map.put(changeListener, zzbra);
            } else if (zzbra.zzcw(1)) {
                zzbnk zzbnk = new zzbnk(googleApiClient, Status.zzftq);
                return zzbnk;
            }
            zzbra.zzcv(1);
            zzm zze = googleApiClient.zze(new zzbnr(this, googleApiClient, new zzbly(1, driveId), zzbra));
            return zze;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzgtu = (DriveId) bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.zzgtv = (DriveId) bundle.getParcelable("com.google.android.gms.drive.appdata_id");
            this.zzgtw = true;
        }
        super.zza(i, iBinder, bundle, i2);
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        String packageName = getContext().getPackageName();
        zzbq.checkNotNull(packageName);
        zzbq.checkState(!zzamr().zzamg().isEmpty());
        Bundle bundle = new Bundle();
        if (!packageName.equals(this.zzehh)) {
            bundle.putString("proxy_package_name", this.zzehh);
        }
        bundle.putAll(this.zzgpr);
        return bundle;
    }

    public final boolean zzacc() {
        return !getContext().getPackageName().equals(this.zzehh) || !zzz.zze(getContext(), Process.myUid());
    }

    public final boolean zzalx() {
        return true;
    }

    public final DriveId zzaqj() {
        return this.zzgtu;
    }

    public final DriveId zzaqk() {
        return this.zzgtv;
    }

    public final boolean zzaql() {
        return this.zzgtw;
    }

    public final boolean zzaqm() {
        return this.zzgtt;
    }

    /* access modifiers changed from: package-private */
    public final PendingResult<Status> zzb(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        zzbq.checkArgument(zzj.zza(1, driveId));
        zzbq.zza(isConnected(), (Object) "Client must be connected");
        zzbq.checkNotNull(changeListener, "listener");
        synchronized (this.zzgty) {
            Map map = this.zzgty.get(driveId);
            if (map == null) {
                zzbnk zzbnk = new zzbnk(googleApiClient, Status.zzftq);
                return zzbnk;
            }
            zzbra zzbra = (zzbra) map.remove(changeListener);
            if (zzbra == null) {
                zzbnk zzbnk2 = new zzbnk(googleApiClient, Status.zzftq);
                return zzbnk2;
            }
            if (map.isEmpty()) {
                this.zzgty.remove(driveId);
            }
            zzm zze = googleApiClient.zze(new zzbns(this, googleApiClient, new zzbti(driveId, 1), zzbra));
            return zze;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzbrk zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
        return queryLocalInterface instanceof zzbrk ? (zzbrk) queryLocalInterface : new zzbrl(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.drive.ApiService.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }
}
