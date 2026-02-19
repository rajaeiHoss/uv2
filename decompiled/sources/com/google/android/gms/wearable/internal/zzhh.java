package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import java.util.concurrent.Callable;

final class zzhh implements Callable<Boolean> {
    private /* synthetic */ byte[] zzlbv;
    private /* synthetic */ ParcelFileDescriptor zzlvu;

    zzhh(zzhg zzhg, ParcelFileDescriptor parcelFileDescriptor, byte[] bArr) {
        this.zzlvu = parcelFileDescriptor;
        this.zzlbv = bArr;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0099, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4 = java.lang.String.valueOf(r7.zzlvu);
        r6 = new java.lang.StringBuilder(java.lang.String.valueOf(r4).length() + 36);
        r6.append("processAssets: writing data failed: ");
        r6.append(r4);
        android.util.Log.w("WearableClient", r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ef, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f4, code lost:
        if (android.util.Log.isLoggable("WearableClient", 3) != false) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f6, code lost:
        r2 = java.lang.String.valueOf(r7.zzlvu);
        r6 = new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 24);
        r6.append("processAssets: closing: ");
        r6.append(r2);
        android.util.Log.d("WearableClient", r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0118, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x011b, code lost:
        throw r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x009b */
    /* renamed from: zzbly */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean call() {
        /*
            r7 = this;
            java.lang.String r0 = "processAssets: closing: "
            java.lang.String r1 = "WearableClient"
            r2 = 3
            boolean r3 = android.util.Log.isLoggable(r1, r2)
            if (r3 == 0) goto L_0x002f
            android.os.ParcelFileDescriptor r3 = r7.zzlvu
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r4 = java.lang.String.valueOf(r3)
            int r4 = r4.length()
            int r4 = r4 + 36
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "processAssets: writing data to FD : "
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            android.util.Log.d(r1, r3)
        L_0x002f:
            android.os.ParcelFileDescriptor$AutoCloseOutputStream r3 = new android.os.ParcelFileDescriptor$AutoCloseOutputStream
            android.os.ParcelFileDescriptor r4 = r7.zzlvu
            r3.<init>(r4)
            byte[] r4 = r7.zzlbv     // Catch:{ IOException -> 0x009b }
            r3.write(r4)     // Catch:{ IOException -> 0x009b }
            r3.flush()     // Catch:{ IOException -> 0x009b }
            boolean r4 = android.util.Log.isLoggable(r1, r2)     // Catch:{ IOException -> 0x009b }
            if (r4 == 0) goto L_0x0068
            android.os.ParcelFileDescriptor r4 = r7.zzlvu     // Catch:{ IOException -> 0x009b }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x009b }
            java.lang.String r5 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x009b }
            int r5 = r5.length()     // Catch:{ IOException -> 0x009b }
            int r5 = r5 + 27
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x009b }
            r6.<init>(r5)     // Catch:{ IOException -> 0x009b }
            java.lang.String r5 = "processAssets: wrote data: "
            r6.append(r5)     // Catch:{ IOException -> 0x009b }
            r6.append(r4)     // Catch:{ IOException -> 0x009b }
            java.lang.String r4 = r6.toString()     // Catch:{ IOException -> 0x009b }
            android.util.Log.d(r1, r4)     // Catch:{ IOException -> 0x009b }
        L_0x0068:
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ IOException -> 0x009b }
            boolean r2 = android.util.Log.isLoggable(r1, r2)     // Catch:{ IOException -> 0x0098 }
            if (r2 == 0) goto L_0x0095
            android.os.ParcelFileDescriptor r2 = r7.zzlvu     // Catch:{ IOException -> 0x0098 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x0098 }
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x0098 }
            int r5 = r5.length()     // Catch:{ IOException -> 0x0098 }
            int r5 = r5 + 24
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0098 }
            r6.<init>(r5)     // Catch:{ IOException -> 0x0098 }
            r6.append(r0)     // Catch:{ IOException -> 0x0098 }
            r6.append(r2)     // Catch:{ IOException -> 0x0098 }
            java.lang.String r0 = r6.toString()     // Catch:{ IOException -> 0x0098 }
            android.util.Log.d(r1, r0)     // Catch:{ IOException -> 0x0098 }
        L_0x0095:
            r3.close()     // Catch:{ IOException -> 0x0098 }
        L_0x0098:
            return r4
        L_0x0099:
            r4 = move-exception
            goto L_0x00f0
        L_0x009b:
            android.os.ParcelFileDescriptor r4 = r7.zzlvu     // Catch:{ all -> 0x0099 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0099 }
            java.lang.String r5 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0099 }
            int r5 = r5.length()     // Catch:{ all -> 0x0099 }
            int r5 = r5 + 36
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r6.<init>(r5)     // Catch:{ all -> 0x0099 }
            java.lang.String r5 = "processAssets: writing data failed: "
            r6.append(r5)     // Catch:{ all -> 0x0099 }
            r6.append(r4)     // Catch:{ all -> 0x0099 }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x0099 }
            android.util.Log.w(r1, r4)     // Catch:{ all -> 0x0099 }
            boolean r2 = android.util.Log.isLoggable(r1, r2)     // Catch:{ IOException -> 0x00ea }
            if (r2 == 0) goto L_0x00e7
            android.os.ParcelFileDescriptor r2 = r7.zzlvu     // Catch:{ IOException -> 0x00ea }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x00ea }
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x00ea }
            int r4 = r4.length()     // Catch:{ IOException -> 0x00ea }
            int r4 = r4 + 24
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ea }
            r5.<init>(r4)     // Catch:{ IOException -> 0x00ea }
            r5.append(r0)     // Catch:{ IOException -> 0x00ea }
            r5.append(r2)     // Catch:{ IOException -> 0x00ea }
            java.lang.String r0 = r5.toString()     // Catch:{ IOException -> 0x00ea }
            android.util.Log.d(r1, r0)     // Catch:{ IOException -> 0x00ea }
        L_0x00e7:
            r3.close()     // Catch:{ IOException -> 0x00ea }
        L_0x00ea:
            r0 = 0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L_0x00f0:
            boolean r2 = android.util.Log.isLoggable(r1, r2)     // Catch:{ IOException -> 0x011b }
            if (r2 == 0) goto L_0x0118
            android.os.ParcelFileDescriptor r2 = r7.zzlvu     // Catch:{ IOException -> 0x011b }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x011b }
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x011b }
            int r5 = r5.length()     // Catch:{ IOException -> 0x011b }
            int r5 = r5 + 24
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011b }
            r6.<init>(r5)     // Catch:{ IOException -> 0x011b }
            r6.append(r0)     // Catch:{ IOException -> 0x011b }
            r6.append(r2)     // Catch:{ IOException -> 0x011b }
            java.lang.String r0 = r6.toString()     // Catch:{ IOException -> 0x011b }
            android.util.Log.d(r1, r0)     // Catch:{ IOException -> 0x011b }
        L_0x0118:
            r3.close()     // Catch:{ IOException -> 0x011b }
        L_0x011b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wearable.internal.zzhh.call():java.lang.Boolean");
    }
}
