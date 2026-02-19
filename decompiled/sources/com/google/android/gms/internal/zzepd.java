package com.google.android.gms.internal;

import android.util.Base64;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.text.Typography;
import org.jivesoftware.smackx.EntityCapsManager;

public final class zzepd {
    private static final char[] zznrk = "0123456789abcdef".toCharArray();

    public static zzepa<Task<Void>, DatabaseReference.CompletionListener> zzb(DatabaseReference.CompletionListener completionListener) {
        if (completionListener != null) {
            return new zzepa<>(null, completionListener);
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        return new zzepa<>(taskCompletionSource.getTask(), new zzepe(taskCompletionSource));
    }

    public static void zzb(boolean z, String str) {
        if (!z) {
            String valueOf = String.valueOf(str);
            throw new AssertionError(valueOf.length() != 0 ? "hardAssert failed: ".concat(valueOf) : new String("hardAssert failed: "));
        }
    }

    public static void zzcw(boolean z) {
        zzb(z, "");
    }

    public static int zzi(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    public static String zzk(double d) {
        StringBuilder sb = new StringBuilder(16);
        long doubleToLongBits = Double.doubleToLongBits(d);
        for (int i = 7; i >= 0; i--) {
            int i2 = (int) ((doubleToLongBits >>> (i << 3)) & 255);
            char[] cArr = zznrk;
            sb.append(cArr[(i2 >> 4) & 15]);
            sb.append(cArr[i2 & 15]);
        }
        return sb.toString();
    }

    public static zzepb zzqj(String str) throws DatabaseException {
        try {
            int indexOf = str.indexOf("//");
            if (indexOf != -1) {
                int i = indexOf + 2;
                int indexOf2 = str.substring(i).indexOf("/");
                if (indexOf2 != -1) {
                    int i2 = indexOf2 + i;
                    String[] split = str.substring(i2).split("/");
                    StringBuilder sb = new StringBuilder();
                    for (int i3 = 0; i3 < split.length; i3++) {
                        if (!split[i3].equals("")) {
                            sb.append("/");
                            sb.append(URLEncoder.encode(split[i3], "UTF-8"));
                        }
                    }
                    String valueOf = String.valueOf(str.substring(0, i2));
                    String valueOf2 = String.valueOf(sb.toString());
                    str = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                }
                URI uri = new URI(str);
                String replace = uri.getPath().replace("+", " ");
                zzepf.zzqo(replace);
                zzegu zzegu = new zzegu(replace);
                String scheme = uri.getScheme();
                zzeia zzeia = new zzeia();
                zzeia.host = uri.getHost().toLowerCase();
                int port = uri.getPort();
                if (port != -1) {
                    zzeia.secure = scheme.equals("https");
                    String valueOf3 = String.valueOf(zzeia.host);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 12);
                    sb2.append(valueOf3);
                    sb2.append(":");
                    sb2.append(port);
                    zzeia.host = sb2.toString();
                } else {
                    zzeia.secure = true;
                }
                zzeia.zzkal = zzeia.host.split("\\.")[0].toLowerCase();
                zzeia.zznhd = zzeia.host;
                zzepb zzepb = new zzepb();
                zzepb.zzmxa = zzegu;
                zzepb.zzmwr = zzeia;
                return zzepb;
            }
            throw new URISyntaxException(str, "Invalid scheme specified");
        } catch (URISyntaxException e) {
            throw new DatabaseException("Invalid Firebase Database url specified", e);
        } catch (UnsupportedEncodingException e2) {
            throw new DatabaseException("Failed to URLEncode the path", e2);
        }
    }

    public static String zzqk(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(EntityCapsManager.HASH_METHOD_CAPS);
            instance.update(str.getBytes("UTF-8"));
            return Base64.encodeToString(instance.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Missing SHA-1 MessageDigest provider.", e);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 encoding is required for Firebase Database to run!");
        }
    }

    public static String zzql(String str) {
        String replace = str.indexOf(92) != -1 ? str.replace("\\", "\\\\") : str;
        if (str.indexOf(34) != -1) {
            replace = replace.replace("\"", "\\\"");
        }
        StringBuilder sb = new StringBuilder(String.valueOf(replace).length() + 2);
        sb.append(Typography.quote);
        sb.append(replace);
        sb.append(Typography.quote);
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        if (r2 > org.xbill.DNS.TTL.MAX_VALUE) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Integer zzqm(java.lang.String r8) {
        /*
            int r0 = r8.length()
            r1 = 0
            r2 = 11
            if (r0 > r2) goto L_0x005d
            int r0 = r8.length()
            if (r0 != 0) goto L_0x0010
            goto L_0x005d
        L_0x0010:
            r0 = 0
            char r2 = r8.charAt(r0)
            r3 = 45
            r4 = 1
            if (r2 != r3) goto L_0x0023
            int r0 = r8.length()
            if (r0 != r4) goto L_0x0021
            return r1
        L_0x0021:
            r0 = 1
            goto L_0x0024
        L_0x0023:
            r4 = 0
        L_0x0024:
            r2 = 0
        L_0x0026:
            int r5 = r8.length()
            if (r0 >= r5) goto L_0x0045
            char r5 = r8.charAt(r0)
            r6 = 48
            if (r5 < r6) goto L_0x0044
            r6 = 57
            if (r5 <= r6) goto L_0x0039
            goto L_0x0044
        L_0x0039:
            r6 = 10
            long r2 = r2 * r6
            int r5 = r5 + -48
            long r5 = (long) r5
            long r2 = r2 + r5
            int r0 = r0 + 1
            goto L_0x0026
        L_0x0044:
            return r1
        L_0x0045:
            if (r4 == 0) goto L_0x0056
            long r2 = -r2
            r4 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x0050
            return r1
        L_0x0050:
            int r8 = (int) r2
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            return r8
        L_0x0056:
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x0050
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzepd.zzqm(java.lang.String):java.lang.Integer");
    }

    public static int zzy(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
