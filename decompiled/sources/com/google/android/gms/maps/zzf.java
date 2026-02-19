package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zzf extends zzae {
    private /* synthetic */ GoogleMap.OnInfoWindowCloseListener zzizy;

    zzf(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.zzizy = onInfoWindowCloseListener;
    }

    public final void zzg(zzp zzp) {
        this.zzizy.onInfoWindowClose(new Marker(zzp));
    }
}
