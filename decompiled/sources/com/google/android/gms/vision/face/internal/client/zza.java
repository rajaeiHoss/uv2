package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzdlc;
import com.google.android.gms.internal.zzdld;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

public final class zza extends zzdlc<zze> {
    private final zzc zzlhi;

    public zza(Context context, zzc zzc) {
        super(context, "FaceNativeHandle");
        this.zzlhi = zzc;
        zzblo();
    }

    private static Landmark[] zza(FaceParcel faceParcel) {
        LandmarkParcel[] landmarkParcelArr = faceParcel.zzlhl;
        if (landmarkParcelArr == null) {
            return new Landmark[0];
        }
        Landmark[] landmarkArr = new Landmark[landmarkParcelArr.length];
        for (int i = 0; i < landmarkParcelArr.length; i++) {
            LandmarkParcel landmarkParcel = landmarkParcelArr[i];
            landmarkArr[i] = new Landmark(new PointF(landmarkParcel.x, landmarkParcel.y), landmarkParcel.type);
        }
        return landmarkArr;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zze zza(com.google.android.gms.dynamite.DynamiteModule r3, android.content.Context r4) throws android.os.RemoteException, com.google.android.gms.dynamite.DynamiteModule.zzc {
        /*
            r2 = this;
            java.lang.String r0 = "com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"
            android.os.IBinder r3 = r3.zzhk(r0)
            if (r3 != 0) goto L_0x000a
            r3 = 0
            goto L_0x001e
        L_0x000a:
            java.lang.String r0 = "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator"
            android.os.IInterface r0 = r3.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.vision.face.internal.client.zzg
            if (r1 == 0) goto L_0x0018
            r3 = r0
            com.google.android.gms.vision.face.internal.client.zzg r3 = (com.google.android.gms.vision.face.internal.client.zzg) r3
            goto L_0x001e
        L_0x0018:
            com.google.android.gms.vision.face.internal.client.zzh r0 = new com.google.android.gms.vision.face.internal.client.zzh
            r0.<init>(r3)
            r3 = r0
        L_0x001e:
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.zzn.zzz(r4)
            com.google.android.gms.vision.face.internal.client.zzc r0 = r2.zzlhi
            com.google.android.gms.vision.face.internal.client.zze r3 = r3.zza(r4, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.face.internal.client.zza.zza(com.google.android.gms.dynamite.DynamiteModule, android.content.Context):java.lang.Object");
    }

    public final Face[] zzb(ByteBuffer byteBuffer, zzdld zzdld) {
        if (!isOperational()) {
            return new Face[0];
        }
        try {
            FaceParcel[] zzc = ((zze) zzblo()).zzc(zzn.zzz(byteBuffer), zzdld);
            Face[] faceArr = new Face[zzc.length];
            for (int i = 0; i < zzc.length; i++) {
                FaceParcel faceParcel = zzc[i];
                faceArr[i] = new Face(faceParcel.id, new PointF(faceParcel.centerX, faceParcel.centerY), faceParcel.width, faceParcel.height, faceParcel.zzlhj, faceParcel.zzlhk, zza(faceParcel), faceParcel.zzlhm, faceParcel.zzlhn, faceParcel.zzlho);
            }
            return faceArr;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new Face[0];
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbll() throws RemoteException {
        ((zze) zzblo()).zzblm();
    }

    public final boolean zzfo(int i) {
        if (!isOperational()) {
            return false;
        }
        try {
            return ((zze) zzblo()).zzfo(i);
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return false;
        }
    }
}
