package com.google.firebase.database.connection.idl;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.zzew;

public abstract class zzu extends zzew implements zzt {
    public zzu() {
        attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnection");
    }

    public static zzt asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        return queryLocalInterface instanceof zzt ? (zzt) queryLocalInterface : new zzv(iBinder);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.firebase.database.connection.idl.zzw] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v11, types: [com.google.firebase.database.connection.idl.zzah] */
    /* JADX WARNING: type inference failed for: r1v16, types: [com.google.firebase.database.connection.idl.zzah] */
    /* JADX WARNING: type inference failed for: r1v21, types: [com.google.firebase.database.connection.idl.zzah] */
    /* JADX WARNING: type inference failed for: r1v26, types: [com.google.firebase.database.connection.idl.zzah] */
    /* JADX WARNING: type inference failed for: r1v31, types: [com.google.firebase.database.connection.idl.zzah] */
    /* JADX WARNING: type inference failed for: r1v36, types: [com.google.firebase.database.connection.idl.zzah] */
    /* JADX WARNING: type inference failed for: r1v41 */
    /* JADX WARNING: type inference failed for: r1v42 */
    /* JADX WARNING: type inference failed for: r1v43 */
    /* JADX WARNING: type inference failed for: r1v44 */
    /* JADX WARNING: type inference failed for: r1v45 */
    /* JADX WARNING: type inference failed for: r1v46 */
    /* JADX WARNING: type inference failed for: r1v47 */
    /* JADX WARNING: type inference failed for: r1v48 */
    /* JADX WARNING: type inference failed for: r1v49 */
    /* JADX WARNING: type inference failed for: r1v50 */
    /* JADX WARNING: type inference failed for: r1v51 */
    /* JADX WARNING: type inference failed for: r1v52 */
    /* JADX WARNING: type inference failed for: r1v53 */
    /* JADX WARNING: type inference failed for: r1v54 */
    /* JADX WARNING: type inference failed for: r1v55 */
    /* JADX WARNING: type inference failed for: r1v56 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
        /*
            r8 = this;
            boolean r12 = r8.zza(r9, r10, r11, r12)
            r0 = 1
            if (r12 == 0) goto L_0x0008
            return r0
        L_0x0008:
            java.lang.String r12 = "com.google.firebase.database.connection.idl.IRequestResultCallback"
            r1 = 0
            switch(r9) {
                case 1: goto L_0x019a;
                case 2: goto L_0x0193;
                case 3: goto L_0x018f;
                case 4: goto L_0x018b;
                case 5: goto L_0x0142;
                case 6: goto L_0x0132;
                case 7: goto L_0x012d;
                case 8: goto L_0x0104;
                case 9: goto L_0x00d7;
                case 10: goto L_0x00ae;
                case 11: goto L_0x0085;
                case 12: goto L_0x005c;
                case 13: goto L_0x003b;
                case 14: goto L_0x0032;
                case 15: goto L_0x0029;
                case 16: goto L_0x0019;
                case 17: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            r9 = 0
            return r9
        L_0x0010:
            java.lang.String r9 = r10.readString()
            r8.refreshAuthToken2(r9)
            goto L_0x0196
        L_0x0019:
            java.lang.String r9 = r10.readString()
            boolean r9 = r8.isInterrupted(r9)
            r11.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r11, (boolean) r9)
            goto L_0x01e1
        L_0x0029:
            java.lang.String r9 = r10.readString()
            r8.resume(r9)
            goto L_0x0196
        L_0x0032:
            java.lang.String r9 = r10.readString()
            r8.interrupt(r9)
            goto L_0x0196
        L_0x003b:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x0046
            goto L_0x0057
        L_0x0046:
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r1 = r12 instanceof com.google.firebase.database.connection.idl.zzah
            if (r1 == 0) goto L_0x0052
            r1 = r12
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x0057
        L_0x0052:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r10)
        L_0x0057:
            r8.onDisconnectCancel(r9, r1)
            goto L_0x0196
        L_0x005c:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r2 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x006f
            goto L_0x0080
        L_0x006f:
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r1 = r12 instanceof com.google.firebase.database.connection.idl.zzah
            if (r1 == 0) goto L_0x007b
            r1 = r12
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x0080
        L_0x007b:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r10)
        L_0x0080:
            r8.onDisconnectMerge(r9, r2, r1)
            goto L_0x0196
        L_0x0085:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r2 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x0098
            goto L_0x00a9
        L_0x0098:
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r1 = r12 instanceof com.google.firebase.database.connection.idl.zzah
            if (r1 == 0) goto L_0x00a4
            r1 = r12
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x00a9
        L_0x00a4:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r10)
        L_0x00a9:
            r8.onDisconnectPut(r9, r2, r1)
            goto L_0x0196
        L_0x00ae:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r2 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x00c1
            goto L_0x00d2
        L_0x00c1:
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r1 = r12 instanceof com.google.firebase.database.connection.idl.zzah
            if (r1 == 0) goto L_0x00cd
            r1 = r12
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x00d2
        L_0x00cd:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r10)
        L_0x00d2:
            r8.merge(r9, r2, r1)
            goto L_0x0196
        L_0x00d7:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r2 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            java.lang.String r3 = r10.readString()
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x00ee
            goto L_0x00ff
        L_0x00ee:
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r1 = r12 instanceof com.google.firebase.database.connection.idl.zzah
            if (r1 == 0) goto L_0x00fa
            r1 = r12
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x00ff
        L_0x00fa:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r10)
        L_0x00ff:
            r8.compareAndPut(r9, r2, r3, r1)
            goto L_0x0196
        L_0x0104:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r2 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x0117
            goto L_0x0128
        L_0x0117:
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r1 = r12 instanceof com.google.firebase.database.connection.idl.zzah
            if (r1 == 0) goto L_0x0123
            r1 = r12
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x0128
        L_0x0123:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r10)
        L_0x0128:
            r8.put(r9, r2, r1)
            goto L_0x0196
        L_0x012d:
            r8.purgeOutstandingWrites()
            goto L_0x0196
        L_0x0132:
            java.util.ArrayList r9 = r10.createStringArrayList()
            android.os.IBinder r10 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r10)
            r8.unlisten(r9, r10)
            goto L_0x0196
        L_0x0142:
            java.util.ArrayList r2 = r10.createStringArrayList()
            android.os.IBinder r9 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r9)
            android.os.IBinder r9 = r10.readStrongBinder()
            if (r9 != 0) goto L_0x0156
            r4 = r1
            goto L_0x0168
        L_0x0156:
            java.lang.String r4 = "com.google.firebase.database.connection.idl.IListenHashProvider"
            android.os.IInterface r4 = r9.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.firebase.database.connection.idl.zzq
            if (r5 == 0) goto L_0x0163
            com.google.firebase.database.connection.idl.zzq r4 = (com.google.firebase.database.connection.idl.zzq) r4
            goto L_0x0168
        L_0x0163:
            com.google.firebase.database.connection.idl.zzs r4 = new com.google.firebase.database.connection.idl.zzs
            r4.<init>(r9)
        L_0x0168:
            long r5 = r10.readLong()
            android.os.IBinder r9 = r10.readStrongBinder()
            if (r9 != 0) goto L_0x0174
        L_0x0172:
            r7 = r1
            goto L_0x0186
        L_0x0174:
            android.os.IInterface r10 = r9.queryLocalInterface(r12)
            boolean r12 = r10 instanceof com.google.firebase.database.connection.idl.zzah
            if (r12 == 0) goto L_0x0180
            r1 = r10
            com.google.firebase.database.connection.idl.zzah r1 = (com.google.firebase.database.connection.idl.zzah) r1
            goto L_0x0172
        L_0x0180:
            com.google.firebase.database.connection.idl.zzaj r1 = new com.google.firebase.database.connection.idl.zzaj
            r1.<init>(r9)
            goto L_0x0172
        L_0x0186:
            r1 = r8
            r1.listen(r2, r3, r4, r5, r7)
            goto L_0x0196
        L_0x018b:
            r8.refreshAuthToken()
            goto L_0x0196
        L_0x018f:
            r8.shutdown()
            goto L_0x0196
        L_0x0193:
            r8.initialize()
        L_0x0196:
            r11.writeNoException()
            goto L_0x01e1
        L_0x019a:
            android.os.Parcelable$Creator<com.google.firebase.database.connection.idl.zzc> r9 = com.google.firebase.database.connection.idl.zzc.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r10, r9)
            com.google.firebase.database.connection.idl.zzc r9 = (com.google.firebase.database.connection.idl.zzc) r9
            android.os.IBinder r12 = r10.readStrongBinder()
            if (r12 != 0) goto L_0x01aa
            r2 = r1
            goto L_0x01bc
        L_0x01aa:
            java.lang.String r2 = "com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider"
            android.os.IInterface r2 = r12.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.firebase.database.connection.idl.zzk
            if (r3 == 0) goto L_0x01b7
            com.google.firebase.database.connection.idl.zzk r2 = (com.google.firebase.database.connection.idl.zzk) r2
            goto L_0x01bc
        L_0x01b7:
            com.google.firebase.database.connection.idl.zzm r2 = new com.google.firebase.database.connection.idl.zzm
            r2.<init>(r12)
        L_0x01bc:
            android.os.IBinder r12 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r12 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r12)
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x01cb
            goto L_0x01dd
        L_0x01cb:
            java.lang.String r1 = "com.google.firebase.database.connection.idl.IPersistentConnectionDelegate"
            android.os.IInterface r1 = r10.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.firebase.database.connection.idl.zzw
            if (r3 == 0) goto L_0x01d8
            com.google.firebase.database.connection.idl.zzw r1 = (com.google.firebase.database.connection.idl.zzw) r1
            goto L_0x01dd
        L_0x01d8:
            com.google.firebase.database.connection.idl.zzy r1 = new com.google.firebase.database.connection.idl.zzy
            r1.<init>(r10)
        L_0x01dd:
            r8.setup(r9, r2, r12, r1)
            goto L_0x0196
        L_0x01e1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.connection.idl.zzu.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
