package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaof;

@zzabh
public final class zzi {
    public final int index;
    public final ViewGroup parent;
    public final Context zzaiq;
    public final ViewGroup.LayoutParams zzcng;

    public zzi(zzaof zzaof) throws zzg {
        this.zzcng = zzaof.getLayoutParams();
        ViewParent parent2 = zzaof.getParent();
        this.zzaiq = zzaof.zztv();
        if (parent2 == null || !(parent2 instanceof ViewGroup)) {
            throw new zzg("Could not get the parent of the WebView for an overlay.");
        }
        ViewGroup viewGroup = (ViewGroup) parent2;
        this.parent = viewGroup;
        this.index = viewGroup.indexOfChild(zzaof.getView());
        viewGroup.removeView(zzaof.getView());
        zzaof.zzah(true);
    }
}
