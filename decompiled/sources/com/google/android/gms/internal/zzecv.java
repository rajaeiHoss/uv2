package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzecv {
    private static zzecv zzmvh;
    private Context mContext;

    private zzecv() {
    }

    public static synchronized zzecv zzbva() {
        zzecv zzecv;
        synchronized (zzecv.class) {
            if (zzmvh == null) {
                zzmvh = new zzecv();
            }
            zzecv = zzmvh;
        }
        return zzecv;
    }

    public final zzect zzbvb() throws zzecw {
        try {
            DynamiteModule zza = DynamiteModule.zza(this.mContext, DynamiteModule.zzhdk, "com.google.android.gms.crash");
            zzbq.checkNotNull(zza);
            IBinder zzhk = zza.zzhk("com.google.firebase.crash.internal.api.FirebaseCrashApiImpl");
            if (zzhk == null) {
                return null;
            }
            IInterface queryLocalInterface = zzhk.queryLocalInterface("com.google.firebase.crash.internal.IFirebaseCrashApi");
            return queryLocalInterface instanceof zzect ? (zzect) queryLocalInterface : new zzecu(zzhk);
        } catch (DynamiteModule.zzc e) {
            zzg.zza(this.mContext, e);
            throw new zzecw(e);
        }
    }

    public final void zzch(Context context) {
        this.mContext = context;
    }
}
