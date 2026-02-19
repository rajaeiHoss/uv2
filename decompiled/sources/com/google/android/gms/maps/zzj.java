package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzba;

final class zzj extends zzba {
    private /* synthetic */ GoogleMap.OnMyLocationClickListener zzjac;

    zzj(GoogleMap googleMap, GoogleMap.OnMyLocationClickListener onMyLocationClickListener) {
        this.zzjac = onMyLocationClickListener;
    }

    public final void onMyLocationClick(Location location) throws RemoteException {
        this.zzjac.onMyLocationClick(location);
    }
}
