package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzfbl extends zzev implements zzfbk {
    zzfbl(IBinder iBinder) {
        super(iBinder, "com.google.firebase.storage.network.INetworkRequestFactory");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zza(android.net.Uri r3, com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r3 = 1
            android.os.Parcel r3 = r2.zza(r3, r0)
            android.os.IBinder r4 = r3.readStrongBinder()
            if (r4 != 0) goto L_0x0017
            r4 = 0
            goto L_0x002b
        L_0x0017:
            java.lang.String r0 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzfbi
            if (r1 == 0) goto L_0x0025
            r4 = r0
            com.google.android.gms.internal.zzfbi r4 = (com.google.android.gms.internal.zzfbi) r4
            goto L_0x002b
        L_0x0025:
            com.google.android.gms.internal.zzfbj r0 = new com.google.android.gms.internal.zzfbj
            r0.<init>(r4)
            r4 = r0
        L_0x002b:
            r3.recycle()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zza(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zza(android.net.Uri r2, com.google.android.gms.dynamic.IObjectWrapper r3, long r4) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            r0.writeLong(r4)
            r2 = 3
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001a
            r3 = 0
            goto L_0x002e
        L_0x001a:
            java.lang.String r4 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzfbi
            if (r5 == 0) goto L_0x0028
            r3 = r4
            com.google.android.gms.internal.zzfbi r3 = (com.google.android.gms.internal.zzfbi) r3
            goto L_0x002e
        L_0x0028:
            com.google.android.gms.internal.zzfbj r4 = new com.google.android.gms.internal.zzfbj
            r4.<init>(r3)
            r3 = r4
        L_0x002e:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zza(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper, long):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zza(android.net.Uri r2, com.google.android.gms.dynamic.IObjectWrapper r3, com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r2 = 9
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001b
            r3 = 0
            goto L_0x002f
        L_0x001b:
            java.lang.String r4 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r0 = r4 instanceof com.google.android.gms.internal.zzfbi
            if (r0 == 0) goto L_0x0029
            r3 = r4
            com.google.android.gms.internal.zzfbi r3 = (com.google.android.gms.internal.zzfbi) r3
            goto L_0x002f
        L_0x0029:
            com.google.android.gms.internal.zzfbj r4 = new com.google.android.gms.internal.zzfbj
            r4.<init>(r3)
            r3 = r4
        L_0x002f:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zza(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zza(android.net.Uri r2, com.google.android.gms.dynamic.IObjectWrapper r3, com.google.android.gms.dynamic.IObjectWrapper r4, java.lang.String r5) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r0.writeString(r5)
            r2 = 8
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001e
            r3 = 0
            goto L_0x0032
        L_0x001e:
            java.lang.String r4 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzfbi
            if (r5 == 0) goto L_0x002c
            r3 = r4
            com.google.android.gms.internal.zzfbi r3 = (com.google.android.gms.internal.zzfbi) r3
            goto L_0x0032
        L_0x002c:
            com.google.android.gms.internal.zzfbj r4 = new com.google.android.gms.internal.zzfbj
            r4.<init>(r3)
            r3 = r4
        L_0x0032:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zza(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.dynamic.IObjectWrapper, java.lang.String):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zza(android.net.Uri r2, com.google.android.gms.dynamic.IObjectWrapper r3, java.lang.String r4) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            r0.writeString(r4)
            r2 = 6
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001a
            r3 = 0
            goto L_0x002e
        L_0x001a:
            java.lang.String r4 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r0 = r4 instanceof com.google.android.gms.internal.zzfbi
            if (r0 == 0) goto L_0x0028
            r3 = r4
            com.google.android.gms.internal.zzfbi r3 = (com.google.android.gms.internal.zzfbi) r3
            goto L_0x002e
        L_0x0028:
            com.google.android.gms.internal.zzfbj r4 = new com.google.android.gms.internal.zzfbj
            r4.<init>(r3)
            r3 = r4
        L_0x002e:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zza(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper, java.lang.String):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zza(android.net.Uri r2, com.google.android.gms.dynamic.IObjectWrapper r3, java.lang.String r4, com.google.android.gms.dynamic.IObjectWrapper r5, long r6, int r8, boolean r9) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            r0.writeString(r4)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r5)
            r0.writeLong(r6)
            r0.writeInt(r8)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (boolean) r9)
            r2 = 5
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0026
            r3 = 0
            goto L_0x003a
        L_0x0026:
            java.lang.String r4 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzfbi
            if (r5 == 0) goto L_0x0034
            r3 = r4
            com.google.android.gms.internal.zzfbi r3 = (com.google.android.gms.internal.zzfbi) r3
            goto L_0x003a
        L_0x0034:
            com.google.android.gms.internal.zzfbj r4 = new com.google.android.gms.internal.zzfbj
            r4.<init>(r3)
            r3 = r4
        L_0x003a:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zza(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper, java.lang.String, com.google.android.gms.dynamic.IObjectWrapper, long, int, boolean):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zzb(android.net.Uri r3, com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r3 = 2
            android.os.Parcel r3 = r2.zza(r3, r0)
            android.os.IBinder r4 = r3.readStrongBinder()
            if (r4 != 0) goto L_0x0017
            r4 = 0
            goto L_0x002b
        L_0x0017:
            java.lang.String r0 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzfbi
            if (r1 == 0) goto L_0x0025
            r4 = r0
            com.google.android.gms.internal.zzfbi r4 = (com.google.android.gms.internal.zzfbi) r4
            goto L_0x002b
        L_0x0025:
            com.google.android.gms.internal.zzfbj r0 = new com.google.android.gms.internal.zzfbj
            r0.<init>(r4)
            r4 = r0
        L_0x002b:
            r3.recycle()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zzb(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.internal.zzfbi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfbi zzb(android.net.Uri r2, com.google.android.gms.dynamic.IObjectWrapper r3, java.lang.String r4) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            r0.writeString(r4)
            r2 = 7
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001a
            r3 = 0
            goto L_0x002e
        L_0x001a:
            java.lang.String r4 = "com.google.firebase.storage.network.INetworkRequest"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r0 = r4 instanceof com.google.android.gms.internal.zzfbi
            if (r0 == 0) goto L_0x0028
            r3 = r4
            com.google.android.gms.internal.zzfbi r3 = (com.google.android.gms.internal.zzfbi) r3
            goto L_0x002e
        L_0x0028:
            com.google.android.gms.internal.zzfbj r4 = new com.google.android.gms.internal.zzfbj
            r4.<init>(r3)
            r3 = r4
        L_0x002e:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbl.zzb(android.net.Uri, com.google.android.gms.dynamic.IObjectWrapper, java.lang.String):com.google.android.gms.internal.zzfbi");
    }

    public final String zzcou() throws RemoteException {
        Parcel zza = zza(10, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final String zzu(Uri uri) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) uri);
        Parcel zza = zza(11, zzbc);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }
}
