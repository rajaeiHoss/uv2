package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class zzac {
    public int bottom;
    public int gravity;
    public int left;
    public int right;
    public int top;
    public IBinder zziae;
    public int zziaf;

    zzac(int i, IBinder iBinder) {
        this.zziaf = -1;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.gravity = i;
        this.zziae = iBinder;
    }

    public final Bundle zzauy() {
        Bundle bundle = new Bundle();
        bundle.putInt("popupLocationInfo.gravity", this.gravity);
        bundle.putInt("popupLocationInfo.displayId", this.zziaf);
        bundle.putInt("popupLocationInfo.left", this.left);
        bundle.putInt("popupLocationInfo.top", this.top);
        bundle.putInt("popupLocationInfo.right", this.right);
        bundle.putInt("popupLocationInfo.bottom", this.bottom);
        return bundle;
    }
}
