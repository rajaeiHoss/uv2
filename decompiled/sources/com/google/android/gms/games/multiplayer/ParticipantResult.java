package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;

public final class ParticipantResult extends zzc {
    public static final Parcelable.Creator<ParticipantResult> CREATOR = new zzd();
    public static final int MATCH_RESULT_DISAGREED = 5;
    public static final int MATCH_RESULT_DISCONNECT = 4;
    public static final int MATCH_RESULT_LOSS = 1;
    public static final int MATCH_RESULT_NONE = 3;
    public static final int MATCH_RESULT_TIE = 2;
    public static final int MATCH_RESULT_UNINITIALIZED = -1;
    public static final int MATCH_RESULT_WIN = 0;
    public static final int PLACING_UNINITIALIZED = -1;
    private final int mResult;
    private final String zzhyl;
    private final int zzifk;

    public ParticipantResult(String str, int i, int i2) {
        this.zzhyl = (String) zzbq.checkNotNull(str);
        boolean z = true;
        if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5)) {
            z = false;
        }
        zzbq.checkState(z);
        this.mResult = i;
        this.zzifk = i2;
    }

    public final String getParticipantId() {
        return this.zzhyl;
    }

    public final int getPlacing() {
        return this.zzifk;
    }

    public final int getResult() {
        return this.mResult;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getParticipantId(), false);
        zzbgo.zzc(parcel, 2, getResult());
        zzbgo.zzc(parcel, 3, getPlacing());
        zzbgo.zzai(parcel, zze);
    }
}
