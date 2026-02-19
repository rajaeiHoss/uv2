package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzmo extends zzev implements zzmm {
    zzmo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IVideoController");
    }

    public final float getAspectRatio() throws RemoteException {
        Parcel zza = zza(9, zzbc());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final int getPlaybackState() throws RemoteException {
        Parcel zza = zza(5, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final boolean isClickToExpandEnabled() throws RemoteException {
        Parcel zza = zza(12, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isCustomControlsEnabled() throws RemoteException {
        Parcel zza = zza(10, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isMuted() throws RemoteException {
        Parcel zza = zza(4, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void mute(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(3, zzbc);
    }

    public final void pause() throws RemoteException {
        zzb(2, zzbc());
    }

    public final void play() throws RemoteException {
        zzb(1, zzbc());
    }

    public final void zza(zzmp zzmp) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzmp);
        zzb(8, zzbc);
    }

    public final float zziq() throws RemoteException {
        Parcel zza = zza(6, zzbc());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    public final float zzir() throws RemoteException {
        Parcel zza = zza(7, zzbc());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzmp zzis() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 11
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzmp
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzmp r1 = (com.google.android.gms.internal.zzmp) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzmr r2 = new com.google.android.gms.internal.zzmr
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmo.zzis():com.google.android.gms.internal.zzmp");
    }
}
