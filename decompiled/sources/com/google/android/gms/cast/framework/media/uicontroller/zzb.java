package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzb implements View.OnClickListener {
    private /* synthetic */ UIMediaController zzfhn;

    zzb(UIMediaController uIMediaController) {
        this.zzfhn = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzfhn.onSkipNextClicked(view);
    }
}
