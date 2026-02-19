package com.google.android.gms.internal;

import android.animation.ObjectAnimator;
import android.view.View;
import com.google.android.gms.common.util.zzs;

final class zzbas implements View.OnClickListener {
    final /* synthetic */ zzbar zzfbw;

    zzbas(zzbar zzbar) {
        this.zzfbw = zzbar;
    }

    public final void onClick(View view) {
        if (zzs.zzans()) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0f});
            ofFloat.setDuration(400).addListener(new zzbat(this));
            ofFloat.start();
            return;
        }
        this.zzfbw.zzaez();
    }
}
