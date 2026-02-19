package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzae;
import com.google.android.gms.ads.internal.js.zzaa;
import org.json.JSONException;
import org.json.JSONObject;

final class zzvl implements zzae {
    private /* synthetic */ zzvi zzcgy;
    private final zzaa zzcgz;
    private final zzamd zzcha;

    public zzvl(zzvi zzvi, zzaa zzaa, zzamd zzamd) {
        this.zzcgy = zzvi;
        this.zzcgz = zzaa;
        this.zzcha = zzamd;
    }

    public final void zzau(String str) {
        if (str == null) {
            try {
                this.zzcha.setException(new zzuw());
            } catch (IllegalStateException unused) {
            } catch (Throwable th) {
                this.zzcgz.release();
                throw th;
            }
        } else {
            this.zzcha.setException(new zzuw(str));
        }
        this.zzcgz.release();
    }

    public final void zzd(JSONObject jSONObject) {
        try {
            this.zzcha.set(this.zzcgy.zzcgs.zze(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzcha.set(e);
        } catch (Throwable th) {
            this.zzcgz.release();
            throw th;
        }
        this.zzcgz.release();
    }
}
