package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;

final class zzx extends zzo {
    private /* synthetic */ GoogleMap.OnCameraIdleListener zzjaq;

    zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.zzjaq = onCameraIdleListener;
    }

    public final void onCameraIdle() {
        this.zzjaq.onCameraIdle();
    }
}
