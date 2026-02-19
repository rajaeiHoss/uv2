package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.zzp;

final class zze extends zzag {
    private /* synthetic */ GoogleMap.OnInfoWindowLongClickListener zzizx;

    zze(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.zzizx = onInfoWindowLongClickListener;
    }

    public final void zzf(zzp zzp) {
        this.zzizx.onInfoWindowLongClick(new Marker(zzp));
    }
}
