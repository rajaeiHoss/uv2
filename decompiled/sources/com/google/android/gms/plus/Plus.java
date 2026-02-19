package com.google.android.gms.plus;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcwa;
import com.google.android.gms.internal.zzcwd;
import com.google.android.gms.internal.zzcwe;
import com.google.android.gms.internal.zzcwf;
import com.google.android.gms.plus.internal.zzh;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public final class Plus {
    @Deprecated
    public static final Api<PlusOptions> API;
    @Deprecated
    public static final Account AccountApi = new zzcwa();
    @Deprecated
    public static final People PeopleApi = new zzcwf();
    @Deprecated
    public static final Scope SCOPE_PLUS_LOGIN = new Scope(Scopes.PLUS_LOGIN);
    @Deprecated
    public static final Scope SCOPE_PLUS_PROFILE = new Scope(Scopes.PLUS_ME);
    public static final Api.zzf<zzh> zzegu;
    private static Api.zza<zzh, PlusOptions> zzegv;
    @Deprecated
    private static zzb zzkhi = new zzcwe();
    private static com.google.android.gms.plus.zza zzkhj = new zzcwd();

    @Deprecated
    public static final class PlusOptions implements Api.ApiOptions.Optional {
        private String zzkhk;
        final Set<String> zzkhl;

        @Deprecated
        public static final class Builder {
            String zzkhk;
            final Set<String> zzkhl = new HashSet();

            @Deprecated
            public final Builder addActivityTypes(String... strArr) {
                zzbq.checkNotNull(strArr, "activityTypes may not be null.");
                for (String add : strArr) {
                    this.zzkhl.add(add);
                }
                return this;
            }

            @Deprecated
            public final PlusOptions build() {
                return new PlusOptions(this, (zzc) null);
            }

            @Deprecated
            public final Builder setServerClientId(String str) {
                this.zzkhk = str;
                return this;
            }
        }

        private PlusOptions() {
            this.zzkhk = null;
            this.zzkhl = new HashSet();
        }

        private PlusOptions(Builder builder) {
            this.zzkhk = builder.zzkhk;
            this.zzkhl = builder.zzkhl;
        }

        /* synthetic */ PlusOptions(Builder builder, zzc zzc) {
            this(builder);
        }

        /* synthetic */ PlusOptions(zzc zzc) {
            this();
        }

        @Deprecated
        public static Builder builder() {
            return new Builder();
        }
    }

    public static abstract class zza<R extends Result> extends zzm<R, zzh> {
        public zza(GoogleApiClient googleApiClient) {
            super(Plus.zzegu, googleApiClient);
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.internal.zzcwe, com.google.android.gms.plus.zzb] */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.plus.zza, com.google.android.gms.internal.zzcwd] */
    static {
        Api.zzf<zzh> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzc zzc = new zzc();
        zzegv = zzc;
        API = new Api<>("Plus.API", zzc, zzf);
    }

    private Plus() {
    }

    public static zzh zzc(GoogleApiClient googleApiClient, boolean z) {
        zzbq.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzbq.zza(googleApiClient.isConnected(), (Object) "GoogleApiClient must be connected.");
        Api<PlusOptions> api = API;
        zzbq.zza(googleApiClient.zza((Api<?>) api), (Object) "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(api);
        if (z && !hasConnectedApi) {
            throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
        } else if (hasConnectedApi) {
            return (zzh) googleApiClient.zza(zzegu);
        } else {
            return null;
        }
    }
}
