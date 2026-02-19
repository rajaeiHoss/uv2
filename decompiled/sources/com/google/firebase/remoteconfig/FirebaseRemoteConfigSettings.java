package com.google.firebase.remoteconfig;

public class FirebaseRemoteConfigSettings {
    private final boolean zzosi;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean zzosi = false;

        public FirebaseRemoteConfigSettings build() {
            return new FirebaseRemoteConfigSettings(this);
        }

        public Builder setDeveloperModeEnabled(boolean z) {
            this.zzosi = z;
            return this;
        }
    }

    private FirebaseRemoteConfigSettings(Builder builder) {
        this.zzosi = builder.zzosi;
    }

    public boolean isDeveloperModeEnabled() {
        return this.zzosi;
    }
}
