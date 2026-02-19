package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzh implements View.OnClickListener {
    private /* synthetic */ UIMediaController zzfhn;

    zzh(UIMediaController uIMediaController) {
        this.zzfhn = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzfhn.onClosedCaptionClicked(view);
    }
}
