package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.HashMap;

final class zzbit extends zzbiw {
    private /* synthetic */ zzbim zzgmd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbit(zzbis zzbis, GoogleApiClient googleApiClient, zzbim zzbim) {
        super(googleApiClient);
        this.zzgmd = zzbim;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.content.Context r17, com.google.android.gms.internal.zzbjl r18) throws android.os.RemoteException {
        /*
            r16 = this;
            r1 = r16
            com.google.android.gms.common.data.DataHolder$zza r0 = com.google.android.gms.common.data.zzd.zzalh()
            com.google.android.gms.internal.zzbim r2 = r1.zzgmd
            java.util.Map r2 = r2.zzaog()
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0014:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0035
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            com.google.android.gms.internal.zzbjd r4 = new com.google.android.gms.internal.zzbjd
            java.lang.Object r5 = r3.getKey()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r3 = r3.getValue()
            java.lang.String r3 = (java.lang.String) r3
            r4.<init>(r5, r3)
            com.google.android.gms.common.data.zzd.zza(r0, r4)
            goto L_0x0014
        L_0x0035:
            r2 = 0
            com.google.android.gms.common.data.DataHolder r2 = r0.zzca(r2)
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.internal.zzbz.zzcl(r17)
            com.google.android.gms.common.api.Status r3 = com.google.android.gms.common.api.Status.zzftq
            r4 = 0
            if (r0 != r3) goto L_0x0049
            java.lang.String r0 = com.google.android.gms.common.api.internal.zzbz.zzakq()
            r8 = r0
            goto L_0x004a
        L_0x0049:
            r8 = r4
        L_0x004a:
            com.google.firebase.iid.FirebaseInstanceId r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance()     // Catch:{ IllegalStateException -> 0x005f }
            java.lang.String r3 = r0.getId()     // Catch:{ IllegalStateException -> 0x005f }
            com.google.firebase.iid.FirebaseInstanceId r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance()     // Catch:{ IllegalStateException -> 0x005d }
            java.lang.String r0 = r0.getToken()     // Catch:{ IllegalStateException -> 0x005d }
            r10 = r0
            r9 = r3
            goto L_0x0071
        L_0x005d:
            r0 = move-exception
            goto L_0x0061
        L_0x005f:
            r0 = move-exception
            r3 = r4
        L_0x0061:
            r5 = 3
            java.lang.String r6 = "ConfigApiImpl"
            boolean r5 = android.util.Log.isLoggable(r6, r5)
            if (r5 == 0) goto L_0x006f
            java.lang.String r5 = "Cannot retrieve instanceId or instanceIdToken."
            android.util.Log.d(r6, r5, r0)
        L_0x006f:
            r9 = r3
            r10 = r4
        L_0x0071:
            java.util.List r13 = com.google.android.gms.internal.zzbir.zzdf(r17)
            com.google.android.gms.internal.zzbjf r0 = new com.google.android.gms.internal.zzbjf
            java.lang.String r4 = r17.getPackageName()
            com.google.android.gms.internal.zzbim r3 = r1.zzgmd
            long r5 = r3.zzaof()
            r11 = 0
            com.google.android.gms.internal.zzbim r3 = r1.zzgmd
            int r12 = r3.zzaoh()
            com.google.android.gms.internal.zzbim r3 = r1.zzgmd
            int r14 = r3.zzaoi()
            com.google.android.gms.internal.zzbim r3 = r1.zzgmd
            int r15 = r3.zzaoj()
            r3 = r0
            r7 = r2
            r3.<init>(r4, r5, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            com.google.android.gms.internal.zzbjj r3 = r1.zzgme     // Catch:{ all -> 0x00a4 }
            r4 = r18
            r4.zza(r3, r0)     // Catch:{ all -> 0x00a4 }
            r2.close()
            return
        L_0x00a4:
            r0 = move-exception
            r2.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbit.zza(android.content.Context, com.google.android.gms.internal.zzbjl):void");
    }

    /* access modifiers changed from: protected */
    public final zzbio zzb(Status status) {
        return new zzbiy(status, new HashMap());
    }
}
