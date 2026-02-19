package com.google.android.gms.fido.u2f;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.internal.zzbvw;
import com.google.android.gms.internal.zzbvy;
import com.google.android.gms.internal.zzbwa;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzc extends zzde<zzbwa, U2fPendingIntent> {
    private /* synthetic */ SignRequestParams zzhga;

    zzc(U2fApiClient u2fApiClient, SignRequestParams signRequestParams) {
        this.zzhga = signRequestParams;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbwa zzbwa, TaskCompletionSource<U2fPendingIntent> taskCompletionSource) throws RemoteException {
        ((zzbvy) zzbwa.zzalw()).zza((zzbvw) new zzd(this, taskCompletionSource), this.zzhga);
    }
}
