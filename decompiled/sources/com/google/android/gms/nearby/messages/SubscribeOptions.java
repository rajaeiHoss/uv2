package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.zzbq;

public final class SubscribeOptions {
    public static final SubscribeOptions DEFAULT = new Builder().build();
    private final Strategy zzkba;
    private final MessageFilter zzkbp;
    private final SubscribeCallback zzkbq;
    public final boolean zzkbr;
    public final int zzkbs;

    public static class Builder {
        private Strategy zzkba = Strategy.DEFAULT;
        private MessageFilter zzkbp = MessageFilter.INCLUDE_ALL_MY_TYPES;
        private SubscribeCallback zzkbq;

        public SubscribeOptions build() {
            return new SubscribeOptions(this.zzkba, this.zzkbp, this.zzkbq, false, 0, (zzh) null);
        }

        public Builder setCallback(SubscribeCallback subscribeCallback) {
            this.zzkbq = (SubscribeCallback) zzbq.checkNotNull(subscribeCallback);
            return this;
        }

        public Builder setFilter(MessageFilter messageFilter) {
            this.zzkbp = messageFilter;
            return this;
        }

        public Builder setStrategy(Strategy strategy) {
            this.zzkba = strategy;
            return this;
        }
    }

    private SubscribeOptions(Strategy strategy, MessageFilter messageFilter, SubscribeCallback subscribeCallback, boolean z, int i) {
        this.zzkba = strategy;
        this.zzkbp = messageFilter;
        this.zzkbq = subscribeCallback;
        this.zzkbr = z;
        this.zzkbs = i;
    }

    /* synthetic */ SubscribeOptions(Strategy strategy, MessageFilter messageFilter, SubscribeCallback subscribeCallback, boolean z, int i, zzh zzh) {
        this(strategy, messageFilter, subscribeCallback, false, 0);
    }

    public final SubscribeCallback getCallback() {
        return this.zzkbq;
    }

    public final MessageFilter getFilter() {
        return this.zzkbp;
    }

    public final Strategy getStrategy() {
        return this.zzkba;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzkba);
        String valueOf2 = String.valueOf(this.zzkbp);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36 + String.valueOf(valueOf2).length());
        sb.append("SubscribeOptions{strategy=");
        sb.append(valueOf);
        sb.append(", filter=");
        sb.append(valueOf2);
        sb.append('}');
        return sb.toString();
    }
}
