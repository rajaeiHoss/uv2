package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

@zzabh
public final class zzabv {
    /* access modifiers changed from: private */
    public static boolean zzd(Context context, boolean z) {
        if (!z) {
            return true;
        }
        int zzy = DynamiteModule.zzy(context, ModuleDescriptor.MODULE_ID);
        return zzy != 0 && zzy <= DynamiteModule.zzx(context, ModuleDescriptor.MODULE_ID);
    }
}
