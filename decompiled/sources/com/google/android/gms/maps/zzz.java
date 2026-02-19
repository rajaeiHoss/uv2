package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzao;
import com.google.android.gms.maps.model.LatLng;

final class zzz extends zzao {
    private /* synthetic */ GoogleMap.OnMapLongClickListener zzjas;

    zzz(GoogleMap googleMap, GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        this.zzjas = onMapLongClickListener;
    }

    public final void onMapLongClick(LatLng latLng) {
        this.zzjas.onMapLongClick(latLng);
    }
}
