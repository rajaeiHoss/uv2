package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Future;

public final class zzasc extends zzari {
    private volatile String zzdxk;
    private Future<String> zzeat;

    protected zzasc(zzark zzark) {
        super(zzark);
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0077 A[SYNTHETIC, Splitter:B:42:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0084 A[SYNTHETIC, Splitter:B:50:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0090 A[SYNTHETIC, Splitter:B:58:0x0090] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzbm(android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = "gaClientId"
            java.lang.String r1 = "Failed to close client id reading stream"
            java.lang.String r2 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.zzbq.zzgw(r2)
            r2 = 0
            java.io.FileInputStream r3 = r9.openFileInput(r0)     // Catch:{ FileNotFoundException -> 0x008d, IOException -> 0x006b, all -> 0x0069 }
            r4 = 36
            byte[] r5 = new byte[r4]     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            r6 = 0
            int r4 = r3.read(r5, r6, r4)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            int r7 = r3.available()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            if (r7 <= 0) goto L_0x0033
            java.lang.String r4 = "clientId file seems corrupted, deleting it."
            r8.zzed(r4)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            r3.close()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            r9.deleteFile(r0)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r9 = move-exception
            r8.zze(r1, r9)
        L_0x0032:
            return r2
        L_0x0033:
            r7 = 14
            if (r4 >= r7) goto L_0x004d
            java.lang.String r4 = "clientId file is empty, deleting it."
            r8.zzed(r4)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            r3.close()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            r9.deleteFile(r0)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            if (r3 == 0) goto L_0x004c
            r3.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r9 = move-exception
            r8.zze(r1, r9)
        L_0x004c:
            return r2
        L_0x004d:
            r3.close()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            java.lang.String r7 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            r7.<init>(r5, r6, r4)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            java.lang.String r4 = "Read client id from disk"
            r8.zza(r4, r7)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x0065 }
            if (r3 == 0) goto L_0x0064
            r3.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r9 = move-exception
            r8.zze(r1, r9)
        L_0x0064:
            return r7
        L_0x0065:
            r4 = move-exception
            goto L_0x006d
        L_0x0067:
            goto L_0x008e
        L_0x0069:
            r9 = move-exception
            goto L_0x0082
        L_0x006b:
            r4 = move-exception
            r3 = r2
        L_0x006d:
            java.lang.String r5 = "Error reading client id file, deleting it"
            r8.zze(r5, r4)     // Catch:{ all -> 0x0080 }
            r9.deleteFile(r0)     // Catch:{ all -> 0x0080 }
            if (r3 == 0) goto L_0x007f
            r3.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r9 = move-exception
            r8.zze(r1, r9)
        L_0x007f:
            return r2
        L_0x0080:
            r9 = move-exception
            r2 = r3
        L_0x0082:
            if (r2 == 0) goto L_0x008c
            r2.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r0 = move-exception
            r8.zze(r1, r0)
        L_0x008c:
            throw r9
        L_0x008d:
            r3 = r2
        L_0x008e:
            if (r3 == 0) goto L_0x0098
            r3.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0098
        L_0x0094:
            r9 = move-exception
            r8.zze(r1, r9)
        L_0x0098:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzasc.zzbm(android.content.Context):java.lang.String");
    }

    private final boolean zzo(Context context, String str) {
        zzbq.zzgv(str);
        zzbq.zzgw("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream == null) {
                return true;
            }
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                zze("Failed to close clientId writing stream", e);
                return true;
            }
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    zze("Failed to close clientId writing stream", e3);
                }
            }
            return false;
        } catch (IOException e4) {
            zze("Error writing to clientId file", e4);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    zze("Failed to close clientId writing stream", e5);
                }
            }
            return false;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final String zzzs() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            return !zzo(zzya().getContext(), lowerCase) ? "0" : lowerCase;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[Catch:{ InterruptedException -> 0x002f, ExecutionException -> 0x0024 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzzp() {
        /*
            r2 = this;
            r2.zzyk()
            monitor-enter(r2)
            java.lang.String r0 = r2.zzdxk     // Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x0017
            com.google.android.gms.analytics.zzk r0 = r2.zzya()     // Catch:{ all -> 0x004e }
            com.google.android.gms.internal.zzasd r1 = new com.google.android.gms.internal.zzasd     // Catch:{ all -> 0x004e }
            r1.<init>(r2)     // Catch:{ all -> 0x004e }
            java.util.concurrent.Future r0 = r0.zza(r1)     // Catch:{ all -> 0x004e }
            r2.zzeat = r0     // Catch:{ all -> 0x004e }
        L_0x0017:
            java.util.concurrent.Future<java.lang.String> r0 = r2.zzeat     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004a
            java.lang.Object r0 = r0.get()     // Catch:{ InterruptedException -> 0x002f, ExecutionException -> 0x0024 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ InterruptedException -> 0x002f, ExecutionException -> 0x0024 }
            r2.zzdxk = r0     // Catch:{ InterruptedException -> 0x002f, ExecutionException -> 0x0024 }
            goto L_0x0038
        L_0x0024:
            r0 = move-exception
            java.lang.String r1 = "Failed to load or generate client id"
            r2.zze(r1, r0)     // Catch:{ all -> 0x004e }
            java.lang.String r0 = "0"
        L_0x002c:
            r2.zzdxk = r0     // Catch:{ all -> 0x004e }
            goto L_0x0038
        L_0x002f:
            r0 = move-exception
            java.lang.String r1 = "ClientId loading or generation was interrupted"
            r2.zzd(r1, r0)     // Catch:{ all -> 0x004e }
            java.lang.String r0 = "0"
            goto L_0x002c
        L_0x0038:
            java.lang.String r0 = r2.zzdxk     // Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x0040
            java.lang.String r0 = "0"
            r2.zzdxk = r0     // Catch:{ all -> 0x004e }
        L_0x0040:
            java.lang.String r0 = "Loaded clientId"
            java.lang.String r1 = r2.zzdxk     // Catch:{ all -> 0x004e }
            r2.zza(r0, r1)     // Catch:{ all -> 0x004e }
            r0 = 0
            r2.zzeat = r0     // Catch:{ all -> 0x004e }
        L_0x004a:
            java.lang.String r0 = r2.zzdxk     // Catch:{ all -> 0x004e }
            monitor-exit(r2)     // Catch:{ all -> 0x004e }
            return r0
        L_0x004e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzasc.zzzp():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final String zzzq() {
        synchronized (this) {
            this.zzdxk = null;
            this.zzeat = zzya().zza(new zzase(this));
        }
        return zzzp();
    }

    /* access modifiers changed from: package-private */
    public final String zzzr() {
        String zzbm = zzbm(zzya().getContext());
        return zzbm == null ? zzzs() : zzbm;
    }
}
