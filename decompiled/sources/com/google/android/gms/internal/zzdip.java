package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzdip {
    private final Context mContext;
    private final ExecutorService zzimc;
    private final zzdiu zzlbq;

    public zzdip(Context context) {
        this(context, Executors.newSingleThreadExecutor(), new zzdiq(context));
    }

    private zzdip(Context context, ExecutorService executorService, zzdiu zzdiu) {
        this.mContext = context;
        this.zzimc = executorService;
        this.zzlbq = zzdiu;
    }

    private static byte[] zzg(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzp.zza(inputStream, byteArrayOutputStream, false);
            try {
                inputStream.close();
            } catch (IOException unused) {
                zzdal.zzcz("Error closing stream for reading resource from disk");
                return null;
            }
        } catch (IOException unused2) {
            zzdal.zzcz("Failed to read the resource from disk");
            try {
                inputStream.close();
            } catch (IOException unused3) {
                zzdal.zzcz("Error closing stream for reading resource from disk");
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
                throw th;
            } catch (IOException unused4) {
                zzdal.zzcz("Error closing stream for reading resource from disk");
                return null;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private final File zznc(String str) {
        return new File(this.mContext.getDir("google_tagmanager", 0), zznd(str));
    }

    private static String zznd(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? "resource_".concat(valueOf) : new String("resource_");
    }

    public final void zza(String str, zzdid zzdid) {
        this.zzimc.execute(new zzdir(this, str, zzdid));
    }

    public final void zza(String str, String str2, zzdid zzdid) {
        this.zzimc.execute(new zzdis(this, str, str2, zzdid));
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str, zzdid zzdid) {
        zzdal.v("Starting to load a saved resource file from Disk.");
        try {
            zzdid.zzaa(zzg(new FileInputStream(zznc(str))));
        } catch (FileNotFoundException unused) {
            String valueOf = String.valueOf(zznd(str));
            zzdal.e(valueOf.length() != 0 ? "Saved resource not found: ".concat(valueOf) : new String("Saved resource not found: "));
            zzdid.zzo(0, 1);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str, String str2, zzdid zzdid) {
        zzdal.v("Starting to load a default asset file from Disk.");
        if (str2 == null) {
            zzdal.v("Default asset file is not specified. Not proceeding with the loading");
        } else {
            try {
                InputStream open = this.zzlbq.open(str2);
                if (open != null) {
                    zzdid.zzaa(zzg(open));
                    return;
                } else {
                    zzdid.zzo(0, 2);
                    return;
                }
            } catch (IOException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 42 + String.valueOf(str2).length());
                sb.append("Default asset file not found. ");
                sb.append(str);
                sb.append(". Filename: ");
                sb.append(str2);
                zzdal.e(sb.toString());
            }
        }
        zzdid.zzo(0, 2);
    }

    public final void zzd(String str, byte[] bArr) {
        this.zzimc.execute(new zzdit(this, str, bArr));
    }

    /* access modifiers changed from: package-private */
    public final void zze(String str, byte[] bArr) {
        File zznc = zznc(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zznc);
            try {
                fileOutputStream.write(bArr);
                try {
                    fileOutputStream.close();
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 24);
                    sb.append("Resource ");
                    sb.append(str);
                    sb.append(" saved on Disk.");
                    zzdal.v(sb.toString());
                } catch (IOException unused) {
                    zzdal.e("Error closing stream for writing resource to disk");
                }
            } catch (IOException unused2) {
                zzdal.e("Error writing resource to disk. Removing resource from disk");
                zznc.delete();
                try {
                    fileOutputStream.close();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 24);
                    sb2.append("Resource ");
                    sb2.append(str);
                    sb2.append(" saved on Disk.");
                    zzdal.v(sb2.toString());
                } catch (IOException unused3) {
                    zzdal.e("Error closing stream for writing resource to disk");
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                    StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 24);
                    sb3.append("Resource ");
                    sb3.append(str);
                    sb3.append(" saved on Disk.");
                    zzdal.v(sb3.toString());
                } catch (IOException unused4) {
                    zzdal.e("Error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException unused5) {
            zzdal.e("Error opening resource file for writing");
        }
    }

    public final long zznb(String str) {
        File zznc = zznc(str);
        if (zznc.exists()) {
            return zznc.lastModified();
        }
        return 0;
    }
}
