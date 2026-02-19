package com.google.android.gms.drive;

import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbnq;
import java.util.Arrays;

public class ExecutionOptions {
    public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
    public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
    public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
    private final String zzgqg;
    private final boolean zzgqh;
    private final int zzgqi;

    public static class Builder {
        protected String zzgqg;
        protected boolean zzgqh;
        protected int zzgqi = 0;

        public ExecutionOptions build() {
            zzapr();
            return new ExecutionOptions(this.zzgqg, this.zzgqh, this.zzgqi);
        }

        public Builder setConflictStrategy(int i) {
            boolean z = true;
            if (!(i == 0 || i == 1)) {
                z = false;
            }
            if (z) {
                this.zzgqi = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(53);
            sb.append("Unrecognized value for conflict strategy: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setNotifyOnCompletion(boolean z) {
            this.zzgqh = z;
            return this;
        }

        public Builder setTrackingTag(String str) {
            if (!TextUtils.isEmpty(str) && str.length() <= 65536) {
                this.zzgqg = str;
                return this;
            }
            throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", new Object[]{65536}));
        }

        /* access modifiers changed from: protected */
        public final void zzapr() {
            if (this.zzgqi == 1 && !this.zzgqh) {
                throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
            }
        }
    }

    public ExecutionOptions(String str, boolean z, int i) {
        this.zzgqg = str;
        this.zzgqh = z;
        this.zzgqi = i;
    }

    public static boolean zzcq(int i) {
        return i == 1;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            ExecutionOptions executionOptions = (ExecutionOptions) obj;
            return zzbg.equal(this.zzgqg, executionOptions.zzgqg) && this.zzgqi == executionOptions.zzgqi && this.zzgqh == executionOptions.zzgqh;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgqg, Integer.valueOf(this.zzgqi), Boolean.valueOf(this.zzgqh)});
    }

    public final void zza(zzbnq zzbnq) {
        if (this.zzgqh && !zzbnq.zzaqm()) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
        }
    }

    public final String zzapo() {
        return this.zzgqg;
    }

    public final boolean zzapp() {
        return this.zzgqh;
    }

    public final int zzapq() {
        return this.zzgqi;
    }

    @Deprecated
    public final void zzf(GoogleApiClient googleApiClient) {
        zza((zzbnq) googleApiClient.zza(Drive.zzegu));
    }
}
