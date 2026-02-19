package com.google.android.gms.cast;

import android.content.Context;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbdz;
import com.google.android.gms.internal.zzbeh;
import com.google.android.gms.internal.zzbeq;
import com.google.android.gms.internal.zzbfa;
import com.google.android.gms.internal.zzccp;

public final class CastRemoteDisplay {
    @Deprecated
    public static final Api<CastRemoteDisplayOptions> API;
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;
    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi;
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final Api.zza<zzbfa, CastRemoteDisplayOptions> zzegv;

    @Deprecated
    public static final class CastRemoteDisplayOptions implements Api.ApiOptions.HasOptions {
        final CastDevice zzetj;
        final CastRemoteDisplaySessionCallbacks zzeue;
        final int zzeuf;

        @Deprecated
        public static final class Builder {
            CastDevice zzetm;
            CastRemoteDisplaySessionCallbacks zzeug;
            int zzeuh = 2;

            public Builder(CastDevice castDevice, CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks) {
                zzbq.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                this.zzetm = castDevice;
                this.zzeug = castRemoteDisplaySessionCallbacks;
            }

            public final CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, (zzo) null);
            }

            public final Builder setConfigPreset(int i) {
                this.zzeuh = i;
                return this;
            }
        }

        private CastRemoteDisplayOptions(Builder builder) {
            this.zzetj = builder.zzetm;
            this.zzeue = builder.zzeug;
            this.zzeuf = builder.zzeuh;
        }

        /* synthetic */ CastRemoteDisplayOptions(Builder builder, zzo zzo) {
            this(builder);
        }
    }

    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(Status status);
    }

    @Deprecated
    public interface CastRemoteDisplaySessionResult extends Result {
        Display getPresentationDisplay();
    }

    public @interface Configuration {
    }

    static {
        zzo zzo = new zzo();
        zzegv = zzo;
        Api<CastRemoteDisplayOptions> api = new Api<>("CastRemoteDisplay.API", zzo, zzbeh.zzfnh);
        API = api;
        CastRemoteDisplayApi = new zzbeq(api);
    }

    private CastRemoteDisplay() {
    }

    public static CastRemoteDisplayClient getClient(Context context) {
        return new CastRemoteDisplayClient(context);
    }

    public static final boolean isRemoteDisplaySdkSupported(Context context) {
        zzbdz.initialize(context);
        return ((Boolean) zzccp.zzaso().zzb(zzbdz.zzfnf)).booleanValue();
    }
}
