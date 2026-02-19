package com.google.android.gms.cast;

import org.json.JSONObject;

public class MediaLoadOptions {
    public static final double PLAYBACK_RATE_MAX = 2.0d;
    public static final double PLAYBACK_RATE_MIN = 0.5d;
    private JSONObject zzess;
    private boolean zzewi;
    private long zzewj;
    private double zzewk;
    private long[] zzewl;
    private String zzewm;
    private String zzewn;

    public static class Builder {
        private JSONObject zzess = null;
        private boolean zzewi = true;
        private long zzewj = 0;
        private double zzewk = 1.0d;
        private long[] zzewl = null;
        private String zzewm = null;
        private String zzewn = null;

        public MediaLoadOptions build() {
            return new MediaLoadOptions(this.zzewi, this.zzewj, this.zzewk, this.zzewl, this.zzess, this.zzewm, this.zzewn);
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzewl = jArr;
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zzewi = z;
            return this;
        }

        public Builder setCredentials(String str) {
            this.zzewm = str;
            return this;
        }

        public Builder setCredentialsType(String str) {
            this.zzewn = str;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzess = jSONObject;
            return this;
        }

        public Builder setPlayPosition(long j) {
            this.zzewj = j;
            return this;
        }

        public Builder setPlaybackRate(double d) {
            if (Double.compare(d, 2.0d) > 0 || Double.compare(d, 0.5d) < 0) {
                throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
            }
            this.zzewk = d;
            return this;
        }
    }

    private MediaLoadOptions(boolean z, long j, double d, long[] jArr, JSONObject jSONObject, String str, String str2) {
        this.zzewi = z;
        this.zzewj = j;
        this.zzewk = d;
        this.zzewl = jArr;
        this.zzess = jSONObject;
        this.zzewm = str;
        this.zzewn = str2;
    }

    public long[] getActiveTrackIds() {
        return this.zzewl;
    }

    public boolean getAutoplay() {
        return this.zzewi;
    }

    public String getCredentials() {
        return this.zzewm;
    }

    public String getCredentialsType() {
        return this.zzewn;
    }

    public JSONObject getCustomData() {
        return this.zzess;
    }

    public long getPlayPosition() {
        return this.zzewj;
    }

    public double getPlaybackRate() {
        return this.zzewk;
    }
}
