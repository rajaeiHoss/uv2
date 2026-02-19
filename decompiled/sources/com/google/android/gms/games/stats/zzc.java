package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;

public final class zzc extends com.google.android.gms.common.data.zzc implements PlayerStats {
    private Bundle zziij;

    zzc(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return zza.zza(this, obj);
    }

    public final PlayerStats freeze() {
        return new zza(this);
    }

    public final float getAverageSessionLength() {
        return getFloat("ave_session_length_minutes");
    }

    public final float getChurnProbability() {
        return getFloat("churn_probability");
    }

    public final int getDaysSinceLastPlayed() {
        return getInteger("days_since_last_played");
    }

    public final float getHighSpenderProbability() {
        if (!zzgj("high_spender_probability")) {
            return -1.0f;
        }
        return getFloat("high_spender_probability");
    }

    public final int getNumberOfPurchases() {
        return getInteger("num_purchases");
    }

    public final int getNumberOfSessions() {
        return getInteger("num_sessions");
    }

    public final float getSessionPercentile() {
        return getFloat("num_sessions_percentile");
    }

    public final float getSpendPercentile() {
        return getFloat("spend_percentile");
    }

    public final float getSpendProbability() {
        if (!zzgj("spend_probability")) {
            return -1.0f;
        }
        return getFloat("spend_probability");
    }

    public final float getTotalSpendNext28Days() {
        if (!zzgj("total_spend_next_28_days")) {
            return -1.0f;
        }
        return getFloat("total_spend_next_28_days");
    }

    public final int hashCode() {
        return zza.zza(this);
    }

    public final String toString() {
        return zza.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((zza) ((PlayerStats) freeze())).writeToParcel(parcel, i);
    }

    public final Bundle zzavz() {
        Bundle bundle = this.zziij;
        if (bundle != null) {
            return bundle;
        }
        this.zziij = new Bundle();
        String string = getString("unknown_raw_keys");
        String string2 = getString("unknown_raw_values");
        if (!(string == null || string2 == null)) {
            String[] split = string.split(",");
            String[] split2 = string2.split(",");
            if (split.length <= split2.length) {
                for (int i = 0; i < split.length; i++) {
                    this.zziij.putString(split[i], split2[i]);
                }
            } else {
                throw new IllegalStateException("Invalid raw arguments!");
            }
        }
        return this.zziij;
    }
}
