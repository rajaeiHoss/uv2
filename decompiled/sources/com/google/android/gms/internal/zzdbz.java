package com.google.android.gms.internal;

import android.net.Uri;

final class zzdbz implements Runnable {
    private /* synthetic */ zzdbo zzkyv;
    private /* synthetic */ Uri zzkzh;

    zzdbz(zzdbo zzdbo, Uri uri) {
        this.zzkyv = zzdbo;
        this.zzkzh = uri;
    }

    public final void run() {
        String valueOf = String.valueOf(this.zzkzh);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
        sb.append("Preview requested to uri ");
        sb.append(valueOf);
        zzdal.v(sb.toString());
        synchronized (this.zzkyv.zzkyp) {
            if (this.zzkyv.zzkyr == 2) {
                zzdal.v("Still initializing. Defer preview container loading.");
                this.zzkyv.zzkys.add(this);
                return;
            }
            String str = (String) this.zzkyv.zzg((String[]) null).first;
            if (str == null) {
                zzdal.zzcz("Preview failed (no container found)");
            } else if (!this.zzkyv.zzkyn.zzc(str, this.zzkzh)) {
                String valueOf2 = String.valueOf(this.zzkzh);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 73);
                sb2.append("Cannot preview the app with the uri: ");
                sb2.append(valueOf2);
                sb2.append(". Launching current version instead.");
                zzdal.zzcz(sb2.toString());
            } else if (!this.zzkyv.zzarf) {
                String valueOf3 = String.valueOf(this.zzkzh);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 84);
                sb3.append("Deferring container loading for preview uri: ");
                sb3.append(valueOf3);
                sb3.append("(Tag Manager has not been initialized).");
                zzdal.v(sb3.toString());
            } else {
                String valueOf4 = String.valueOf(this.zzkzh);
                StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 36);
                sb4.append("Starting to load preview container: ");
                sb4.append(valueOf4);
                zzdal.zzcy(sb4.toString());
                if (!this.zzkyv.zzkym.zzbjj()) {
                    zzdal.zzcz("Failed to reset TagManager service for preview");
                    return;
                }
                boolean unused = this.zzkyv.zzarf = false;
                int unused2 = this.zzkyv.zzkyr = 1;
                this.zzkyv.initialize();
            }
        }
    }
}
