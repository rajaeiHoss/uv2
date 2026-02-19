package com.google.android.gms.cast.framework.media;

import android.app.Dialog;
import android.content.DialogInterface;

final class zzar implements DialogInterface.OnClickListener {
    private /* synthetic */ TracksChooserDialogFragment zzfgk;

    zzar(TracksChooserDialogFragment tracksChooserDialogFragment) {
        this.zzfgk = tracksChooserDialogFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.zzfgk.mDialog != null) {
            this.zzfgk.mDialog.cancel();
            Dialog unused = this.zzfgk.mDialog = null;
        }
    }
}
