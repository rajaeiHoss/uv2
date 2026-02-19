package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Display;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzbez;
import com.google.android.gms.internal.zzbfc;
import com.google.android.gms.internal.zzbfe;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzq extends zzde<zzbez, Display> {
    private /* synthetic */ String zzetg;
    private /* synthetic */ int zzeuk;
    private /* synthetic */ PendingIntent zzeul;
    private /* synthetic */ CastDevice zzeum;
    final /* synthetic */ CastRemoteDisplayClient zzeun;

    zzq(CastRemoteDisplayClient castRemoteDisplayClient, int i, PendingIntent pendingIntent, CastDevice castDevice, String str) {
        this.zzeun = castRemoteDisplayClient;
        this.zzeuk = i;
        this.zzeul = pendingIntent;
        this.zzeum = castDevice;
        this.zzetg = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbez zzbez, TaskCompletionSource<Display> taskCompletionSource) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", this.zzeuk);
        ((zzbfe) zzbez.zzalw()).zza((zzbfc) new zzr(this, taskCompletionSource, zzbez), this.zzeul, this.zzeum.getDeviceId(), this.zzetg, bundle);
    }
}
