package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.internal.zzj;

final class zza extends zzaa {
    private /* synthetic */ GoogleMap.OnIndoorStateChangeListener zzizt;

    zza(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.zzizt = onIndoorStateChangeListener;
    }

    public final void onIndoorBuildingFocused() {
        this.zzizt.onIndoorBuildingFocused();
    }

    public final void zza(zzj zzj) {
        this.zzizt.onIndoorLevelActivated(new IndoorBuilding(zzj));
    }
}
