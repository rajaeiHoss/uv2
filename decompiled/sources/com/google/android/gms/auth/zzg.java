package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzez;
import java.io.IOException;
import java.util.List;

final class zzg implements zzj<List<AccountChangeEvent>> {
    private /* synthetic */ String zzehv;
    private /* synthetic */ int zzehw;

    zzg(String str, int i) {
        this.zzehv = str;
        this.zzehw = i;
    }

    public final List<AccountChangeEvent> zzac(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        return ((AccountChangeEventsResponse) zzd.zzp(zzez.zza(iBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzehv).setEventIndex(this.zzehw)))).getEvents();
    }
}
