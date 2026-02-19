package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;

public final class zzac extends zzev implements zzaa {
    zzac(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.messages.internal.ISubscribeCallback");
    }

    public final void onExpired() throws RemoteException {
        zzc(1, zzbc());
    }
}
