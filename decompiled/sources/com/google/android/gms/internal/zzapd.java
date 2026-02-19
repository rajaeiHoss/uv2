package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class zzapd implements DialogInterface.OnCancelListener {
    private /* synthetic */ JsResult zzdsc;

    zzapd(JsResult jsResult) {
        this.zzdsc = jsResult;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.zzdsc.cancel();
    }
}
