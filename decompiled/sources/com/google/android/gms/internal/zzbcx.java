package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzbcx implements GameManagerClient.GameManagerResult {
    private final Status mStatus;
    private final String zzfld;
    private final long zzfle;
    private final JSONObject zzflf;

    zzbcx(Status status, String str, long j, JSONObject jSONObject) {
        this.mStatus = status;
        this.zzfld = str;
        this.zzfle = j;
        this.zzflf = jSONObject;
    }

    public final JSONObject getExtraMessageData() {
        return this.zzflf;
    }

    public final String getPlayerId() {
        return this.zzfld;
    }

    public final long getRequestId() {
        return this.zzfle;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
