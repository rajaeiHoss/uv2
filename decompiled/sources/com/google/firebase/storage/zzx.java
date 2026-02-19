package com.google.firebase.storage;

import android.app.Activity;
import android.os.Build;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfaz;
import com.google.android.gms.internal.zzfbg;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

final class zzx<TListenerType, TResult extends StorageTask.ProvideError> {
    private final Queue<TListenerType> zzowl = new ConcurrentLinkedQueue();
    private final HashMap<TListenerType, zzfbg> zzowm = new HashMap<>();
    private StorageTask<TResult> zzown;
    private int zzowo;
    /* access modifiers changed from: private */
    public zzab<TListenerType, TResult> zzowp;

    public zzx(StorageTask<TResult> storageTask, int i, zzab<TListenerType, TResult> zzab) {
        this.zzown = storageTask;
        this.zzowo = i;
        this.zzowp = zzab;
    }

    public final void zza(Activity activity, Executor executor, TListenerType tlistenertype) {
        boolean z;
        zzfbg zzfbg;
        zzbq.checkNotNull(tlistenertype);
        synchronized (this.zzown.mSyncObject) {
            boolean z2 = true;
            z = (this.zzown.zzcnz() & this.zzowo) != 0;
            this.zzowl.add(tlistenertype);
            zzfbg = new zzfbg(executor);
            this.zzowm.put(tlistenertype, zzfbg);
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= 17) {
                    if (activity.isDestroyed()) {
                        z2 = false;
                    }
                    zzbq.checkArgument(z2, "Activity is already destroyed!");
                }
                zzfaz.zzcok().zza(activity, tlistenertype, new zzy(this, tlistenertype));
            }
        }
        if (z) {
            zzfbg.zzx(new zzz(this, tlistenertype, this.zzown.zzcoa()));
        }
    }

    public final void zzcoh() {
        if ((this.zzown.zzcnz() & this.zzowo) != 0) {
            TResult zzcoa = this.zzown.zzcoa();
            for (Object next : this.zzowl) {
                zzfbg zzfbg = this.zzowm.get(next);
                if (zzfbg != null) {
                    zzfbg.zzx(new zzaa(this, next, zzcoa));
                }
            }
        }
    }

    public final void zzcp(TListenerType tlistenertype) {
        zzbq.checkNotNull(tlistenertype);
        synchronized (this.zzown.mSyncObject) {
            this.zzowm.remove(tlistenertype);
            this.zzowl.remove(tlistenertype);
            zzfaz.zzcok().zzcq(tlistenertype);
        }
    }
}
