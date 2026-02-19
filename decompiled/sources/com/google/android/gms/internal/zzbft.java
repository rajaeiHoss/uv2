package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.gms.phenotype.PhenotypeFlag;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public final class zzbft implements ClearcutLogger.zza {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final PhenotypeFlag.Factory zzfqj = new PhenotypeFlag.Factory(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).withGservicePrefix("gms:playlog:service:sampling_").withPhenotypePrefix("LogSampling__");
    private static Map<String, PhenotypeFlag<String>> zzfqk = null;
    private static Boolean zzfql = null;
    private static Long zzfqm = null;
    private final Context zzaiq;

    public zzbft(Context context) {
        this.zzaiq = context;
        if (zzfqk == null) {
            zzfqk = new HashMap();
        }
        if (context != null) {
            PhenotypeFlag.maybeInit(context);
        }
    }

    private static boolean zzcc(Context context) {
        if (zzfql == null) {
            zzfql = Boolean.valueOf(zzbih.zzdd(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zzfql.booleanValue();
    }

    private static zzbfu zzge(String str) {
        String str2;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(44);
        int i = 0;
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        } else {
            str2 = "";
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            String valueOf = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf.length() != 0 ? "Failed to parse the rule: ".concat(valueOf) : new String("Failed to parse the rule: "));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzbfu(str2, parseLong, parseLong2);
            }
            StringBuilder sb = new StringBuilder(72);
            sb.append("negative values not supported: ");
            sb.append(parseLong);
            sb.append("/");
            sb.append(parseLong2);
            Log.e("LogSamplerImpl", sb.toString());
            return null;
        } catch (NumberFormatException e) {
            String valueOf2 = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf2.length() != 0 ? "parseLong() failed while parsing: ".concat(valueOf2) : new String("parseLong() failed while parsing: "), e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzg(java.lang.String r14, int r15) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L_0x000a
            boolean r1 = r14.isEmpty()
            if (r1 != 0) goto L_0x000a
            goto L_0x0012
        L_0x000a:
            if (r15 < 0) goto L_0x0011
            java.lang.String r14 = java.lang.String.valueOf(r15)
            goto L_0x0012
        L_0x0011:
            r14 = r0
        L_0x0012:
            r15 = 1
            if (r14 != 0) goto L_0x0016
            return r15
        L_0x0016:
            android.content.Context r1 = r13.zzaiq
            if (r1 == 0) goto L_0x003d
            boolean r1 = zzcc(r1)
            if (r1 != 0) goto L_0x0021
            goto L_0x003d
        L_0x0021:
            java.util.Map<java.lang.String, com.google.android.gms.phenotype.PhenotypeFlag<java.lang.String>> r1 = zzfqk
            java.lang.Object r1 = r1.get(r14)
            com.google.android.gms.phenotype.PhenotypeFlag r1 = (com.google.android.gms.phenotype.PhenotypeFlag) r1
            if (r1 != 0) goto L_0x0036
            com.google.android.gms.phenotype.PhenotypeFlag$Factory r1 = zzfqj
            com.google.android.gms.phenotype.PhenotypeFlag r1 = r1.createFlag(r14, r0)
            java.util.Map<java.lang.String, com.google.android.gms.phenotype.PhenotypeFlag<java.lang.String>> r0 = zzfqk
            r0.put(r14, r1)
        L_0x0036:
            java.lang.Object r14 = r1.get()
            r0 = r14
            java.lang.String r0 = (java.lang.String) r0
        L_0x003d:
            com.google.android.gms.internal.zzbfu r14 = zzge(r0)
            if (r14 != 0) goto L_0x0044
            return r15
        L_0x0044:
            java.lang.String r0 = r14.zzfqn
            android.content.Context r1 = r13.zzaiq
            java.lang.Long r2 = zzfqm
            r3 = 0
            if (r2 != 0) goto L_0x006e
            if (r1 == 0) goto L_0x006c
            boolean r2 = zzcc(r1)
            if (r2 == 0) goto L_0x0065
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "android_id"
            long r1 = com.google.android.gms.internal.zzdnm.getLong(r1, r2, r3)
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            goto L_0x0069
        L_0x0065:
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
        L_0x0069:
            zzfqm = r1
            goto L_0x006e
        L_0x006c:
            r1 = r3
            goto L_0x0074
        L_0x006e:
            java.lang.Long r1 = zzfqm
            long r1 = r1.longValue()
        L_0x0074:
            r5 = 8
            if (r0 == 0) goto L_0x009a
            boolean r6 = r0.isEmpty()
            if (r6 == 0) goto L_0x007f
            goto L_0x009a
        L_0x007f:
            java.nio.charset.Charset r6 = UTF_8
            byte[] r0 = r0.getBytes(r6)
            int r6 = r0.length
            int r6 = r6 + r5
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r6)
            r5.put(r0)
            r5.putLong(r1)
            byte[] r0 = r5.array()
            long r0 = com.google.android.gms.internal.zzbfo.zzi(r0)
            goto L_0x00aa
        L_0x009a:
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r5)
            java.nio.ByteBuffer r0 = r0.putLong(r1)
            byte[] r0 = r0.array()
            long r0 = com.google.android.gms.internal.zzbfo.zzi(r0)
        L_0x00aa:
            long r5 = r14.zzfqo
            long r7 = r14.zzfqp
            int r14 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r14 < 0) goto L_0x00d4
            int r14 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r14 < 0) goto L_0x00d4
            if (r14 <= 0) goto L_0x00d2
            int r14 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r14 < 0) goto L_0x00be
            long r0 = r0 % r7
            goto L_0x00cd
        L_0x00be:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            long r9 = r2 % r7
            r11 = 1
            long r9 = r9 + r11
            long r0 = r0 & r2
            long r0 = r0 % r7
            long r9 = r9 + r0
            long r0 = r9 % r7
        L_0x00cd:
            int r14 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r14 >= 0) goto L_0x00d2
            return r15
        L_0x00d2:
            r14 = 0
            return r14
        L_0x00d4:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            r15 = 72
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r15)
            java.lang.String r15 = "negative values not supported: "
            r0.append(r15)
            r0.append(r5)
            java.lang.String r15 = "/"
            r0.append(r15)
            r0.append(r7)
            java.lang.String r15 = r0.toString()
            r14.<init>(r15)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbft.zzg(java.lang.String, int):boolean");
    }
}
