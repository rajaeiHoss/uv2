package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;

final class zzf implements SeekBar.OnSeekBarChangeListener {
    private /* synthetic */ UIMediaController zzfhn;

    zzf(UIMediaController uIMediaController) {
        this.zzfhn = uIMediaController;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.zzfhn.onSeekBarProgressChanged(seekBar, i, z);
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.zzfhn.onSeekBarStartTrackingTouch(seekBar);
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.zzfhn.onSeekBarStopTrackingTouch(seekBar);
    }
}
