package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzdkf;
import com.google.android.gms.internal.zzdkh;
import com.google.android.gms.internal.zzdkl;
import com.google.android.gms.internal.zzdkp;
import com.google.android.gms.internal.zzflr;
import com.google.android.gms.internal.zzfls;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

final class zzey implements zzah {
    private final Context mContext;
    private final ExecutorService zzimc = Executors.newSingleThreadExecutor();
    private final String zzknc;
    private zzdi<zzdkf> zzksb;

    zzey(Context context, String str) {
        this.mContext = context;
        this.zzknc = str;
    }

    private static zzdkl zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzdb.zzly(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            zzdj.zzby("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException unused2) {
            zzdj.zzcz("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private final File zzbhn() {
        String valueOf = String.valueOf(this.zzknc);
        return new File(this.mContext.getDir("google_tagmanager", 0), valueOf.length() != 0 ? "resource_".concat(valueOf) : new String("resource_"));
    }

    private static zzdkl zzz(byte[] bArr) {
        try {
            zzdkl zza = zzdkh.zza((zzbp) zzfls.zza(new zzbp(), bArr));
            if (zza != null) {
                zzdj.v("The container was successfully loaded from the resource (using binary file)");
            }
            return zza;
        } catch (zzflr unused) {
            zzdj.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzdkp unused2) {
            zzdj.zzcz("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    public final synchronized void release() {
        this.zzimc.shutdown();
    }

    public final void zza(zzdkf zzdkf) {
        this.zzimc.execute(new zzfa(this, zzdkf));
    }

    public final void zza(zzdi<zzdkf> zzdi) {
        this.zzksb = zzdi;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(zzdkf zzdkf) {
        File zzbhn = zzbhn();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzbhn);
            try {
                fileOutputStream.write(zzfls.zzc(zzdkf));
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException unused) {
                    zzdj.zzcz("error closing stream for writing resource to disk");
                    return true;
                }
            } catch (IOException unused2) {
                zzdj.zzcz("Error writing resource to disk. Removing resource from disk.");
                zzbhn.delete();
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                    zzdj.zzcz("error closing stream for writing resource to disk");
                }
                return false;
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    zzdj.zzcz("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException unused5) {
            zzdj.e("Error opening resource file for writing");
            return false;
        }
    }

    public final void zzbfv() {
        this.zzimc.execute(new zzez(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r4.zzksb.zzex(com.google.android.gms.tagmanager.zzda.zzkqo);
        com.google.android.gms.tagmanager.zzdj.zzcz("Failed to read the resource from disk. The resource is inconsistent");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r4.zzksb.zzex(com.google.android.gms.tagmanager.zzda.zzkqo);
        com.google.android.gms.tagmanager.zzdj.zzcz("Failed to read the resource from disk");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a1, code lost:
        com.google.android.gms.tagmanager.zzdj.zzcz("Error closing stream for reading resource from disk");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a4, code lost:
        throw r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:22:0x0077, B:26:0x0087] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0077 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0087 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzbhm() {
        /*
            r4 = this;
            java.lang.String r0 = "Error closing stream for reading resource from disk"
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzdkf> r1 = r4.zzksb
            if (r1 == 0) goto L_0x00b2
            r1.zzbfu()
            java.lang.String r1 = "Attempting to load resource from disk"
            com.google.android.gms.tagmanager.zzdj.v(r1)
            com.google.android.gms.tagmanager.zzei r1 = com.google.android.gms.tagmanager.zzei.zzbhh()
            com.google.android.gms.tagmanager.zzei$zza r1 = r1.zzbhi()
            com.google.android.gms.tagmanager.zzei$zza r2 = com.google.android.gms.tagmanager.zzei.zza.CONTAINER
            if (r1 == r2) goto L_0x0026
            com.google.android.gms.tagmanager.zzei r1 = com.google.android.gms.tagmanager.zzei.zzbhh()
            com.google.android.gms.tagmanager.zzei$zza r1 = r1.zzbhi()
            com.google.android.gms.tagmanager.zzei$zza r2 = com.google.android.gms.tagmanager.zzei.zza.CONTAINER_DEBUG
            if (r1 != r2) goto L_0x003e
        L_0x0026:
            java.lang.String r1 = r4.zzknc
            com.google.android.gms.tagmanager.zzei r2 = com.google.android.gms.tagmanager.zzei.zzbhh()
            java.lang.String r2 = r2.getContainerId()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x003e
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzdkf> r0 = r4.zzksb
            int r1 = com.google.android.gms.tagmanager.zzda.zzkqn
            r0.zzex(r1)
            return
        L_0x003e:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00a5 }
            java.io.File r2 = r4.zzbhn()     // Catch:{ FileNotFoundException -> 0x00a5 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00a5 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            r2.<init>()     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            com.google.android.gms.internal.zzdkh.zzb(r1, r2)     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            byte[] r2 = r2.toByteArray()     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            com.google.android.gms.internal.zzdkf r3 = new com.google.android.gms.internal.zzdkf     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            r3.<init>()     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            com.google.android.gms.internal.zzfls r2 = com.google.android.gms.internal.zzfls.zza(r3, r2)     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            com.google.android.gms.internal.zzdkf r2 = (com.google.android.gms.internal.zzdkf) r2     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            com.google.android.gms.internal.zzbp r3 = r2.zzyi     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            if (r3 != 0) goto L_0x006f
            com.google.android.gms.internal.zzbs r3 = r2.zzldm     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            if (r3 == 0) goto L_0x0067
            goto L_0x006f
        L_0x0067:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            java.lang.String r3 = "Resource and SupplementedResource are NULL."
            r2.<init>(r3)     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            throw r2     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
        L_0x006f:
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzdkf> r3 = r4.zzksb     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            r3.onSuccess(r2)     // Catch:{ IOException -> 0x0087, IllegalArgumentException -> 0x0077 }
            goto L_0x0083
        L_0x0075:
            r2 = move-exception
            goto L_0x009d
        L_0x0077:
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzdkf> r2 = r4.zzksb     // Catch:{ all -> 0x0075 }
            int r3 = com.google.android.gms.tagmanager.zzda.zzkqo     // Catch:{ all -> 0x0075 }
            r2.zzex(r3)     // Catch:{ all -> 0x0075 }
            java.lang.String r2 = "Failed to read the resource from disk. The resource is inconsistent"
            com.google.android.gms.tagmanager.zzdj.zzcz(r2)     // Catch:{ all -> 0x0075 }
        L_0x0083:
            r1.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0097
        L_0x0087:
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzdkf> r2 = r4.zzksb     // Catch:{ all -> 0x0075 }
            int r3 = com.google.android.gms.tagmanager.zzda.zzkqo     // Catch:{ all -> 0x0075 }
            r2.zzex(r3)     // Catch:{ all -> 0x0075 }
            java.lang.String r2 = "Failed to read the resource from disk"
            com.google.android.gms.tagmanager.zzdj.zzcz(r2)     // Catch:{ all -> 0x0075 }
            goto L_0x0083
        L_0x0094:
            com.google.android.gms.tagmanager.zzdj.zzcz(r0)
        L_0x0097:
            java.lang.String r0 = "The Disk resource was successfully read."
            com.google.android.gms.tagmanager.zzdj.v(r0)
            return
        L_0x009d:
            r1.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x00a4
        L_0x00a1:
            com.google.android.gms.tagmanager.zzdj.zzcz(r0)
        L_0x00a4:
            throw r2
        L_0x00a5:
            java.lang.String r0 = "Failed to find the resource in the disk"
            com.google.android.gms.tagmanager.zzdj.zzby(r0)
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzdkf> r0 = r4.zzksb
            int r1 = com.google.android.gms.tagmanager.zzda.zzkqn
            r0.zzex(r1)
            return
        L_0x00b2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Callback must be set before execute"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzey.zzbhm():void");
    }

    public final zzdkl zzey(int i) {
        String sb;
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            String resourceName = this.mContext.getResources().getResourceName(i);
            StringBuilder sb2 = new StringBuilder(String.valueOf(resourceName).length() + 66);
            sb2.append("Attempting to load a container from the resource ID ");
            sb2.append(i);
            sb2.append(" (");
            sb2.append(resourceName);
            sb2.append(")");
            zzdj.v(sb2.toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzdkh.zzb(openRawResource, byteArrayOutputStream);
                zzdkl zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzz(byteArrayOutputStream.toByteArray());
                }
                zzdj.v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException unused) {
                String resourceName2 = this.mContext.getResources().getResourceName(i);
                StringBuilder sb3 = new StringBuilder(String.valueOf(resourceName2).length() + 67);
                sb3.append("Error reading the default container with resource ID ");
                sb3.append(i);
                sb3.append(" (");
                sb3.append(resourceName2);
                sb3.append(")");
                sb = sb3.toString();
                zzdj.zzcz(sb);
                return null;
            }
        } catch (Resources.NotFoundException unused2) {
            StringBuilder sb4 = new StringBuilder(98);
            sb4.append("Failed to load the container. No default container resource found with the resource ID ");
            sb4.append(i);
            sb = sb4.toString();
            zzdj.zzcz(sb);
            return null;
        }
    }
}
