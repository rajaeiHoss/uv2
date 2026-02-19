package com.google.android.gms.internal;

import android.view.View;

public class zzaiw extends zzaiv {
    public boolean isAttachedToWindow(View view) {
        return super.isAttachedToWindow(view) || view.getWindowId() != null;
    }

    public final int zzri() {
        return 14;
    }
}
