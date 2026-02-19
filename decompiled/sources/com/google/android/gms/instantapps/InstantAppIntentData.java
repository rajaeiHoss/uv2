package com.google.android.gms.instantapps;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class InstantAppIntentData extends zzbgl {
    public static final Parcelable.Creator<InstantAppIntentData> CREATOR = new zza();
    public static final int RESULT_LAUNCH_OK = 0;
    public static final int RESULT_NO_LAUNCH = 1;
    public static final int RESULT_NO_LAUNCH_HOLDBACK = 2;
    public static final int RESULT_USER_PREFERS_BROWSER = 3;
    public static final InstantAppIntentData zziod = new InstantAppIntentData((Intent) null, 1, (String) null);
    private final Intent intent;
    private final String packageName;
    private final int zzioe;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MatchResult {
    }

    public InstantAppIntentData(Intent intent2, int i, String str) {
        this.intent = intent2;
        this.zzioe = i;
        this.packageName = str;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public int getMatchResult() {
        return this.zzioe;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getIntent(), i, false);
        zzbgo.zzc(parcel, 2, getMatchResult());
        zzbgo.zza(parcel, 3, getPackageName(), false);
        zzbgo.zzai(parcel, zze);
    }
}
