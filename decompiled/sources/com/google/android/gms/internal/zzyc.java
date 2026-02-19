package com.google.android.gms.internal;

import android.content.DialogInterface;

final class zzyc implements DialogInterface.OnClickListener {
    private /* synthetic */ zzya zzckx;

    zzyc(zzya zzya) {
        this.zzckx = zzya;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzckx.zzbm("Operation denied by user.");
    }
}
