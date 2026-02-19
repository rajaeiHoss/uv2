package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

final class zzd implements View.OnClickListener {
    private /* synthetic */ UIMediaController zzfhn;
    private /* synthetic */ long zzfho;

    zzd(UIMediaController uIMediaController, long j) {
        this.zzfhn = uIMediaController;
        this.zzfho = j;
    }

    public final void onClick(View view) {
        this.zzfhn.onForwardClicked(view, this.zzfho);
    }
}
