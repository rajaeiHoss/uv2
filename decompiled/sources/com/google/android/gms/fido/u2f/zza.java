package com.google.android.gms.fido.u2f;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.internal.zzbvw;
import com.google.android.gms.internal.zzbvy;
import com.google.android.gms.internal.zzbwa;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zza extends zzde<zzbwa, U2fPendingIntent> {
    private /* synthetic */ RegisterRequestParams zzhfz;

    zza(U2fApiClient u2fApiClient, RegisterRequestParams registerRequestParams) {
        this.zzhfz = registerRequestParams;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbwa zzbwa, TaskCompletionSource<U2fPendingIntent> taskCompletionSource) throws RemoteException {
        ((zzbvy) zzbwa.zzalw()).zza((zzbvw) new zzb(this, taskCompletionSource), this.zzhfz);
    }
}
