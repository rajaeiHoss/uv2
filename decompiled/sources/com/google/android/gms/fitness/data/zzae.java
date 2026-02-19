package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import org.jivesoftware.smackx.workgroup.packet.SessionID;

public final class zzae extends zzbgl {
    public static final Parcelable.Creator<zzae> CREATOR = new zzaf();
    private final Session zzhhs;
    private final DataSet zzhlq;

    public zzae(Session session, DataSet dataSet) {
        this.zzhhs = session;
        this.zzhlq = dataSet;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzae)) {
            return false;
        }
        zzae zzae = (zzae) obj;
        return zzbg.equal(this.zzhhs, zzae.zzhhs) && zzbg.equal(this.zzhlq, zzae.zzhlq);
    }

    public final DataSet getDataSet() {
        return this.zzhlq;
    }

    public final Session getSession() {
        return this.zzhhs;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhs, this.zzhlq});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg(SessionID.ELEMENT_NAME, this.zzhhs).zzg("dataSet", this.zzhlq).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhhs, i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhlq, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
