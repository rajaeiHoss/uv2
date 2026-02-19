package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class zzer extends zzeu {
    private final View zzakh;

    public zzer(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2, View view) {
        super(zzdm, str, str2, zzba, i, 57);
        this.zzakh = view;
    }

    /* access modifiers changed from: protected */
    public final void zzaw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzakh != null) {
            DisplayMetrics displayMetrics = this.zzagq.getContext().getResources().getDisplayMetrics();
            zzdt zzdt = new zzdt((String) this.zzaku.invoke((Object) null, new Object[]{this.zzakh, displayMetrics}));
            zzbc zzbc = new zzbc();
            zzbc.zzgc = zzdt.zzajt;
            zzbc.zzgd = zzdt.zzgd;
            zzbc.zzge = zzdt.zzge;
            this.zzakm.zzet = zzbc;
        }
    }
}
