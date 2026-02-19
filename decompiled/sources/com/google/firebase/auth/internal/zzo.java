package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzebs;
import com.google.firebase.auth.ProviderQueryResult;
import java.util.List;

public final class zzo implements ProviderQueryResult {
    private List<String> zzmts;

    public zzo(zzebs zzebs) {
        zzbq.checkNotNull(zzebs);
        this.zzmts = zzebs.getAllProviders();
    }

    public final List<String> getProviders() {
        return this.zzmts;
    }
}
