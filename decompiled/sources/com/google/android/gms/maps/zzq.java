package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

final class zzq extends zzbg {
    private /* synthetic */ GoogleMap.OnPolylineClickListener zzjaj;

    zzq(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.zzjaj = onPolylineClickListener;
    }

    public final void zza(IPolylineDelegate iPolylineDelegate) {
        this.zzjaj.onPolylineClick(new Polyline(iPolylineDelegate));
    }
}
