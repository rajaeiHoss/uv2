package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.R;

public final class zzdar {
    private final Intent mIntent;
    /* access modifiers changed from: private */
    public final Context zzevc;
    private final Context zzhdo;
    private final zzdbo zzkuf;

    public zzdar(Intent intent, Context context, Context context2, zzdbo zzdbo) {
        this.zzevc = context;
        this.zzhdo = context2;
        this.mIntent = intent;
        this.zzkuf = zzdbo;
    }

    public final void zzbiz() {
        try {
            this.zzkuf.zzr(this.mIntent.getData());
            String string = this.zzhdo.getResources().getString(R.string.tagmanager_preview_dialog_title);
            String string2 = this.zzhdo.getResources().getString(R.string.tagmanager_preview_dialog_message);
            String string3 = this.zzhdo.getResources().getString(R.string.tagmanager_preview_dialog_button);
            AlertDialog create = new AlertDialog.Builder(this.zzevc).create();
            create.setTitle(string);
            create.setMessage(string2);
            create.setButton(-1, string3, new zzdas(this));
            create.show();
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzdal.e(valueOf.length() != 0 ? "Calling preview threw an exception: ".concat(valueOf) : new String("Calling preview threw an exception: "));
        }
    }
}
