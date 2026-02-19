package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.Strategy;
import com.streamax.config.constant.Constants;

public final class SubscribeRequest extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<SubscribeRequest> CREATOR = new zzcd();
    private int zzehz;
    private PendingIntent zzekk;
    @Deprecated
    private String zzkav;
    @Deprecated
    private boolean zzkaw;
    private boolean zzkbr;
    private int zzkbs;
    @Deprecated
    private String zzkct;
    private int zzkcv;
    private zzp zzkcx;
    @Deprecated
    private ClientAppContext zzkcy;
    private Strategy zzked;
    @Deprecated
    private boolean zzkee;
    private zzm zzkei;
    private MessageFilter zzkej;
    @Deprecated
    private int zzkek;
    private byte[] zzkel;
    private zzaa zzkem;

    /* JADX WARNING: type inference failed for: r1v14, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SubscribeRequest(int r11, android.os.IBinder r12, com.google.android.gms.nearby.messages.Strategy r13, android.os.IBinder r14, com.google.android.gms.nearby.messages.MessageFilter r15, android.app.PendingIntent r16, int r17, java.lang.String r18, java.lang.String r19, byte[] r20, boolean r21, android.os.IBinder r22, boolean r23, com.google.android.gms.nearby.messages.internal.ClientAppContext r24, boolean r25, int r26, int r27) {
        /*
            r10 = this;
            r0 = r10
            r1 = r12
            r2 = r14
            r3 = r18
            r4 = r19
            r5 = r22
            r6 = r23
            r10.<init>()
            r7 = r11
            r0.zzehz = r7
            r7 = 0
            if (r1 != 0) goto L_0x0016
            r8 = r7
            goto L_0x0028
        L_0x0016:
            java.lang.String r8 = "com.google.android.gms.nearby.messages.internal.IMessageListener"
            android.os.IInterface r8 = r12.queryLocalInterface(r8)
            boolean r9 = r8 instanceof com.google.android.gms.nearby.messages.internal.zzm
            if (r9 == 0) goto L_0x0023
            com.google.android.gms.nearby.messages.internal.zzm r8 = (com.google.android.gms.nearby.messages.internal.zzm) r8
            goto L_0x0028
        L_0x0023:
            com.google.android.gms.nearby.messages.internal.zzo r8 = new com.google.android.gms.nearby.messages.internal.zzo
            r8.<init>(r12)
        L_0x0028:
            r0.zzkei = r8
            r1 = r13
            r0.zzked = r1
            if (r2 != 0) goto L_0x0031
            r1 = r7
            goto L_0x0043
        L_0x0031:
            java.lang.String r1 = "com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback"
            android.os.IInterface r1 = r14.queryLocalInterface(r1)
            boolean r8 = r1 instanceof com.google.android.gms.nearby.messages.internal.zzp
            if (r8 == 0) goto L_0x003e
            com.google.android.gms.nearby.messages.internal.zzp r1 = (com.google.android.gms.nearby.messages.internal.zzp) r1
            goto L_0x0043
        L_0x003e:
            com.google.android.gms.nearby.messages.internal.zzr r1 = new com.google.android.gms.nearby.messages.internal.zzr
            r1.<init>(r14)
        L_0x0043:
            r0.zzkcx = r1
            r1 = r15
            r0.zzkej = r1
            r1 = r16
            r0.zzekk = r1
            r1 = r17
            r0.zzkek = r1
            r0.zzkav = r3
            r0.zzkct = r4
            r1 = r20
            r0.zzkel = r1
            r1 = r21
            r0.zzkee = r1
            if (r5 != 0) goto L_0x005f
            goto L_0x0075
        L_0x005f:
            if (r5 != 0) goto L_0x0062
            goto L_0x0075
        L_0x0062:
            java.lang.String r1 = "com.google.android.gms.nearby.messages.internal.ISubscribeCallback"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.nearby.messages.internal.zzaa
            if (r2 == 0) goto L_0x0070
            r7 = r1
            com.google.android.gms.nearby.messages.internal.zzaa r7 = (com.google.android.gms.nearby.messages.internal.zzaa) r7
            goto L_0x0075
        L_0x0070:
            com.google.android.gms.nearby.messages.internal.zzac r7 = new com.google.android.gms.nearby.messages.internal.zzac
            r7.<init>(r5)
        L_0x0075:
            r0.zzkem = r7
            r0.zzkaw = r6
            r1 = r24
            com.google.android.gms.nearby.messages.internal.ClientAppContext r1 = com.google.android.gms.nearby.messages.internal.ClientAppContext.zza(r1, r4, r3, r6)
            r0.zzkcy = r1
            r1 = r25
            r0.zzkbr = r1
            r1 = r26
            r0.zzkbs = r1
            r1 = r27
            r0.zzkcv = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.internal.SubscribeRequest.<init>(int, android.os.IBinder, com.google.android.gms.nearby.messages.Strategy, android.os.IBinder, com.google.android.gms.nearby.messages.MessageFilter, android.app.PendingIntent, int, java.lang.String, java.lang.String, byte[], boolean, android.os.IBinder, boolean, com.google.android.gms.nearby.messages.internal.ClientAppContext, boolean, int, int):void");
    }

    public SubscribeRequest(IBinder iBinder, Strategy strategy, IBinder iBinder2, MessageFilter messageFilter, PendingIntent pendingIntent, byte[] bArr, IBinder iBinder3, boolean z, int i) {
        this(iBinder, strategy, iBinder2, messageFilter, (PendingIntent) null, bArr, iBinder3, z, 0, i);
    }

    public SubscribeRequest(IBinder iBinder, Strategy strategy, IBinder iBinder2, MessageFilter messageFilter, PendingIntent pendingIntent, byte[] bArr, IBinder iBinder3, boolean z, int i, int i2) {
        this(3, iBinder, strategy, iBinder2, messageFilter, pendingIntent, 0, (String) null, (String) null, bArr, false, iBinder3, false, (ClientAppContext) null, z, i, i2);
    }

    public final String toString() {
        String str;
        String valueOf = String.valueOf(this.zzkei);
        String valueOf2 = String.valueOf(this.zzked);
        String valueOf3 = String.valueOf(this.zzkcx);
        String valueOf4 = String.valueOf(this.zzkej);
        String valueOf5 = String.valueOf(this.zzekk);
        byte[] bArr = this.zzkel;
        if (bArr == null) {
            str = null;
        } else {
            int length = bArr.length;
            StringBuilder sb = new StringBuilder(19);
            sb.append("<");
            sb.append(length);
            sb.append(" bytes>");
            str = sb.toString();
        }
        String valueOf6 = String.valueOf(this.zzkem);
        boolean z = this.zzkaw;
        String valueOf7 = String.valueOf(this.zzkcy);
        boolean z2 = this.zzkbr;
        String str2 = this.zzkav;
        String str3 = this.zzkct;
        boolean z3 = this.zzkee;
        int i = this.zzkcv;
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 291 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(str).length() + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb2.append("SubscribeRequest{messageListener=");
        sb2.append(valueOf);
        sb2.append(", strategy=");
        sb2.append(valueOf2);
        sb2.append(", callback=");
        sb2.append(valueOf3);
        sb2.append(", filter=");
        sb2.append(valueOf4);
        sb2.append(", pendingIntent=");
        sb2.append(valueOf5);
        sb2.append(", hint=");
        sb2.append(str);
        sb2.append(", subscribeCallback=");
        sb2.append(valueOf6);
        sb2.append(", useRealClientApiKey=");
        sb2.append(z);
        sb2.append(", clientAppContext=");
        sb2.append(valueOf7);
        sb2.append(", isDiscardPendingIntent=");
        sb2.append(z2);
        sb2.append(", zeroPartyPackageName=");
        sb2.append(str2);
        sb2.append(", realClientPackageName=");
        sb2.append(str3);
        sb2.append(", isIgnoreNearbyPermission=");
        sb2.append(z3);
        sb2.append(", callingContext=");
        sb2.append(i);
        sb2.append(Constants.JsonSstringSuffix);
        return sb2.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzm zzm = this.zzkei;
        IBinder iBinder = null;
        zzbgo.zza(parcel, 2, zzm == null ? null : zzm.asBinder(), false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzked, i, false);
        zzp zzp = this.zzkcx;
        zzbgo.zza(parcel, 4, zzp == null ? null : zzp.asBinder(), false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzkej, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzekk, i, false);
        zzbgo.zzc(parcel, 7, this.zzkek);
        zzbgo.zza(parcel, 8, this.zzkav, false);
        zzbgo.zza(parcel, 9, this.zzkct, false);
        zzbgo.zza(parcel, 10, this.zzkel, false);
        zzbgo.zza(parcel, 11, this.zzkee);
        zzaa zzaa = this.zzkem;
        if (zzaa != null) {
            iBinder = zzaa.asBinder();
        }
        zzbgo.zza(parcel, 12, iBinder, false);
        zzbgo.zza(parcel, 13, this.zzkaw);
        zzbgo.zza(parcel, 14, (Parcelable) this.zzkcy, i, false);
        zzbgo.zza(parcel, 15, this.zzkbr);
        zzbgo.zzc(parcel, 16, this.zzkbs);
        zzbgo.zzc(parcel, 17, this.zzkcv);
        zzbgo.zzai(parcel, zze);
    }
}
