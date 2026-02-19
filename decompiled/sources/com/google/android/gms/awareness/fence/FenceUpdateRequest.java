package com.google.android.gms.awareness.fence;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbke;
import com.google.android.gms.internal.zzbkp;
import com.google.android.gms.internal.zzbkz;
import java.util.ArrayList;

public interface FenceUpdateRequest {

    public static class Builder {
        private final ArrayList<zzbkz> zzeqx = new ArrayList<>();

        public Builder addFence(String str, AwarenessFence awarenessFence, PendingIntent pendingIntent) {
            zzbq.zzgv(str);
            zzbq.checkNotNull(awarenessFence);
            zzbq.checkNotNull(pendingIntent);
            this.zzeqx.add(zzbkz.zza(str, 0, (zzbke) awarenessFence, pendingIntent));
            return this;
        }

        public FenceUpdateRequest build() {
            return new zzbkp(this.zzeqx);
        }

        public Builder removeFence(PendingIntent pendingIntent) {
            zzbq.checkNotNull(pendingIntent);
            this.zzeqx.add(zzbkz.zza(pendingIntent));
            return this;
        }

        public Builder removeFence(String str) {
            zzbq.zzgv(str);
            this.zzeqx.add(zzbkz.zzhd(str));
            return this;
        }
    }
}
