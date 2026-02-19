package com.google.android.gms.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.pickview.view.WheelTime;

public final class zzast {
    private static zzasu<Boolean> zzebl = zzasu.zza("analytics.service_enabled", false, false);
    public static zzasu<Boolean> zzebm = zzasu.zza("analytics.service_client_enabled", true, true);
    public static zzasu<String> zzebn = zzasu.zzd("analytics.log_tag", "GAv4", "GAv4-SVC");
    private static zzasu<Long> zzebo = zzasu.zza("analytics.max_tokens", 60L, 60L);
    private static zzasu<Float> zzebp = zzasu.zza("analytics.tokens_per_sec", 0.5f, 0.5f);
    public static zzasu<Integer> zzebq = zzasu.zza("analytics.max_stored_hits", 2000, 20000);
    private static zzasu<Integer> zzebr = zzasu.zza("analytics.max_stored_hits_per_app", 2000, 2000);
    public static zzasu<Integer> zzebs = zzasu.zza("analytics.max_stored_properties_per_app", 100, 100);
    public static zzasu<Long> zzebt = zzasu.zza("analytics.local_dispatch_millis", 1800000L, 120000L);
    public static zzasu<Long> zzebu = zzasu.zza("analytics.initial_local_dispatch_millis", 5000L, 5000L);
    private static zzasu<Long> zzebv = zzasu.zza("analytics.min_local_dispatch_millis", 120000L, 120000L);
    private static zzasu<Long> zzebw = zzasu.zza("analytics.max_local_dispatch_millis", 7200000L, 7200000L);
    public static zzasu<Long> zzebx = zzasu.zza("analytics.dispatch_alarm_millis", 7200000L, 7200000L);
    public static zzasu<Long> zzeby = zzasu.zza("analytics.max_dispatch_alarm_millis", 32400000L, 32400000L);
    public static zzasu<Integer> zzebz = zzasu.zza("analytics.max_hits_per_dispatch", 20, 20);
    public static zzasu<Integer> zzeca = zzasu.zza("analytics.max_hits_per_batch", 20, 20);
    public static zzasu<String> zzecb = zzasu.zzd("analytics.insecure_host", "http://www.google-analytics.com", "http://www.google-analytics.com");
    public static zzasu<String> zzecc = zzasu.zzd("analytics.secure_host", "https://ssl.google-analytics.com", "https://ssl.google-analytics.com");
    public static zzasu<String> zzecd = zzasu.zzd("analytics.simple_endpoint", "/collect", "/collect");
    public static zzasu<String> zzece = zzasu.zzd("analytics.batching_endpoint", "/batch", "/batch");
    public static zzasu<Integer> zzecf = zzasu.zza("analytics.max_get_length", (int) WheelTime.DEFULT_END_YEAR, (int) WheelTime.DEFULT_END_YEAR);
    public static zzasu<String> zzecg = zzasu.zzd("analytics.batching_strategy.k", zzasb.BATCH_BY_COUNT.name(), zzasb.BATCH_BY_COUNT.name());
    public static zzasu<String> zzech;
    private static zzasu<Integer> zzeci = zzasu.zza("analytics.max_hits_per_request.k", 20, 20);
    public static zzasu<Integer> zzecj = zzasu.zza("analytics.max_hit_length.k", 8192, 8192);
    public static zzasu<Integer> zzeck = zzasu.zza("analytics.max_post_length.k", 8192, 8192);
    public static zzasu<Integer> zzecl = zzasu.zza("analytics.max_batch_post_length", 8192, 8192);
    public static zzasu<String> zzecm = zzasu.zzd("analytics.fallback_responses.k", "404,502", "404,502");
    public static zzasu<Integer> zzecn = zzasu.zza("analytics.batch_retry_interval.seconds.k", 3600, 3600);
    private static zzasu<Long> zzeco = zzasu.zza("analytics.service_monitor_interval", 86400000L, 86400000L);
    public static zzasu<Integer> zzecp = zzasu.zza("analytics.http_connection.connect_timeout_millis", 60000, 60000);
    public static zzasu<Integer> zzecq = zzasu.zza("analytics.http_connection.read_timeout_millis", 61000, 61000);
    public static zzasu<Long> zzecr = zzasu.zza("analytics.campaigns.time_limit", 86400000L, 86400000L);
    private static zzasu<String> zzecs = zzasu.zzd("analytics.first_party_experiment_id", "", "");
    private static zzasu<Integer> zzect = zzasu.zza("analytics.first_party_experiment_variant", 0, 0);
    public static zzasu<Boolean> zzecu = zzasu.zza("analytics.test.disable_receiver", false, false);
    public static zzasu<Long> zzecv = zzasu.zza("analytics.service_client.idle_disconnect_millis", (long) NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, (long) NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    public static zzasu<Long> zzecw = zzasu.zza("analytics.service_client.connect_timeout_millis", 5000L, 5000L);
    private static zzasu<Long> zzecx = zzasu.zza("analytics.service_client.second_connect_delay_millis", 5000L, 5000L);
    private static zzasu<Long> zzecy = zzasu.zza("analytics.service_client.unexpected_reconnect_millis", 60000L, 60000L);
    public static zzasu<Long> zzecz = zzasu.zza("analytics.service_client.reconnect_throttle_millis", 1800000L, 1800000L);
    public static zzasu<Long> zzeda = zzasu.zza("analytics.monitoring.sample_period_millis", 86400000L, 86400000L);
    public static zzasu<Long> zzedb = zzasu.zza("analytics.initialization_warning_threshold", 5000L, 5000L);

    static {
        String name = zzash.GZIP.name();
        zzech = zzasu.zzd("analytics.compression_strategy.k", name, name);
    }
}
