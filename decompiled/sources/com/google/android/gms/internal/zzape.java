package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class zzape implements DialogInterface.OnClickListener {
    private /* synthetic */ JsResult zzdsc;

    zzape(JsResult jsResult) {
        this.zzdsc = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdsc.cancel();
    }
}
