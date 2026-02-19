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
public final class zzace extends zzd<zzacn> {
    private int zzcrt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzace(Context context, Looper looper, zzf zzf, zzg zzg, int i) {
        super(context.getApplicationContext() != null ? context.getApplicationContext() : context, looper, 8, zzf, zzg, (String) null);
        this.zzcrt = i;
    }

    /* access modifiers changed from: protected */
    public final zzacn zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        return queryLocalInterface instanceof zzacn ? (zzacn) queryLocalInterface : new zzacp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.ads.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    public final zzacn zzoa() throws DeadObjectException {
        return (zzacn) super.zzalw();
    }
}
