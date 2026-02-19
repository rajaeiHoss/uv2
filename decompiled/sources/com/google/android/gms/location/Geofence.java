package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.zzchp;

public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    public static final class Builder {
        private String zzcwj = null;
        private int zziro = 0;
        private long zzirp = Long.MIN_VALUE;
        private short zzirq = -1;
        private double zzirr;
        private double zzirs;
        private float zzirt;
        private int zziru = 0;
        private int zzirv = -1;

        public final Geofence build() {
            if (this.zzcwj != null) {
                int i = this.zziro;
                if (i == 0) {
                    throw new IllegalArgumentException("Transitions types not set.");
                } else if ((i & 4) != 0 && this.zzirv < 0) {
                    throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
                } else if (this.zzirp == Long.MIN_VALUE) {
                    throw new IllegalArgumentException("Expiration not set.");
                } else if (this.zzirq == -1) {
                    throw new IllegalArgumentException("Geofence region not set.");
                } else if (this.zziru >= 0) {
                    return new zzchp(this.zzcwj, this.zziro, this.zzirq, this.zzirr, this.zzirs, this.zzirt, this.zzirp, this.zziru, this.zzirv);
                } else {
                    throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
                }
            } else {
                throw new IllegalArgumentException("Request ID not set.");
            }
        }

        public final Builder setCircularRegion(double d, double d2, float f) {
            this.zzirq = 1;
            this.zzirr = d;
            this.zzirs = d2;
            this.zzirt = f;
            return this;
        }

        public final Builder setExpirationDuration(long j) {
            if (j < 0) {
                this.zzirp = -1;
            } else {
                this.zzirp = SystemClock.elapsedRealtime() + j;
            }
            return this;
        }

        public final Builder setLoiteringDelay(int i) {
            this.zzirv = i;
            return this;
        }

        public final Builder setNotificationResponsiveness(int i) {
            this.zziru = i;
            return this;
        }

        public final Builder setRequestId(String str) {
            this.zzcwj = str;
            return this;
        }

        public final Builder setTransitionTypes(int i) {
            this.zziro = i;
            return this;
        }
    }

    String getRequestId();
}
