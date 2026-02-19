package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzdx extends zzeu {
    private final Activity zzakg;
    private final View zzakh;

    public zzdx(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2, View view, Activity activity) {
        super(zzdm, str, str2, zzba, i, 62);
        this.zzakh = view;
        this.zzakg = activity;
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzakh != null) {
            long[] jArr = (long[]) this.zzaku.invoke((Object) null, new Object[]{this.zzakh, this.zzakg});
            synchronized (this.zzakm) {
                this.zzakm.zzey = Long.valueOf(jArr[0]);
                this.zzakm.zzez = Long.valueOf(jArr[1]);
            }
        }
    }
}
