package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzcb extends zzbgl {
    public static final Parcelable.Creator<zzcb> CREATOR = new zzcc();
    private int versionCode;
    @Deprecated
    private String zzkav;
    private zzp zzkcx;
    @Deprecated
    private ClientAppContext zzkcy;
    private zzx zzkeg;
    public boolean zzkeh;

    zzcb(int i, IBinder iBinder, IBinder iBinder2, boolean z, String str, ClientAppContext clientAppContext) {
        zzp zzp;
        zzx zzx;
        this.versionCode = i;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzkcx = zzp;
        if (iBinder2 == null) {
            zzx = null;
        } else {
            IInterface queryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IStatusCallback");
            zzx = queryLocalInterface2 instanceof zzx ? (zzx) queryLocalInterface2 : new zzz(iBinder2);
        }
        this.zzkeg = zzx;
        this.zzkeh = z;
        this.zzkav = str;
        this.zzkcy = ClientAppContext.zza(clientAppContext, (String) null, str, false);
    }

    public zzcb(IBinder iBinder, IBinder iBinder2) {
        this(1, iBinder, iBinder2, false, (String) null, (ClientAppContext) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, this.zzkcx.asBinder(), false);
        zzbgo.zza(parcel, 3, this.zzkeg.asBinder(), false);
        zzbgo.zza(parcel, 4, this.zzkeh);
        zzbgo.zza(parcel, 5, this.zzkav, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzkcy, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
