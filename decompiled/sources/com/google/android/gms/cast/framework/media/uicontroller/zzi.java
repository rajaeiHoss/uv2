package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import android.widget.ImageView;

final class zzi implements View.OnClickListener {
    private /* synthetic */ UIMediaController zzfhn;

    zzi(UIMediaController uIMediaController) {
        this.zzfhn = uIMediaController;
    }

    public final void onClick(View view) {
        this.zzfhn.onMuteToggleClicked((ImageView) view);
    }
}
