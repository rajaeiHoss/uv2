package com.google.android.gms.internal;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

final class zzdiq implements zzdiu {
    private /* synthetic */ Context val$context;

    zzdiq(Context context) {
        this.val$context = context;
    }

    public final InputStream open(String str) throws IOException {
        return this.val$context.getAssets().open(str);
    }
}
