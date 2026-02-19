package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.api.Api;

public class MessagesOptions implements Api.ApiOptions.Optional {
    public final String zzkav;
    public final boolean zzkaw;
    public final int zzkax;
    public final String zzkay;

    public static class Builder {
        /* access modifiers changed from: private */
        public int zzkaz = -1;

        public MessagesOptions build() {
            return new MessagesOptions(this);
        }

        public Builder setPermissions(int i) {
            this.zzkaz = i;
            return this;
        }
    }

    private MessagesOptions(Builder builder) {
        this.zzkav = null;
        this.zzkaw = false;
        this.zzkax = builder.zzkaz;
        this.zzkay = null;
    }
}
