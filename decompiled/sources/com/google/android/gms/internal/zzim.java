package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;

@zzabh
public final class zzim extends zzd<zziq> {
    zzim(Context context, Looper looper, zzf zzf, zzg zzg) {
        super(context, looper, 123, zzf, zzg, (String) null);
    }

    /* access modifiers changed from: protected */
    public final zziq zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.cache.ICacheService");
        return queryLocalInterface instanceof zziq ? (zziq) queryLocalInterface : new zzir(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }

    public final zziq zzho() throws DeadObjectException {
        return (zziq) super.zzalw();
    }
}
