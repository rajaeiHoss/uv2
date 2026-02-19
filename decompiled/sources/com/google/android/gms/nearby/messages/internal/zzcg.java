package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzcg extends zzbgl {
    public static final Parcelable.Creator<zzcg> CREATOR = new zzch();
    private int zzehz;
    private PendingIntent zzekk;
    @Deprecated
    private String zzkav;
    @Deprecated
    private boolean zzkaw;
    @Deprecated
    private String zzkct;
    private zzp zzkcx;
    @Deprecated
    private ClientAppContext zzkcy;
    private zzm zzkei;
    @Deprecated
    private int zzkek;

    public zzcg(int i, IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent, int i2, String str, String str2, boolean z, ClientAppContext clientAppContext) {
        zzm zzm;
        this.zzehz = i;
        zzp zzp = null;
        if (iBinder == null) {
            zzm = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IMessageListener");
            zzm = queryLocalInterface instanceof zzm ? (zzm) queryLocalInterface : new zzo(iBinder);
        }
        this.zzkei = zzm;
        if (iBinder2 != null) {
            IInterface queryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface2 instanceof zzp ? (zzp) queryLocalInterface2 : new zzr(iBinder2);
        }
        this.zzkcx = zzp;
        this.zzekk = pendingIntent;
        this.zzkek = i2;
        this.zzkav = str;
        this.zzkct = str2;
        this.zzkaw = z;
        this.zzkcy = ClientAppContext.zza(clientAppContext, str2, str, z);
    }

    public zzcg(IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent) {
        this(1, iBinder, iBinder2, pendingIntent, 0, (String) null, (String) null, false, (ClientAppContext) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzm zzm = this.zzkei;
        zzbgo.zza(parcel, 2, zzm == null ? null : zzm.asBinder(), false);
        zzbgo.zza(parcel, 3, this.zzkcx.asBinder(), false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzekk, i, false);
        zzbgo.zzc(parcel, 5, this.zzkek);
        zzbgo.zza(parcel, 6, this.zzkav, false);
        zzbgo.zza(parcel, 7, this.zzkct, false);
        zzbgo.zza(parcel, 8, this.zzkaw);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzkcy, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
