package com.google.android.gms.internal;

import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;

final class zzbbl extends MediaSessionCompat.Callback {
    private /* synthetic */ zzbbi zzfhg;

    zzbbl(zzbbi zzbbi) {
        this.zzfhg = zzbbi;
    }

    public final boolean onMediaButtonEvent(Intent intent) {
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return true;
        }
        if (keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 126) {
            return true;
        }
        this.zzfhg.zzfaq.togglePlayback();
        return true;
    }

    public final void onPause() {
        this.zzfhg.zzfaq.togglePlayback();
    }

    public final void onPlay() {
        this.zzfhg.zzfaq.togglePlayback();
    }

    public final void onStop() {
        if (this.zzfhg.zzfaq.isLiveStream()) {
            this.zzfhg.zzfaq.togglePlayback();
        }
    }
}
