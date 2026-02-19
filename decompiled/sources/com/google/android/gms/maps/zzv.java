package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzs;

final class zzv extends zzs {
    private /* synthetic */ GoogleMap.OnCameraMoveListener zzjao;

    zzv(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        this.zzjao = onCameraMoveListener;
    }

    public final void onCameraMove() {
        this.zzjao.onCameraMove();
    }
}
