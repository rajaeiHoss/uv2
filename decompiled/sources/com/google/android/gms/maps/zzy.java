package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.LatLng;

final class zzy extends zzak {
    private /* synthetic */ GoogleMap.OnMapClickListener zzjar;

    zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zzjar = onMapClickListener;
    }

    public final void onMapClick(LatLng latLng) {
        this.zzjar.onMapClick(latLng);
    }
}
