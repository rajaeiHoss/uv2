package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzawg;
import com.google.android.gms.internal.zzawi;
import com.google.android.gms.internal.zzawj;
import com.google.android.gms.internal.zzawm;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawp;
import com.google.android.gms.internal.zzawr;
import com.google.android.gms.internal.zzawt;
import com.google.android.gms.internal.zzawv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class AccountTransferClient extends GoogleApi<zzn> {
    private static final Api.zzf<zzawi> zzeiz;
    private static final Api.zza<zzawi, zzn> zzeja;
    private static final Api<zzn> zzejb;

    static class zza<T> extends zzawg {
        private zzb<T> zzejl;

        public zza(zzb<T> zzb) {
            this.zzejl = zzb;
        }

        public final void onFailure(Status status) {
            this.zzejl.zzd(status);
        }
    }

    static abstract class zzb<T> extends zzde<zzawi, T> {
        private TaskCompletionSource<T> zzejm;

        private zzb() {
        }

        /* synthetic */ zzb(zzc zzc) {
            this();
        }

        /* access modifiers changed from: protected */
        public final void setResult(T t) {
            this.zzejm.setResult(t);
        }

        /* access modifiers changed from: protected */
        public final void zza(zzawi zzawi, TaskCompletionSource<T> taskCompletionSource) throws RemoteException {
            this.zzejm = taskCompletionSource;
            zza((zzawn) zzawi.zzalw());
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzawn zzawn) throws RemoteException;

        /* access modifiers changed from: protected */
        public final void zzd(Status status) {
            AccountTransferClient.zza(this.zzejm, status);
        }
    }

    static abstract class zzc extends zzb<Void> {
        zzawm zzejn;

        private zzc() {
            super((zzc) null);
            this.zzejn = new zzk(this);
        }

        /* synthetic */ zzc(zzc zzc) {
            this();
        }
    }

    static {
        Api.zzf<zzawi> zzf = new Api.zzf<>();
        zzeiz = zzf;
        com.google.android.gms.auth.api.accounttransfer.zzc zzc2 = new com.google.android.gms.auth.api.accounttransfer.zzc();
        zzeja = zzc2;
        zzejb = new Api<>("AccountTransfer.ACCOUNT_TRANSFER_API", zzc2, zzf);
    }

    AccountTransferClient(Activity activity) {
        super(activity, zzejb, null, new zzd().zza((zzda) new zzg()).zzahy());
    }

    AccountTransferClient(Context context) {
        super(context, zzejb, null, new zzd().zza((zzda) new zzg()).zzahy());
    }

    /* access modifiers changed from: private */
    public static void zza(TaskCompletionSource taskCompletionSource, Status status) {
        taskCompletionSource.setException(new AccountTransferException(status));
    }

    public Task<DeviceMetaData> getDeviceMetaData(String str) {
        zzbq.checkNotNull(str);
        return zza(new com.google.android.gms.auth.api.accounttransfer.zzg(this, new zzawj(str)));
    }

    public Task<Void> notifyCompletion(String str, int i) {
        zzbq.checkNotNull(str);
        return zzb(new com.google.android.gms.auth.api.accounttransfer.zzj(this, new zzawp(str, i)));
    }

    public Task<byte[]> retrieveData(String str) {
        zzbq.checkNotNull(str);
        return zza(new com.google.android.gms.auth.api.accounttransfer.zze(this, new zzawr(str)));
    }

    public Task<Void> sendData(String str, byte[] bArr) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(bArr);
        return zzb(new com.google.android.gms.auth.api.accounttransfer.zzd(this, new zzawt(str, bArr)));
    }

    public Task<Void> showUserChallenge(String str, PendingIntent pendingIntent) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(pendingIntent);
        return zzb(new com.google.android.gms.auth.api.accounttransfer.zzi(this, new zzawv(str, pendingIntent)));
    }
}
