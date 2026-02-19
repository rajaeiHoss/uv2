package com.google.android.gms.internal;

import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.OnChangeListener;

final /* synthetic */ class zzbqf implements ChangeListener {
    private final OnChangeListener zzgwd;

    private zzbqf(OnChangeListener onChangeListener) {
        this.zzgwd = onChangeListener;
    }

    static ChangeListener zza(OnChangeListener onChangeListener) {
        return new zzbqf(onChangeListener);
    }

    public final void onChange(ChangeEvent changeEvent) {
        this.zzgwd.onChange(changeEvent);
    }
}
