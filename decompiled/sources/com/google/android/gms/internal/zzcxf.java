package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.HarmfulAppsData;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafeBrowsingThreat;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class zzcxf implements SafetyNetApi {
    private static final String TAG = "zzcxf";

    static class zza implements SafetyNetApi.zza {
        private final Status mStatus;
        private final com.google.android.gms.safetynet.zza zzkkq;

        public zza(Status status, com.google.android.gms.safetynet.zza zza) {
            this.mStatus = status;
            this.zzkkq = zza;
        }

        public final String getJwsResult() {
            com.google.android.gms.safetynet.zza zza = this.zzkkq;
            if (zza == null) {
                return null;
            }
            return zza.getJwsResult();
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static abstract class zzb extends zzcxa<SafetyNetApi.zza> {
        protected zzcxb zzkkr = new zzcxn(this);

        public zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final SafetyNetApi.zza zzb(Status status) {
            return new zzcxf.zza(status, (com.google.android.gms.safetynet.zza) null);
        }
    }

    static abstract class zzc extends zzcxa<SafetyNetApi.zzc> {
        protected zzcxb zzkkr = new zzcxo(this);

        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final SafetyNetApi.zzc zzb(Status status) {
            return new zzj(status, false);
        }
    }

    static abstract class zzd extends zzcxa<SafetyNetApi.zzb> {
        protected final zzcxb zzkkr = new zzcxp(this);

        public zzd(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final SafetyNetApi.zzb zzb(Status status) {
            return new zzg(status, (com.google.android.gms.safetynet.zzd) null);
        }
    }

    static abstract class zze extends zzcxa<SafetyNetApi.RecaptchaTokenResult> {
        protected zzcxb zzkkr = new zzcxq(this);

        public zze(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final SafetyNetApi.RecaptchaTokenResult zzb(Status status) {
            return new zzh(status, (com.google.android.gms.safetynet.zzf) null);
        }
    }

    static abstract class zzf extends zzcxa<SafetyNetApi.SafeBrowsingResult> {
        protected zzcxb zzkkr = new zzcxr(this);

        public zzf(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final SafetyNetApi.SafeBrowsingResult zzb(Status status) {
            return new zzi(status, (SafeBrowsingData) null);
        }
    }

    static class zzg implements SafetyNetApi.zzb {
        private final Status mStatus;
        private final com.google.android.gms.safetynet.zzd zzkkx;

        public zzg(Status status, com.google.android.gms.safetynet.zzd zzd) {
            this.mStatus = status;
            this.zzkkx = zzd;
        }

        public final List<HarmfulAppsData> getHarmfulAppsList() {
            com.google.android.gms.safetynet.zzd zzd = this.zzkkx;
            return zzd == null ? Collections.emptyList() : Arrays.asList(zzd.zzkjy);
        }

        public final int getHoursSinceLastScanWithHarmfulApp() {
            com.google.android.gms.safetynet.zzd zzd = this.zzkkx;
            if (zzd == null) {
                return -1;
            }
            return zzd.zzkjz;
        }

        public final long getLastScanTimeMs() {
            com.google.android.gms.safetynet.zzd zzd = this.zzkkx;
            if (zzd == null) {
                return 0;
            }
            return zzd.zzkjx;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static class zzh implements SafetyNetApi.RecaptchaTokenResult {
        private final Status mStatus;
        private final com.google.android.gms.safetynet.zzf zzkky;

        public zzh(Status status, com.google.android.gms.safetynet.zzf zzf) {
            this.mStatus = status;
            this.zzkky = zzf;
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final String getTokenResult() {
            com.google.android.gms.safetynet.zzf zzf = this.zzkky;
            if (zzf == null) {
                return null;
            }
            return zzf.getTokenResult();
        }
    }

    public static class zzi implements SafetyNetApi.SafeBrowsingResult {
        private Status mStatus;
        private long zzddi;
        private String zzkkd = null;
        private byte[] zzkkf;
        private final SafeBrowsingData zzkkz;

        public zzi(Status status, SafeBrowsingData safeBrowsingData) {
            this.mStatus = status;
            this.zzkkz = safeBrowsingData;
            if (safeBrowsingData != null) {
                this.zzkkd = safeBrowsingData.getMetadata();
                this.zzddi = safeBrowsingData.getLastUpdateTimeMs();
                this.zzkkf = safeBrowsingData.getState();
            } else if (status.isSuccess()) {
                this.mStatus = new Status(8);
            }
        }

        public final List<SafeBrowsingThreat> getDetectedThreats() {
            ArrayList arrayList = new ArrayList();
            if (this.zzkkd == null) {
                return arrayList;
            }
            try {
                JSONArray jSONArray = new JSONObject(this.zzkkd).getJSONArray("matches");
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList.add(new SafeBrowsingThreat(Integer.parseInt(jSONArray.getJSONObject(i).getString("threat_type"))));
                    } catch (NumberFormatException | JSONException unused) {
                    }
                }
            } catch (JSONException unused2) {
            }
            return arrayList;
        }

        public final long getLastUpdateTimeMs() {
            return this.zzddi;
        }

        public final String getMetadata() {
            return this.zzkkd;
        }

        public final byte[] getState() {
            return this.zzkkf;
        }

        public final Status getStatus() {
            return this.mStatus;
        }
    }

    static class zzj implements SafetyNetApi.zzc {
        private Status mStatus;
        private boolean zzdig;

        public zzj() {
        }

        public zzj(Status status, boolean z) {
            this.mStatus = status;
            this.zzdig = z;
        }

        public final Status getStatus() {
            return this.mStatus;
        }

        public final boolean isVerifyAppsEnabled() {
            Status status = this.mStatus;
            if (status == null || !status.isSuccess()) {
                return false;
            }
            return this.zzdig;
        }
    }

    public static PendingResult<SafetyNetApi.SafeBrowsingResult> zza(GoogleApiClient googleApiClient, String str, int i, String str2, int... iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        } else if (!TextUtils.isEmpty(str)) {
            return googleApiClient.zzd(new zzcxi(googleApiClient, iArr, i, str, str2));
        } else {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
    }

    public static PendingResult<SafetyNetApi.zza> zza(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        return googleApiClient.zzd(new zzcxg(googleApiClient, bArr, str));
    }

    public PendingResult<SafetyNetApi.zza> attest(GoogleApiClient googleApiClient, byte[] bArr) {
        return zza(googleApiClient, bArr, (String) null);
    }

    public PendingResult<SafetyNetApi.zzc> enableVerifyApps(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzcxk(this, googleApiClient));
    }

    public PendingResult<SafetyNetApi.zzc> isVerifyAppsEnabled(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzcxj(this, googleApiClient));
    }

    public boolean isVerifyAppsEnabled(Context context) {
        GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(SafetyNet.API).build();
        try {
            boolean z = false;
            if (build.blockingConnect(3, TimeUnit.SECONDS).isSuccess()) {
                SafetyNetApi.zzc await = isVerifyAppsEnabled(build).await(3, TimeUnit.SECONDS);
                if (await != null && await.isVerifyAppsEnabled()) {
                    z = true;
                }
                return z;
            }
            if (build != null) {
                build.disconnect();
            }
            return false;
        } finally {
            if (build != null) {
                build.disconnect();
            }
        }
    }

    public PendingResult<SafetyNetApi.zzb> listHarmfulApps(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzcxl(this, googleApiClient));
    }

    public PendingResult<SafetyNetApi.SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, String str, String str2, int... iArr) {
        return zza(googleApiClient, str, 1, str2, iArr);
    }

    public PendingResult<SafetyNetApi.SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, List<Integer> list, String str) {
        if (list == null) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        } else if (!TextUtils.isEmpty(str)) {
            return googleApiClient.zzd(new zzcxh(this, googleApiClient, list, str, (String) null));
        } else {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
    }

    public PendingResult<SafetyNetApi.RecaptchaTokenResult> verifyWithRecaptcha(GoogleApiClient googleApiClient, String str) {
        if (!TextUtils.isEmpty(str)) {
            return googleApiClient.zzd(new zzcxm(this, googleApiClient, str));
        }
        throw new IllegalArgumentException("Null or empty site key in verifyWithRecaptcha");
    }
}
