package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.nearby.messages.Message;
import com.streamax.config.constant.Constants;
import java.util.Arrays;

public final class zzaf extends zzbgl {
    public static final Parcelable.Creator<zzaf> CREATOR = new zzag();
    private int zzehz;
    private Message zzkda;

    zzaf(int i, Message message) {
        this.zzehz = i;
        this.zzkda = (Message) zzbq.checkNotNull(message);
    }

    public static final zzaf zza(Message message) {
        return new zzaf(1, message);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaf)) {
            return false;
        }
        return zzbg.equal(this.zzkda, ((zzaf) obj).zzkda);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkda});
    }

    public final String toString() {
        String message = this.zzkda.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 24);
        sb.append("MessageWrapper{message=");
        sb.append(message);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzkda, i, false);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }
}
