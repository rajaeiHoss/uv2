package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.ChannelApi;

public final class zzaw extends zzbgl {
    public static final Parcelable.Creator<zzaw> CREATOR = new zzax();
    private int type;
    private int zzltf;
    private int zzltg;
    private zzay zzlth;

    public zzaw(zzay zzay, int i, int i2, int i3) {
        this.zzlth = zzay;
        this.type = i;
        this.zzltf = i2;
        this.zzltg = i3;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlth);
        int i = this.type;
        String num = i != 1 ? i != 2 ? i != 3 ? i != 4 ? Integer.toString(i) : "OUTPUT_CLOSED" : "INPUT_CLOSED" : "CHANNEL_CLOSED" : "CHANNEL_OPENED";
        int i2 = this.zzltf;
        String num2 = i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? Integer.toString(i2) : "CLOSE_REASON_LOCAL_CLOSE" : "CLOSE_REASON_REMOTE_CLOSE" : "CLOSE_REASON_DISCONNECTED" : "CLOSE_REASON_NORMAL";
        int i3 = this.zzltg;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 81 + String.valueOf(num).length() + String.valueOf(num2).length());
        sb.append("ChannelEventParcelable[, channel=");
        sb.append(valueOf);
        sb.append(", type=");
        sb.append(num);
        sb.append(", closeReason=");
        sb.append(num2);
        sb.append(", appErrorCode=");
        sb.append(i3);
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzlth, i, false);
        zzbgo.zzc(parcel, 3, this.type);
        zzbgo.zzc(parcel, 4, this.zzltf);
        zzbgo.zzc(parcel, 5, this.zzltg);
        zzbgo.zzai(parcel, zze);
    }

    public final void zza(ChannelApi.ChannelListener channelListener) {
        int i = this.type;
        if (i == 1) {
            channelListener.onChannelOpened(this.zzlth);
        } else if (i == 2) {
            channelListener.onChannelClosed(this.zzlth, this.zzltf, this.zzltg);
        } else if (i == 3) {
            channelListener.onInputClosed(this.zzlth, this.zzltf, this.zzltg);
        } else if (i != 4) {
            StringBuilder sb = new StringBuilder(25);
            sb.append("Unknown type: ");
            sb.append(i);
            Log.w("ChannelEventParcelable", sb.toString());
        } else {
            channelListener.onOutputClosed(this.zzlth, this.zzltf, this.zzltg);
        }
    }
}
