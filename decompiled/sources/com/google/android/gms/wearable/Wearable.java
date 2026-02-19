package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import androidx.core.util.Preconditions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.wearable.internal.zzaa;
import com.google.android.gms.wearable.internal.zzaj;
import com.google.android.gms.wearable.internal.zzao;
import com.google.android.gms.wearable.internal.zzbv;
import com.google.android.gms.wearable.internal.zzbw;
import com.google.android.gms.wearable.internal.zzcj;
import com.google.android.gms.wearable.internal.zzeu;
import com.google.android.gms.wearable.internal.zzez;
import com.google.android.gms.wearable.internal.zzfg;
import com.google.android.gms.wearable.internal.zzfl;
import com.google.android.gms.wearable.internal.zzgi;
import com.google.android.gms.wearable.internal.zzh;
import com.google.android.gms.wearable.internal.zzhg;
import com.google.android.gms.wearable.internal.zzhq;
import com.google.android.gms.wearable.internal.zzk;
import com.google.android.gms.wearable.internal.zzo;

public class Wearable {
    @Deprecated
    public static final Api<WearableOptions> API;
    @Deprecated
    public static final CapabilityApi CapabilityApi = new zzo();
    @Deprecated
    public static final ChannelApi ChannelApi = new zzaj();
    @Deprecated
    public static final DataApi DataApi = new zzbw();
    @Deprecated
    public static final MessageApi MessageApi = new zzeu();
    @Deprecated
    public static final NodeApi NodeApi = new zzfg();
    private static final Api.zzf<zzhg> zzegu;
    private static final Api.zza<zzhg, WearableOptions> zzegv;
    @Deprecated
    private static zzc zzlrb = new zzk();
    @Deprecated
    private static zza zzlrc = new zzh();
    @Deprecated
    private static zzf zzlrd = new zzbv();
    @Deprecated
    private static zzi zzlre = new zzgi();
    @Deprecated
    private static zzu zzlrf = new zzhq();

    public static final class WearableOptions implements Api.ApiOptions.Optional {
        private final Looper zzfst;

        public static class Builder {
            /* access modifiers changed from: private */
            public Looper zzfst;

            public WearableOptions build() {
                return new WearableOptions(this, (zzj) null);
            }

            public Builder setLooper(Looper looper) {
                this.zzfst = looper;
                return this;
            }
        }

        private WearableOptions(Builder builder) {
            this.zzfst = builder.zzfst;
        }

        /* synthetic */ WearableOptions(Builder builder, zzj zzj) {
            this(builder);
        }

        /* access modifiers changed from: private */
        public final GoogleApi.zza zzblw() {
            return this.zzfst != null ? new zzd().zza(this.zzfst).zzahy() : GoogleApi.zza.zzfsr;
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.wearable.internal.zzk, com.google.android.gms.wearable.zzc] */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.wearable.zza, com.google.android.gms.wearable.internal.zzh] */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.google.android.gms.wearable.internal.zzbv, com.google.android.gms.wearable.zzf] */
    /* JADX WARNING: type inference failed for: r0v8, types: [com.google.android.gms.wearable.zzi, com.google.android.gms.wearable.internal.zzgi] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.google.android.gms.wearable.internal.zzhq, com.google.android.gms.wearable.zzu] */
    static {
        Api.zzf<zzhg> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzj zzj = new zzj();
        zzegv = zzj;
        API = new Api<>("Wearable.API", zzj, zzf);
    }

    private Wearable() {
    }

    public static CapabilityClient getCapabilityClient(Activity activity) {
        return new zzaa(activity, GoogleApi.zza.zzfsr);
    }

    public static CapabilityClient getCapabilityClient(Activity activity, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzaa(activity, wearableOptions.zzblw());
    }

    public static CapabilityClient getCapabilityClient(Context context) {
        return new zzaa(context, GoogleApi.zza.zzfsr);
    }

    public static CapabilityClient getCapabilityClient(Context context, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzaa(context, wearableOptions.zzblw());
    }

    public static ChannelClient getChannelClient(Activity activity) {
        return new zzao(activity, GoogleApi.zza.zzfsr);
    }

    public static ChannelClient getChannelClient(Activity activity, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzao(activity, wearableOptions.zzblw());
    }

    public static ChannelClient getChannelClient(Context context) {
        return new zzao(context, GoogleApi.zza.zzfsr);
    }

    public static ChannelClient getChannelClient(Context context, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzao(context, wearableOptions.zzblw());
    }

    public static DataClient getDataClient(Activity activity) {
        return new zzcj(activity, GoogleApi.zza.zzfsr);
    }

    public static DataClient getDataClient(Activity activity, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzcj(activity, wearableOptions.zzblw());
    }

    public static DataClient getDataClient(Context context) {
        return new zzcj(context, GoogleApi.zza.zzfsr);
    }

    public static DataClient getDataClient(Context context, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzcj(context, wearableOptions.zzblw());
    }

    public static MessageClient getMessageClient(Activity activity) {
        return new zzez(activity, GoogleApi.zza.zzfsr);
    }

    public static MessageClient getMessageClient(Activity activity, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzez(activity, wearableOptions.zzblw());
    }

    public static MessageClient getMessageClient(Context context) {
        return new zzez(context, GoogleApi.zza.zzfsr);
    }

    public static MessageClient getMessageClient(Context context, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzez(context, wearableOptions.zzblw());
    }

    public static NodeClient getNodeClient(Activity activity) {
        return new zzfl(activity, GoogleApi.zza.zzfsr);
    }

    public static NodeClient getNodeClient(Activity activity, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfl(activity, wearableOptions.zzblw());
    }

    public static NodeClient getNodeClient(Context context) {
        return new zzfl(context, GoogleApi.zza.zzfsr);
    }

    public static NodeClient getNodeClient(Context context, WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfl(context, wearableOptions.zzblw());
    }
}
