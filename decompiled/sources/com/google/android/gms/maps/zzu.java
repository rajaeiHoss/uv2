package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;

final class zzu extends com.google.android.gms.maps.internal.zzu {
    private /* synthetic */ GoogleMap.OnCameraMoveStartedListener zzjan;

    zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.zzjan = onCameraMoveStartedListener;
    }

    public final void onCameraMoveStarted(int i) {
        this.zzjan.onCameraMoveStarted(i);
    }
}
