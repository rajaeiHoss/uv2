package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import android.widget.ImageView;

final class zza implements View.OnClickListener {
    private /* synthetic */ UIMediaController zzfhn;

    zza(UIMediaController uIMediaController) {
        this.zzfhn = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzfhn.onPlayPauseToggleClicked((ImageView) view);
    }
}
