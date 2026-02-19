package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.internal.zzah;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzm implements LocationSource.OnLocationChangedListener {
    private /* synthetic */ zzah zzjaf;

    zzm(zzl zzl, zzah zzah) {
        this.zzjaf = zzah;
    }

    public final void onLocationChanged(Location location) {
        try {
            this.zzjaf.zzd(location);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
