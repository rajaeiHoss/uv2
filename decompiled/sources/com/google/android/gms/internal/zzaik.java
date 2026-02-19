package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;

final class zzaik implements zzpg {
    private /* synthetic */ Context val$context;
    private /* synthetic */ List<String> zzdft;
    private /* synthetic */ zzpf zzdfu;

    zzaik(zzaij zzaij, List<String> list, zzpf zzpf, Context context) {
        this.zzdft = list;
        this.zzdfu = zzpf;
        this.val$context = context;
    }

    public final void zzju() {
        for (String str : this.zzdft) {
            String valueOf = String.valueOf(str);
            zzahw.zzcy(valueOf.length() != 0 ? "Pinging url: ".concat(valueOf) : new String("Pinging url: "));
            this.zzdfu.mayLaunchUrl(Uri.parse(str), (Bundle) null, (List<Bundle>) null);
        }
        this.zzdfu.zzc((Activity) this.val$context);
    }

    public final void zzjv() {
    }
}
