package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzce extends zzbgl {
    public static final Parcelable.Creator<zzce> CREATOR = new zzcf();
    private int zzehz;
    @Deprecated
    private String zzkav;
    @Deprecated
    private boolean zzkaw;
    @Deprecated
    private String zzkct;
    private zzp zzkcx;
    @Deprecated
    private ClientAppContext zzkcy;
    private zzaf zzkec;

    zzce(int i, zzaf zzaf, IBinder iBinder, String str, String str2, boolean z, ClientAppContext clientAppContext) {
        zzp zzp;
        this.zzehz = i;
        this.zzkec = zzaf;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzkcx = zzp;
        this.zzkav = str;
        this.zzkct = str2;
        this.zzkaw = z;
        this.zzkcy = ClientAppContext.zza(clientAppContext, str2, str, z);
    }

    public zzce(zzaf zzaf, IBinder iBinder) {
        this(1, zzaf, iBinder, (String) null, (String) null, false, (ClientAppContext) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzkec, i, false);
        zzbgo.zza(parcel, 3, this.zzkcx.asBinder(), false);
        zzbgo.zza(parcel, 4, this.zzkav, false);
        zzbgo.zza(parcel, 5, this.zzkct, false);
        zzbgo.zza(parcel, 6, this.zzkaw);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzkcy, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
