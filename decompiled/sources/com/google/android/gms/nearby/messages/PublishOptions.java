package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.zzbq;

public final class PublishOptions {
    public static final PublishOptions DEFAULT = new Builder().build();
    private final Strategy zzkba;
    private final PublishCallback zzkbb;

    public static class Builder {
        private Strategy zzkba = Strategy.DEFAULT;
        private PublishCallback zzkbb;

        public PublishOptions build() {
            return new PublishOptions(this.zzkba, this.zzkbb);
        }

        public Builder setCallback(PublishCallback publishCallback) {
            this.zzkbb = (PublishCallback) zzbq.checkNotNull(publishCallback);
            return this;
        }

        public Builder setStrategy(Strategy strategy) {
            this.zzkba = (Strategy) zzbq.checkNotNull(strategy);
            return this;
        }
    }

    private PublishOptions(Strategy strategy, PublishCallback publishCallback) {
        this.zzkba = strategy;
        this.zzkbb = publishCallback;
    }

    public final PublishCallback getCallback() {
        return this.zzkbb;
    }

    public final Strategy getStrategy() {
        return this.zzkba;
    }
}
