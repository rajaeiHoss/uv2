package com.google.android.gms.internal;

public abstract class zzcjc extends zzew implements zzcjb {
    public zzcjc() {
        attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00e4, code lost:
        r10.writeNoException();
        r10.writeTypedList(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x013e, code lost:
        r10.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0141, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r8, android.os.Parcel r9, android.os.Parcel r10, int r11) throws android.os.RemoteException {
        /*
            r7 = this;
            boolean r11 = r7.zza(r8, r9, r10, r11)
            r0 = 1
            if (r11 == 0) goto L_0x0008
            return r0
        L_0x0008:
            switch(r8) {
                case 1: goto L_0x012b;
                case 2: goto L_0x0117;
                case 3: goto L_0x000b;
                case 4: goto L_0x010b;
                case 5: goto L_0x00f7;
                case 6: goto L_0x00eb;
                case 7: goto L_0x00d4;
                case 8: goto L_0x000b;
                case 9: goto L_0x00bd;
                case 10: goto L_0x00a7;
                case 11: goto L_0x0093;
                case 12: goto L_0x007e;
                case 13: goto L_0x0071;
                case 14: goto L_0x0058;
                case 15: goto L_0x0042;
                case 16: goto L_0x002c;
                case 17: goto L_0x001a;
                case 18: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r8 = 0
            return r8
        L_0x000d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r8 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcif r8 = (com.google.android.gms.internal.zzcif) r8
            r7.zzd(r8)
            goto L_0x013e
        L_0x001a:
            java.lang.String r8 = r9.readString()
            java.lang.String r11 = r9.readString()
            java.lang.String r9 = r9.readString()
            java.util.List r8 = r7.zzk(r8, r11, r9)
            goto L_0x00e4
        L_0x002c:
            java.lang.String r8 = r9.readString()
            java.lang.String r11 = r9.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r1 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r1)
            com.google.android.gms.internal.zzcif r9 = (com.google.android.gms.internal.zzcif) r9
            java.util.List r8 = r7.zza((java.lang.String) r8, (java.lang.String) r11, (com.google.android.gms.internal.zzcif) r9)
            goto L_0x00e4
        L_0x0042:
            java.lang.String r8 = r9.readString()
            java.lang.String r11 = r9.readString()
            java.lang.String r1 = r9.readString()
            boolean r9 = com.google.android.gms.internal.zzex.zza(r9)
            java.util.List r8 = r7.zza((java.lang.String) r8, (java.lang.String) r11, (java.lang.String) r1, (boolean) r9)
            goto L_0x00e4
        L_0x0058:
            java.lang.String r8 = r9.readString()
            java.lang.String r11 = r9.readString()
            boolean r1 = com.google.android.gms.internal.zzex.zza(r9)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r2 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r2)
            com.google.android.gms.internal.zzcif r9 = (com.google.android.gms.internal.zzcif) r9
            java.util.List r8 = r7.zza((java.lang.String) r8, (java.lang.String) r11, (boolean) r1, (com.google.android.gms.internal.zzcif) r9)
            goto L_0x00e4
        L_0x0071:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcii> r8 = com.google.android.gms.internal.zzcii.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcii r8 = (com.google.android.gms.internal.zzcii) r8
            r7.zzb((com.google.android.gms.internal.zzcii) r8)
            goto L_0x013e
        L_0x007e:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcii> r8 = com.google.android.gms.internal.zzcii.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcii r8 = (com.google.android.gms.internal.zzcii) r8
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r11 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r11)
            com.google.android.gms.internal.zzcif r9 = (com.google.android.gms.internal.zzcif) r9
            r7.zza((com.google.android.gms.internal.zzcii) r8, (com.google.android.gms.internal.zzcif) r9)
            goto L_0x013e
        L_0x0093:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r8 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcif r8 = (com.google.android.gms.internal.zzcif) r8
            java.lang.String r8 = r7.zzc(r8)
            r10.writeNoException()
            r10.writeString(r8)
            goto L_0x0141
        L_0x00a7:
            long r2 = r9.readLong()
            java.lang.String r4 = r9.readString()
            java.lang.String r5 = r9.readString()
            java.lang.String r6 = r9.readString()
            r1 = r7
            r1.zza((long) r2, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6)
            goto L_0x013e
        L_0x00bd:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r8 = com.google.android.gms.internal.zzcix.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcix r8 = (com.google.android.gms.internal.zzcix) r8
            java.lang.String r9 = r9.readString()
            byte[] r8 = r7.zza((com.google.android.gms.internal.zzcix) r8, (java.lang.String) r9)
            r10.writeNoException()
            r10.writeByteArray(r8)
            goto L_0x0141
        L_0x00d4:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r8 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcif r8 = (com.google.android.gms.internal.zzcif) r8
            boolean r9 = com.google.android.gms.internal.zzex.zza(r9)
            java.util.List r8 = r7.zza((com.google.android.gms.internal.zzcif) r8, (boolean) r9)
        L_0x00e4:
            r10.writeNoException()
            r10.writeTypedList(r8)
            goto L_0x0141
        L_0x00eb:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r8 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcif r8 = (com.google.android.gms.internal.zzcif) r8
            r7.zzb((com.google.android.gms.internal.zzcif) r8)
            goto L_0x013e
        L_0x00f7:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r8 = com.google.android.gms.internal.zzcix.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcix r8 = (com.google.android.gms.internal.zzcix) r8
            java.lang.String r11 = r9.readString()
            java.lang.String r9 = r9.readString()
            r7.zza((com.google.android.gms.internal.zzcix) r8, (java.lang.String) r11, (java.lang.String) r9)
            goto L_0x013e
        L_0x010b:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r8 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcif r8 = (com.google.android.gms.internal.zzcif) r8
            r7.zza(r8)
            goto L_0x013e
        L_0x0117:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcnl> r8 = com.google.android.gms.internal.zzcnl.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcnl r8 = (com.google.android.gms.internal.zzcnl) r8
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r11 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r11)
            com.google.android.gms.internal.zzcif r9 = (com.google.android.gms.internal.zzcif) r9
            r7.zza((com.google.android.gms.internal.zzcnl) r8, (com.google.android.gms.internal.zzcif) r9)
            goto L_0x013e
        L_0x012b:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcix> r8 = com.google.android.gms.internal.zzcix.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            com.google.android.gms.internal.zzcix r8 = (com.google.android.gms.internal.zzcix) r8
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcif> r11 = com.google.android.gms.internal.zzcif.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r11)
            com.google.android.gms.internal.zzcif r9 = (com.google.android.gms.internal.zzcif) r9
            r7.zza((com.google.android.gms.internal.zzcix) r8, (com.google.android.gms.internal.zzcif) r9)
        L_0x013e:
            r10.writeNoException()
        L_0x0141:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcjc.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
