package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.nearby.messages.Strategy;

public final class zzbz extends zzbgl {
    public static final Parcelable.Creator<zzbz> CREATOR = new zzca();
    private int zzehz;
    @Deprecated
    private String zzkav;
    @Deprecated
    private boolean zzkaw;
    @Deprecated
    private String zzkct;
    private int zzkcv;
    private zzp zzkcx;
    @Deprecated
    private ClientAppContext zzkcy;
    private zzaf zzkec;
    private Strategy zzked;
    @Deprecated
    private boolean zzkee;
    private zzu zzkef;

    zzbz(int i, zzaf zzaf, Strategy strategy, IBinder iBinder, String str, String str2, boolean z, IBinder iBinder2, boolean z2, ClientAppContext clientAppContext, int i2) {
        zzp zzp;
        this.zzehz = i;
        this.zzkec = zzaf;
        this.zzked = strategy;
        zzu zzu = null;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzkcx = zzp;
        this.zzkav = str;
        this.zzkct = str2;
        this.zzkee = z;
        if (!(iBinder2 == null || iBinder2 == null)) {
            IInterface queryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IPublishCallback");
            zzu = queryLocalInterface2 instanceof zzu ? (zzu) queryLocalInterface2 : new zzw(iBinder2);
        }
        this.zzkef = zzu;
        this.zzkaw = z2;
        this.zzkcy = ClientAppContext.zza(clientAppContext, str2, str, z2);
        this.zzkcv = i2;
    }

    public zzbz(zzaf zzaf, Strategy strategy, IBinder iBinder, IBinder iBinder2, int i) {
        this(2, zzaf, strategy, iBinder, (String) null, (String) null, false, iBinder2, false, (ClientAppContext) null, i);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzkec, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzked, i, false);
        zzbgo.zza(parcel, 4, this.zzkcx.asBinder(), false);
        zzbgo.zza(parcel, 5, this.zzkav, false);
        zzbgo.zza(parcel, 6, this.zzkct, false);
        zzbgo.zza(parcel, 7, this.zzkee);
        zzu zzu = this.zzkef;
        zzbgo.zza(parcel, 8, zzu == null ? null : zzu.asBinder(), false);
        zzbgo.zza(parcel, 9, this.zzkaw);
        zzbgo.zza(parcel, 10, (Parcelable) this.zzkcy, i, false);
        zzbgo.zzc(parcel, 11, this.zzkcv);
        zzbgo.zzai(parcel, zze);
    }
}
