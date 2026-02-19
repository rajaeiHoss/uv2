package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class RealTimeMessage implements Parcelable {
    public static final Parcelable.Creator<RealTimeMessage> CREATOR = new zza();
    public static final int RELIABLE = 1;
    public static final int UNRELIABLE = 0;
    private final String zzifl;
    private final byte[] zzifm;
    private final int zzifn;

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    /* synthetic */ RealTimeMessage(Parcel parcel, zza zza) {
        this(parcel);
    }

    private RealTimeMessage(String str, byte[] bArr, int i) {
        this.zzifl = (String) zzbq.checkNotNull(str);
        this.zzifm = (byte[]) ((byte[]) zzbq.checkNotNull(bArr)).clone();
        this.zzifn = i;
    }

    public final int describeContents() {
        return 0;
    }

    public final byte[] getMessageData() {
        return this.zzifm;
    }

    public final String getSenderParticipantId() {
        return this.zzifl;
    }

    public final boolean isReliable() {
        return this.zzifn == 1;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzifl);
        parcel.writeByteArray(this.zzifm);
        parcel.writeInt(this.zzifn);
    }
}
