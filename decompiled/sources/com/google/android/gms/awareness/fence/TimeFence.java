package com.google.android.gms.awareness.fence;

import com.google.android.gms.internal.zzbke;
import com.google.android.gms.internal.zzbkw;
import com.google.android.gms.internal.zzbkx;
import com.google.android.gms.internal.zzbky;
import java.util.TimeZone;

public final class TimeFence {
    public static final int DAY_OF_WEEK_FRIDAY = 6;
    public static final int DAY_OF_WEEK_MONDAY = 2;
    public static final int DAY_OF_WEEK_SATURDAY = 7;
    public static final int DAY_OF_WEEK_SUNDAY = 1;
    public static final int DAY_OF_WEEK_THURSDAY = 5;
    public static final int DAY_OF_WEEK_TUESDAY = 3;
    public static final int DAY_OF_WEEK_WEDNESDAY = 4;
    public static final int TIME_INSTANT_SUNRISE = 1;
    public static final int TIME_INSTANT_SUNSET = 2;
    public static final int TIME_INTERVAL_AFTERNOON = 5;
    public static final int TIME_INTERVAL_EVENING = 6;
    public static final int TIME_INTERVAL_HOLIDAY = 3;
    public static final int TIME_INTERVAL_MORNING = 4;
    public static final int TIME_INTERVAL_NIGHT = 7;
    public static final int TIME_INTERVAL_WEEKDAY = 1;
    public static final int TIME_INTERVAL_WEEKEND = 2;

    private TimeFence() {
    }

    public static AwarenessFence aroundTimeInstant(int i, long j, long j2) {
        zzbkw zzc;
        if (i == 1) {
            zzc = zzbkw.zzc(j, j2);
        } else if (i == 2) {
            zzc = zzbkw.zzd(j, j2);
        } else {
            StringBuilder sb = new StringBuilder(40);
            sb.append("Unknown time instant label = ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        return zzbke.zza(zzc);
    }

    public static AwarenessFence inDailyInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(2, timeZone, j, j2));
    }

    @Deprecated
    public static AwarenessFence inFridayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(10, timeZone, j, j2));
    }

    public static AwarenessFence inInterval(long j, long j2) {
        return zzbke.zza(zzbkx.zze(j, j2));
    }

    public static AwarenessFence inIntervalOfDay(int i, TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zzb(i, timeZone, j, j2));
    }

    @Deprecated
    public static AwarenessFence inMondayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(6, timeZone, j, j2));
    }

    @Deprecated
    public static AwarenessFence inSaturdayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(11, timeZone, j, j2));
    }

    @Deprecated
    public static AwarenessFence inSundayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(5, timeZone, j, j2));
    }

    @Deprecated
    public static AwarenessFence inThursdayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(9, timeZone, j, j2));
    }

    public static AwarenessFence inTimeInterval(int i) {
        return zzbke.zza(zzbky.zzm(1, i));
    }

    @Deprecated
    public static AwarenessFence inTuesdayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(7, timeZone, j, j2));
    }

    @Deprecated
    public static AwarenessFence inWednesdayInterval(TimeZone timeZone, long j, long j2) {
        return zzbke.zza(zzbkx.zza(8, timeZone, j, j2));
    }
}
