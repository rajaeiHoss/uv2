package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zzbr extends AsyncTask<Void, Void, String> {
    private /* synthetic */ zzbn zzasj;

    private zzbr(zzbn zzbn) {
        this.zzasj = zzbn;
    }

    /* synthetic */ zzbr(zzbn zzbn, zzbo zzbo) {
        this(zzbn);
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final String doInBackground(Void... voidArr) {
        try {
            zzbn zzbn = this.zzasj;
            zzcv unused = zzbn.zzash = (zzcv) zzbn.zzase.get(((Long) zzlc.zzio().zzd(zzoi.zzbtr)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException e) {
            zzahw.zzc("Failed to load ad data", e);
        } catch (TimeoutException unused2) {
            zzahw.zzcz("Timed out waiting for ad data");
        }
        return this.zzasj.zzeb();
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(String str) {
        if (this.zzasj.zzasg != null && str != null) {
            this.zzasj.zzasg.loadUrl(str);
        }
    }
}
