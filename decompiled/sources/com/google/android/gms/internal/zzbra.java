package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzi;
import java.util.ArrayList;
import java.util.List;

public final class zzbra extends zzbrp {
    /* access modifiers changed from: private */
    public static final zzal zzgpv = new zzal("EventCallback", "");
    private final int zzgjw = 1;
    private final zzi zzgwp;
    private final zzbrc zzgwq;
    private final List<Integer> zzgwr = new ArrayList();

    public zzbra(Looper looper, Context context, int i, zzi zzi) {
        this.zzgwp = zzi;
        this.zzgwq = new zzbrc(looper, context);
    }

    public final void zzc(zzbsf zzbsf) throws RemoteException {
        DriveEvent zzaqq = zzbsf.zzaqq();
        zzbq.checkState(this.zzgjw == zzaqq.getType());
        zzbq.checkState(this.zzgwr.contains(Integer.valueOf(zzaqq.getType())));
        zzbrc zzbrc = this.zzgwq;
        zzbrc.sendMessage(zzbrc.obtainMessage(1, new Pair(this.zzgwp, zzaqq)));
    }

    public final void zzcv(int i) {
        this.zzgwr.add(1);
    }

    public final boolean zzcw(int i) {
        return this.zzgwr.contains(1);
    }
}
