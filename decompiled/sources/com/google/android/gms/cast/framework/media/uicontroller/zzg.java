package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzg implements View.OnClickListener {
    private /* synthetic */ UIMediaController zzfhn;

    zzg(UIMediaController uIMediaController) {
        this.zzfhn = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzfhn.onLaunchExpandedControllerClicked(view);
    }
}
