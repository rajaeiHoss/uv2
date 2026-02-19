package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.internal.zzs;

final class zzp extends zzbe {
    private /* synthetic */ GoogleMap.OnPolygonClickListener zzjai;

    zzp(GoogleMap googleMap, GoogleMap.OnPolygonClickListener onPolygonClickListener) {
        this.zzjai = onPolygonClickListener;
    }

    public final void zza(zzs zzs) {
        this.zzjai.onPolygonClick(new Polygon(zzs));
    }
}
