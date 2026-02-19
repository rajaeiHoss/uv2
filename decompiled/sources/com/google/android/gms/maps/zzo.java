package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.internal.zzd;

final class zzo extends zzw {
    private /* synthetic */ GoogleMap.OnCircleClickListener zzjah;

    zzo(GoogleMap googleMap, GoogleMap.OnCircleClickListener onCircleClickListener) {
        this.zzjah = onCircleClickListener;
    }

    public final void zza(zzd zzd) {
        this.zzjah.onCircleClick(new Circle(zzd));
    }
}
