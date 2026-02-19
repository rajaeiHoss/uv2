package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.TimeUnit;

public final class zzecz {
    public static final zzcce<Boolean> zzmvk = zzcce.zzb(0, "crash:enabled", (Boolean) true);
    private static zzcce<String> zzmvl = new zzccj(0, "crash:gateway_url", "https://mobilecrashreporting.googleapis.com/v1/crashes:batchCreate?key=");
    private static zzcce<Integer> zzmvm = zzcce.zzb(0, "crash:log_buffer_capacity", 100);
    private static zzcce<Integer> zzmvn = zzcce.zzb(0, "crash:log_buffer_max_total_size", 32768);
    private static zzcce<Integer> zzmvo = zzcce.zzb(0, "crash:crash_backlog_capacity", 5);
    private static zzcce<Long> zzmvp = zzcce.zzb(0, "crash:crash_backlog_max_age", 604800000L);
    private static zzcce<Long> zzmvq = zzcce.zzb(0, "crash:starting_backoff", TimeUnit.SECONDS.toMillis(1));
    private static zzcce<Long> zzmvr = zzcce.zzb(0, "crash:backoff_limit", TimeUnit.MINUTES.toMillis(60));
    private static zzcce<Integer> zzmvs = zzcce.zzb(0, "crash:retry_num_attempts", 12);
    private static zzcce<Integer> zzmvt = zzcce.zzb(0, "crash:batch_size", 5);
    private static zzcce<Long> zzmvu = zzcce.zzb(0, "crash:batch_throttle", TimeUnit.MINUTES.toMillis(5));
    private static zzcce<Integer> zzmvv = zzcce.zzb(0, "crash:frame_depth", 60);
    private static zzcce<Integer> zzmvw = zzcce.zzb(0, "crash:receiver_delay", 100);
    private static zzcce<Integer> zzmvx = zzcce.zzb(0, "crash:thread_idle_timeout", 10);

    public static final void initialize(Context context) {
        zzccp.zzasn();
        zzccp.zzaso().initialize(context);
    }
}
