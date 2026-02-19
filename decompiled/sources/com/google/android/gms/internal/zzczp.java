package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.zzbq;
import java.util.Random;

public final class zzczp {
    private final Context mContext;
    private final Random zzbje;
    private final String zzknc;

    public zzczp(Context context, String str) {
        this(context, str, new Random());
    }

    private zzczp(Context context, String str, Random random) {
        this.mContext = (Context) zzbq.checkNotNull(context);
        this.zzknc = (String) zzbq.checkNotNull(str);
        this.zzbje = random;
    }

    private final SharedPreferences zzbga() {
        Context context = this.mContext;
        String valueOf = String.valueOf(this.zzknc);
        return context.getSharedPreferences(valueOf.length() != 0 ? "v5_gtmContainerRefreshPolicy_".concat(valueOf) : new String("v5_gtmContainerRefreshPolicy_"), 0);
    }

    private final long zzg(long j, long j2) {
        SharedPreferences zzbga = zzbga();
        long max = Math.max(0, zzbga.getLong("FORBIDDEN_COUNT", 0));
        return (long) (this.zzbje.nextFloat() * ((float) (j + ((long) ((((float) max) / ((float) ((max + Math.max(0, zzbga.getLong("SUCCESSFUL_COUNT", 0))) + 1))) * ((float) (j2 - j)))))));
    }

    public final long zzbfw() {
        return zzg(7200000, 259200000) + 43200000;
    }

    public final long zzbfx() {
        return zzg(600000, 86400000) + 3600000;
    }

    public final void zzbfy() {
        SharedPreferences zzbga = zzbga();
        long j = zzbga.getLong("FORBIDDEN_COUNT", 0);
        long j2 = zzbga.getLong("SUCCESSFUL_COUNT", 0);
        SharedPreferences.Editor edit = zzbga.edit();
        long min = j == 0 ? 3 : Math.min(10, j + 1);
        long max = Math.max(0, Math.min(j2, 10 - min));
        edit.putLong("FORBIDDEN_COUNT", min);
        edit.putLong("SUCCESSFUL_COUNT", max);
        edit.apply();
    }

    public final void zzbfz() {
        SharedPreferences zzbga = zzbga();
        long j = zzbga.getLong("SUCCESSFUL_COUNT", 0);
        long j2 = zzbga.getLong("FORBIDDEN_COUNT", 0);
        long min = Math.min(10, j + 1);
        long max = Math.max(0, Math.min(j2, 10 - min));
        SharedPreferences.Editor edit = zzbga.edit();
        edit.putLong("SUCCESSFUL_COUNT", min);
        edit.putLong("FORBIDDEN_COUNT", max);
        edit.apply();
    }

    public final long zzbim() {
        if (Math.max(0, zzbga().getLong("FORBIDDEN_COUNT", 0)) == 0) {
            return 0;
        }
        return zzg(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 600000) + NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    }

    public final boolean zzbin() {
        return Math.max(0, zzbga().getLong("FORBIDDEN_COUNT", 0)) > 0;
    }
}
