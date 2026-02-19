package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;

final class zzas implements DialogInterface.OnClickListener {
    private /* synthetic */ TracksChooserDialogFragment zzfgk;
    private /* synthetic */ zzat zzfgl;
    private /* synthetic */ zzat zzfgm;

    zzas(TracksChooserDialogFragment tracksChooserDialogFragment, zzat zzat, zzat zzat2) {
        this.zzfgk = tracksChooserDialogFragment;
        this.zzfgl = zzat;
        this.zzfgm = zzat2;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzfgk.zza(this.zzfgl, this.zzfgm);
    }
}
