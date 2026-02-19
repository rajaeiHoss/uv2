package com.google.android.gms.internal;

import android.util.JsonWriter;
import java.io.IOException;

final /* synthetic */ class zzakw implements zzakx {
    private final String zzdiu;

    zzakw(String str) {
        this.zzdiu = str;
    }

    public final void zza(JsonWriter jsonWriter) throws IOException {
        zzaks.zza(this.zzdiu, jsonWriter);
    }
}
