package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Deprecated
public final class zzh extends zzbgl {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    private int zzehz;
    @Deprecated
    private String zzkav;
    private zzp zzkcx;
    @Deprecated
    private ClientAppContext zzkcy;

    zzh(int i, IBinder iBinder, String str, ClientAppContext clientAppContext) {
        zzp zzp;
        this.zzehz = i;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzkcx = zzp;
        this.zzkav = str;
        this.zzkcy = ClientAppContext.zza(clientAppContext, (String) null, str, false);
    }

    zzh(IBinder iBinder) {
        this(1, iBinder, (String) null, (ClientAppContext) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, this.zzkcx.asBinder(), false);
        zzbgo.zza(parcel, 3, this.zzkav, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzkcy, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
