package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzq;

final class zzw extends zzq {
    private /* synthetic */ GoogleMap.OnCameraMoveCanceledListener zzjap;

    zzw(GoogleMap googleMap, GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.zzjap = onCameraMoveCanceledListener;
    }

    public final void onCameraMoveCanceled() {
        this.zzjap.onCameraMoveCanceled();
    }
}
