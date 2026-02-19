package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.awareness.AwarenessOptions;
import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.awareness.fence.zza;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.zzd;
import java.util.ArrayList;

public final class zzblg extends zzab<zzblp> {
    private static zzfd zzgod = zzfd.zzalf;
    private final Looper zzalj;
    private final zzblh zzgoe;
    private zzfk<zza, zzbjz> zzgof;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzblg(Context context, Looper looper, zzr zzr, AwarenessOptions awarenessOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 47, zzr, connectionCallbacks, onConnectionFailedListener);
        Context context2 = context;
        AwarenessOptions awarenessOptions2 = awarenessOptions;
        this.zzalj = looper;
        String str = zzr.getAccount() == null ? "@@ContextManagerNullAccount@@" : zzr.getAccount().name;
        this.zzgoe = awarenessOptions2 == null ? new zzblh(str, context.getPackageName(), Process.myUid(), context.getPackageName(), zzd.zzt(context2, context.getPackageName()), 3, (String) null, (String) null, -1, Process.myPid()) : zzblh.zza(context2, str, awarenessOptions2);
    }

    public final void zza(zzn<zzazy> zzn, zzazw zzazw) throws RemoteException {
        zzalv();
        ((zzblp) zzalw()).zza((zzbln) zzblj.zzd(zzn), this.zzgoe.packageName, this.zzgoe.zzgog, this.zzgoe.moduleId, zzazw);
    }

    public final void zza(zzn<FenceQueryResult> zzn, zzbkg zzbkg) throws RemoteException {
        zzalv();
        ((zzblp) zzalw()).zza((zzbln) zzblj.zze(zzn), this.zzgoe.packageName, this.zzgoe.zzgog, this.zzgoe.moduleId, zzbkg);
    }

    public final void zza(zzn<Status> zzn, zzbkp zzbkp) throws RemoteException {
        zzalv();
        if (this.zzgof == null) {
            this.zzgof = new zzfk<>(this.zzalj, zzbjz.zzgnc);
        }
        ArrayList arrayList = zzbkp.zzgnt;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            zzbkz zzbkz = (zzbkz) obj;
            if (zzbkz.zzgnz == null) {
                zza zza = zzbkz.zzgoa;
            }
        }
        ((zzblp) zzalw()).zza((zzbln) zzblj.zza(zzn, (zzblm) null), this.zzgoe.packageName, this.zzgoe.zzgog, this.zzgoe.moduleId, zzbkp);
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("com.google.android.contextmanager.service.args", zzbgq.zza(this.zzgoe));
        return bundle;
    }

    public final boolean zzalx() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzblp zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.contextmanager.internal.IContextManagerService");
        return queryLocalInterface instanceof zzblp ? (zzblp) queryLocalInterface : new zzblq(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.contextmanager.service.ContextManagerService.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.contextmanager.internal.IContextManagerService";
    }
}
