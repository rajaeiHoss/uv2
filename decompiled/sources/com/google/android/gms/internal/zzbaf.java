package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionProvider;

public final class zzbaf extends SessionProvider {
    private final CastOptions zzezz;
    private final zzbaw zzfbo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbaf(Context context, CastOptions castOptions, zzbaw zzbaw) {
        super(context, castOptions.getSupportedNamespaces().isEmpty() ? CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId()) : CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId(), castOptions.getSupportedNamespaces()));
        this.zzezz = castOptions;
        this.zzfbo = zzbaw;
    }

    public final Session createSession(String str) {
        return new CastSession(getContext(), getCategory(), str, this.zzezz, Cast.CastApi, new zzbag(), new zzbbi(getContext(), this.zzezz, this.zzfbo));
    }

    public final boolean isSessionRecoverable() {
        return this.zzezz.getResumeSavedSession();
    }
}
