package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smackx.workgroup.packet.SessionID;

public final class zzaz extends zzbgl {
    public static final Parcelable.Creator<zzaz> CREATOR = new zzba();
    private final Session zzhhs;
    private final zzbzt zzhnu;

    zzaz(Session session, IBinder iBinder) {
        this.zzhhs = session;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zzaz(Session session, zzbzt zzbzt) {
        zzbq.checkArgument(session.getStartTime(TimeUnit.MILLISECONDS) < System.currentTimeMillis(), "Cannot start a session in the future");
        zzbq.checkArgument(session.isOngoing(), "Cannot start a session which has already ended");
        this.zzhhs = session;
        this.zzhnu = zzbzt;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzaz) && zzbg.equal(this.zzhhs, ((zzaz) obj).zzhhs);
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhs});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg(SessionID.ELEMENT_NAME, this.zzhhs).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhhs, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 2, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
