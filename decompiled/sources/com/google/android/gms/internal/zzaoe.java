package com.google.android.gms.internal;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzabh
public final class zzaoe extends zzany {
    private static final Set<String> zzdox = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzdoy = new DecimalFormat("#,###");
    private File zzdoz;
    private boolean zzdpa;

    public zzaoe(zzann zzann) {
        super(zzann);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzahw.zzcz("Context.getCacheDir() returned null");
            return;
        }
        File file = new File(cacheDir, "admobVideoStreams");
        this.zzdoz = file;
        if (!file.isDirectory() && !this.zzdoz.mkdirs()) {
            String valueOf = String.valueOf(this.zzdoz.getAbsolutePath());
            zzahw.zzcz(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzdoz = null;
        } else if (!this.zzdoz.setReadable(true, false) || !this.zzdoz.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzdoz.getAbsolutePath());
            zzahw.zzcz(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzdoz = null;
        }
    }

    private final File zzc(File file) {
        return new File(this.zzdoz, String.valueOf(file.getName()).concat(".done"));
    }

    public final void abort() {
        this.zzdpa = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0233, code lost:
        zza(r9, r12.getAbsolutePath(), "sizeExceeded", r0);
        r1.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x023f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r4 = new java.lang.StringBuilder((java.lang.String.valueOf(r3).length() + 20) + java.lang.String.valueOf(r30).length());
        r4.append("Caching ");
        r4.append(r3);
        r4.append(" bytes from ");
        r4.append(r9);
        com.google.android.gms.internal.zzahw.zzby(r4.toString());
        r5 = java.nio.channels.Channels.newChannel(r2.getInputStream());
        r4 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r3 = r4.getChannel();
        r2 = java.nio.ByteBuffer.allocate(1048576);
        r16 = com.google.android.gms.ads.internal.zzbt.zzes();
        r17 = r16.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x029a, code lost:
        r10 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r1 = new com.google.android.gms.internal.zzake(((java.lang.Long) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzbly)).longValue());
        r13 = ((java.lang.Long) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzblx)).longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02b4, code lost:
        r19 = r5.read(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02b8, code lost:
        if (r19 < 0) goto L_0x03d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02ba, code lost:
        r11 = r11 + r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02bc, code lost:
        if (r11 <= r6) goto L_0x02f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02be, code lost:
        r15 = "sizeExceeded";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:?, code lost:
        r1 = java.lang.String.valueOf(java.lang.Integer.toString(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02ce, code lost:
        if (r1.length() == 0) goto L_0x02d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02d0, code lost:
        r1 = "File too big for full file cache. Size: ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02d6, code lost:
        r1 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02e2, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02e3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02e6, code lost:
        r2 = r0;
        r0 = r1;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02ea, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02ed, code lost:
        r2 = r0;
        r1 = r10;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02f0, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        r2.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02fa, code lost:
        if (r3.write(r2) > 0) goto L_0x02f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02fc, code lost:
        r2.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x030b, code lost:
        if ((r16.currentTimeMillis() - r17) > (1000 * r13)) goto L_0x0383;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x030d, code lost:
        r19 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0311, code lost:
        if (r8.zzdpa != false) goto L_0x0373;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0317, code lost:
        if (r1.tryAcquire() == false) goto L_0x034f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0319, code lost:
        r20 = r12.getAbsolutePath();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x031f, code lost:
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0323, code lost:
        r23 = r1;
        r1 = r1;
        r24 = r10;
        r10 = com.google.android.gms.internal.zzako.zzaju;
        r25 = r3;
        r26 = r4;
        r4 = r20;
        r20 = r5;
        r27 = r6;
        r28 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:?, code lost:
        r1 = new com.google.android.gms.internal.zzanz(r29, r30, r4, r11, r7, false);
        r10.post(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0346, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0348, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0349, code lost:
        r26 = r4;
        r2 = r0;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x034f, code lost:
        r23 = r1;
        r25 = r3;
        r26 = r4;
        r20 = r5;
        r27 = r6;
        r28 = r7;
        r24 = r10;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x035f, code lost:
        r2 = r19;
        r5 = r20;
        r15 = r21;
        r1 = r23;
        r10 = r24;
        r3 = r25;
        r4 = r26;
        r6 = r27;
        r7 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0373, code lost:
        r26 = r4;
        r24 = r10;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0382, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0383, code lost:
        r26 = r4;
        r24 = r10;
        r21 = r15;
        r15 = "downloadTimeout";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:?, code lost:
        r0 = java.lang.Long.toString(r13);
        r2 = new java.lang.StringBuilder(java.lang.String.valueOf(r0).length() + 29);
        r2.append("Timeout exceeded. Limit: ");
        r2.append(r0);
        r2.append(" sec");
        r10 = r2.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03b6, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03b7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03ba, code lost:
        r2 = r0;
        r0 = r10;
        r1 = r24;
        r10 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03c2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03c5, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03c7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03ca, code lost:
        r26 = r4;
        r21 = r15;
        r2 = r0;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03d2, code lost:
        r26 = r4;
        r24 = r10;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:?, code lost:
        r26.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03e0, code lost:
        if (com.google.android.gms.internal.zzahw.zzae(3) == false) goto L_0x0422;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:?, code lost:
        r1 = zzdoy.format((long) r11);
        r3 = new java.lang.StringBuilder((java.lang.String.valueOf(r1).length() + 22) + java.lang.String.valueOf(r30).length());
        r3.append("Preloaded ");
        r3.append(r1);
        r3.append(" bytes from ");
        r3.append(r9);
        com.google.android.gms.internal.zzahw.zzby(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0419, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x041b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:?, code lost:
        r12.setReadable(true, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x042b, code lost:
        if (r0.isFile() == false) goto L_0x0435;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
        r0.setLastModified(java.lang.System.currentTimeMillis());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:?, code lost:
        r0.createNewFile();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        zza(r9, r12.getAbsolutePath(), r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0441, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:?, code lost:
        zzdox.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0446, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0448, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x044c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x044f, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0451, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0452, code lost:
        r15 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0455, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0458, code lost:
        r26 = r4;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x045c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x045f, code lost:
        r26 = r4;
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0462, code lost:
        r21 = r15;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0469, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x046c, code lost:
        r1 = r14;
        r21 = r15;
        r2 = r0;
        r0 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0476, code lost:
        com.google.android.gms.ads.internal.zzbt.zzep().zza(r2, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0488, code lost:
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r30).length() + 26);
        r3.append("Preload aborted for URL \"");
        r3.append(r9);
        r3.append("\"");
        com.google.android.gms.internal.zzahw.zzcy(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x04ac, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r30).length() + 25);
        r4.append("Preload failed for URL \"");
        r4.append(r9);
        r4.append("\"");
        com.google.android.gms.internal.zzahw.zzc(r4.toString(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x04eb, code lost:
        r2 = "Could not delete partial cache file at ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x04f0, code lost:
        r2 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012b, code lost:
        r15 = androidx.mediarouter.media.MediaRouteProviderProtocol.SERVICE_DATA_ERROR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        com.google.android.gms.ads.internal.zzbt.zzex();
        r2 = com.google.android.gms.internal.zzame.zzb(r9, ((java.lang.Integer) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzblz)).intValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0146, code lost:
        if ((r2 instanceof java.net.HttpURLConnection) == false) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r3 = r2.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0151, code lost:
        if (r3 < 400) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0153, code lost:
        r15 = "badUrl";
        r1 = java.lang.String.valueOf(java.lang.Integer.toString(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0163, code lost:
        if (r1.length() == 0) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0165, code lost:
        r1 = "HTTP request failed. Code: ".concat(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x016b, code lost:
        r1 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r30).length() + 32);
        r4.append("HTTP status code ");
        r4.append(r3);
        r4.append(" at ");
        r4.append(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0198, code lost:
        throw new java.io.IOException(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0199, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x019c, code lost:
        r2 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x019f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01a2, code lost:
        r2 = r0;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01a4, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        r7 = r2.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01ab, code lost:
        if (r7 >= 0) goto L_0x01d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r2 = java.lang.String.valueOf(r30);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b7, code lost:
        if (r2.length() == 0) goto L_0x01be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b9, code lost:
        r0 = "Stream cache aborted, missing content-length header at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01be, code lost:
        r0 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c4, code lost:
        com.google.android.gms.internal.zzahw.zzcz(r0);
        zza(r9, r12.getAbsolutePath(), "contentLengthMissing", (java.lang.String) null);
        r1.remove(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01d3, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r3 = zzdoy.format((long) r7);
        r6 = ((java.lang.Integer) com.google.android.gms.internal.zzlc.zzio().zzd(com.google.android.gms.internal.zzoi.zzblv)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01eb, code lost:
        if (r7 <= r6) goto L_0x0240;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        r2 = new java.lang.StringBuilder((java.lang.String.valueOf(r3).length() + 33) + java.lang.String.valueOf(r30).length());
        r2.append("Content length ");
        r2.append(r3);
        r2.append(" exceeds limit at ");
        r2.append(r9);
        com.google.android.gms.internal.zzahw.zzcz(r2.toString());
        r2 = java.lang.String.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0226, code lost:
        if (r2.length() == 0) goto L_0x022d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0228, code lost:
        r0 = "File too big for full file cache. Size: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x022d, code lost:
        r0 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:184:0x0438 */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0419 A[ExcHandler: RuntimeException (e java.lang.RuntimeException), PHI: r21 r24 r26 
      PHI: (r21v6 java.lang.String) = (r21v2 java.lang.String), (r21v2 java.lang.String), (r21v2 java.lang.String), (r21v2 java.lang.String), (r21v2 java.lang.String), (r21v2 java.lang.String), (r21v14 java.lang.String), (r21v14 java.lang.String) binds: [B:182:0x0435, B:183:?, B:180:0x042d, B:181:?, B:170:0x03e2, B:171:?, B:139:0x0321, B:142:0x033f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r24v5 java.lang.String) = (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v1 java.lang.String), (r24v9 java.lang.String), (r24v11 java.lang.String) binds: [B:182:0x0435, B:183:?, B:180:0x042d, B:181:?, B:170:0x03e2, B:171:?, B:139:0x0321, B:142:0x033f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r26v10 java.io.FileOutputStream) = (r26v5 java.io.FileOutputStream), (r26v5 java.io.FileOutputStream), (r26v5 java.io.FileOutputStream), (r26v5 java.io.FileOutputStream), (r26v5 java.io.FileOutputStream), (r26v5 java.io.FileOutputStream), (r26v15 java.io.FileOutputStream), (r26v18 java.io.FileOutputStream) binds: [B:182:0x0435, B:183:?, B:180:0x042d, B:181:?, B:170:0x03e2, B:171:?, B:139:0x0321, B:142:0x033f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:142:0x033f] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0476  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0488  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04ac  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x04eb  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x04f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdc(java.lang.String r30) {
        /*
            r29 = this;
            r8 = r29
            r9 = r30
            java.io.File r0 = r8.zzdoz
            r10 = 0
            r11 = 0
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "noCacheDir"
        L_0x000c:
            r8.zza(r9, r10, r0, r10)
            return r11
        L_0x0010:
            java.io.File r0 = r8.zzdoz
            if (r0 != 0) goto L_0x0016
            r3 = 0
            goto L_0x0032
        L_0x0016:
            java.io.File[] r0 = r0.listFiles()
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x001d:
            if (r2 >= r1) goto L_0x0032
            r4 = r0[r2]
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = ".done"
            boolean r4 = r4.endsWith(r5)
            if (r4 != 0) goto L_0x002f
            int r3 = r3 + 1
        L_0x002f:
            int r2 = r2 + 1
            goto L_0x001d
        L_0x0032:
            com.google.android.gms.internal.zzny<java.lang.Integer> r0 = com.google.android.gms.internal.zzoi.zzblu
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r3 <= r0) goto L_0x0093
            java.io.File r0 = r8.zzdoz
            if (r0 != 0) goto L_0x004a
        L_0x0048:
            r0 = 0
            goto L_0x0088
        L_0x004a:
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.io.File[] r0 = r0.listFiles()
            int r3 = r0.length
            r5 = r10
            r4 = 0
        L_0x0056:
            if (r4 >= r3) goto L_0x0073
            r6 = r0[r4]
            java.lang.String r7 = r6.getName()
            java.lang.String r12 = ".done"
            boolean r7 = r7.endsWith(r12)
            if (r7 != 0) goto L_0x0070
            long r12 = r6.lastModified()
            int r7 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r7 >= 0) goto L_0x0070
            r5 = r6
            r1 = r12
        L_0x0070:
            int r4 = r4 + 1
            goto L_0x0056
        L_0x0073:
            if (r5 == 0) goto L_0x0048
            boolean r0 = r5.delete()
            java.io.File r1 = r8.zzc(r5)
            boolean r2 = r1.isFile()
            if (r2 == 0) goto L_0x0088
            boolean r1 = r1.delete()
            r0 = r0 & r1
        L_0x0088:
            if (r0 != 0) goto L_0x0010
            java.lang.String r0 = "Unable to expire stream cache"
            com.google.android.gms.internal.zzahw.zzcz(r0)
            java.lang.String r0 = "expireFailed"
            goto L_0x000c
        L_0x0093:
            com.google.android.gms.internal.zzlc.zzij()
            java.lang.String r0 = com.google.android.gms.internal.zzako.zzcu(r30)
            java.io.File r12 = new java.io.File
            java.io.File r1 = r8.zzdoz
            r12.<init>(r1, r0)
            java.io.File r0 = r8.zzc(r12)
            boolean r1 = r12.isFile()
            r13 = 1
            if (r1 == 0) goto L_0x00d9
            boolean r1 = r0.isFile()
            if (r1 == 0) goto L_0x00d9
            long r0 = r12.length()
            int r1 = (int) r0
            java.lang.String r0 = "Stream cache hit at "
            java.lang.String r2 = java.lang.String.valueOf(r30)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00c8
            java.lang.String r0 = r0.concat(r2)
            goto L_0x00ce
        L_0x00c8:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r0)
            r0 = r2
        L_0x00ce:
            com.google.android.gms.internal.zzahw.zzby(r0)
            java.lang.String r0 = r12.getAbsolutePath()
            r8.zza((java.lang.String) r9, (java.lang.String) r0, (int) r1)
            return r13
        L_0x00d9:
            java.io.File r1 = r8.zzdoz
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r30)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00f3
            java.lang.String r1 = r1.concat(r2)
            r14 = r1
            goto L_0x00f9
        L_0x00f3:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r14 = r2
        L_0x00f9:
            java.util.Set<java.lang.String> r1 = zzdox
            monitor-enter(r1)
            boolean r2 = r1.contains(r14)     // Catch:{ all -> 0x0507 }
            if (r2 == 0) goto L_0x0127
            java.lang.String r0 = "Stream cache already in progress at "
            java.lang.String r2 = java.lang.String.valueOf(r30)     // Catch:{ all -> 0x0507 }
            int r3 = r2.length()     // Catch:{ all -> 0x0507 }
            if (r3 == 0) goto L_0x0113
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x0507 }
            goto L_0x0119
        L_0x0113:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0507 }
            r2.<init>(r0)     // Catch:{ all -> 0x0507 }
            r0 = r2
        L_0x0119:
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ all -> 0x0507 }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ all -> 0x0507 }
            java.lang.String r2 = "inProgress"
            r8.zza(r9, r0, r2, r10)     // Catch:{ all -> 0x0507 }
            monitor-exit(r1)     // Catch:{ all -> 0x0507 }
            return r11
        L_0x0127:
            r1.add(r14)     // Catch:{ all -> 0x0507 }
            monitor-exit(r1)     // Catch:{ all -> 0x0507 }
            java.lang.String r15 = "error"
            com.google.android.gms.ads.internal.zzbt.zzex()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            com.google.android.gms.internal.zzny<java.lang.Integer> r2 = com.google.android.gms.internal.zzoi.zzblz     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            com.google.android.gms.internal.zzog r3 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            int r2 = r2.intValue()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.net.HttpURLConnection r2 = com.google.android.gms.internal.zzame.zzb(r9, r2)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            boolean r3 = r2 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            if (r3 == 0) goto L_0x01a7
            r3 = r2
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r3 = r3.getResponseCode()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r4 = 400(0x190, float:5.6E-43)
            if (r3 < r4) goto L_0x01a7
            java.lang.String r15 = "badUrl"
            java.lang.String r0 = "HTTP request failed. Code: "
            java.lang.String r1 = java.lang.Integer.toString(r3)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r2 = r1.length()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            if (r2 == 0) goto L_0x016b
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r1 = r0
            goto L_0x0170
        L_0x016b:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
        L_0x0170:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            java.lang.String r2 = java.lang.String.valueOf(r30)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            int r2 = r2 + 32
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            java.lang.String r2 = "HTTP status code "
            r4.append(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            r4.append(r3)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            java.lang.String r2 = " at "
            r4.append(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            r4.append(r9)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            java.lang.String r2 = r4.toString()     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
            throw r0     // Catch:{ IOException -> 0x019b, RuntimeException -> 0x0199 }
        L_0x0199:
            r0 = move-exception
            goto L_0x019c
        L_0x019b:
            r0 = move-exception
        L_0x019c:
            r2 = r0
            r0 = r1
            goto L_0x01a4
        L_0x019f:
            r0 = move-exception
            goto L_0x01a2
        L_0x01a1:
            r0 = move-exception
        L_0x01a2:
            r2 = r0
            r0 = r10
        L_0x01a4:
            r1 = r14
            goto L_0x0472
        L_0x01a7:
            int r7 = r2.getContentLength()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            if (r7 >= 0) goto L_0x01d4
            java.lang.String r0 = "Stream cache aborted, missing content-length header at "
            java.lang.String r2 = java.lang.String.valueOf(r30)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r3 = r2.length()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            if (r3 == 0) goto L_0x01be
            java.lang.String r0 = r0.concat(r2)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            goto L_0x01c4
        L_0x01be:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r0 = r2
        L_0x01c4:
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r2 = "contentLengthMissing"
            r8.zza(r9, r0, r2, r10)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r1.remove(r14)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            return r11
        L_0x01d4:
            java.text.DecimalFormat r3 = zzdoy     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            long r4 = (long) r7     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.String r3 = r3.format(r4)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            com.google.android.gms.internal.zzny<java.lang.Integer> r4 = com.google.android.gms.internal.zzoi.zzblv     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            com.google.android.gms.internal.zzog r5 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.Object r4 = r5.zzd(r4)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            int r6 = r4.intValue()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            if (r7 <= r6) goto L_0x0240
            java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r0 = r0.length()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r0 = r0 + 33
            java.lang.String r2 = java.lang.String.valueOf(r30)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r2 = r2.length()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r0 = r0 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r0 = "Content length "
            r2.append(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r2.append(r3)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r0 = " exceeds limit at "
            r2.append(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r2.append(r9)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r0 = r2.toString()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r0 = "File too big for full file cache. Size: "
            java.lang.String r2 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            int r3 = r2.length()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            if (r3 == 0) goto L_0x022d
            java.lang.String r0 = r0.concat(r2)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            goto L_0x0233
        L_0x022d:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r2.<init>(r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r0 = r2
        L_0x0233:
            java.lang.String r2 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            java.lang.String r3 = "sizeExceeded"
            r8.zza(r9, r2, r3, r0)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            r1.remove(r14)     // Catch:{ IOException -> 0x01a1, RuntimeException -> 0x019f }
            return r11
        L_0x0240:
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            int r1 = r1.length()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            int r1 = r1 + 20
            java.lang.String r4 = java.lang.String.valueOf(r30)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            int r4 = r4.length()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            int r1 = r1 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            r4.<init>(r1)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.String r1 = "Caching "
            r4.append(r1)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            r4.append(r3)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.String r1 = " bytes from "
            r4.append(r1)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            r4.append(r9)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            com.google.android.gms.internal.zzahw.zzby(r1)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.io.InputStream r1 = r2.getInputStream()     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.nio.channels.ReadableByteChannel r5 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            r4.<init>(r12)     // Catch:{ IOException -> 0x046b, RuntimeException -> 0x0469 }
            java.nio.channels.FileChannel r3 = r4.getChannel()     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            r1 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            com.google.android.gms.common.util.zze r16 = com.google.android.gms.ads.internal.zzbt.zzes()     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            long r17 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            com.google.android.gms.internal.zzny<java.lang.Long> r1 = com.google.android.gms.internal.zzoi.zzbly     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            com.google.android.gms.internal.zzog r10 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            java.lang.Object r1 = r10.zzd(r1)     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ IOException -> 0x045e, RuntimeException -> 0x045c }
            r10 = r14
            long r13 = r1.longValue()     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            com.google.android.gms.internal.zzake r1 = new com.google.android.gms.internal.zzake     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            r1.<init>(r13)     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            com.google.android.gms.internal.zzny<java.lang.Long> r13 = com.google.android.gms.internal.zzoi.zzblx     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            com.google.android.gms.internal.zzog r14 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            java.lang.Object r13 = r14.zzd(r13)     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            long r13 = r13.longValue()     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
        L_0x02b4:
            int r19 = r5.read(r2)     // Catch:{ IOException -> 0x0457, RuntimeException -> 0x0455 }
            if (r19 < 0) goto L_0x03d2
            int r11 = r11 + r19
            if (r11 <= r6) goto L_0x02f3
            java.lang.String r15 = "sizeExceeded"
            java.lang.String r0 = "File too big for full file cache. Size: "
            java.lang.String r1 = java.lang.Integer.toString(r11)     // Catch:{ IOException -> 0x02ec, RuntimeException -> 0x02ea }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x02ec, RuntimeException -> 0x02ea }
            int r2 = r1.length()     // Catch:{ IOException -> 0x02ec, RuntimeException -> 0x02ea }
            if (r2 == 0) goto L_0x02d6
            java.lang.String r0 = r0.concat(r1)     // Catch:{ IOException -> 0x02ec, RuntimeException -> 0x02ea }
            r1 = r0
            goto L_0x02db
        L_0x02d6:
            java.lang.String r1 = new java.lang.String     // Catch:{ IOException -> 0x02ec, RuntimeException -> 0x02ea }
            r1.<init>(r0)     // Catch:{ IOException -> 0x02ec, RuntimeException -> 0x02ea }
        L_0x02db:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x02e5, RuntimeException -> 0x02e3 }
            java.lang.String r2 = "stream cache file size limit exceeded"
            r0.<init>(r2)     // Catch:{ IOException -> 0x02e5, RuntimeException -> 0x02e3 }
            throw r0     // Catch:{ IOException -> 0x02e5, RuntimeException -> 0x02e3 }
        L_0x02e3:
            r0 = move-exception
            goto L_0x02e6
        L_0x02e5:
            r0 = move-exception
        L_0x02e6:
            r2 = r0
            r0 = r1
            r1 = r10
            goto L_0x02f0
        L_0x02ea:
            r0 = move-exception
            goto L_0x02ed
        L_0x02ec:
            r0 = move-exception
        L_0x02ed:
            r2 = r0
            r1 = r10
            r0 = 0
        L_0x02f0:
            r10 = r4
            goto L_0x0472
        L_0x02f3:
            r2.flip()     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
        L_0x02f6:
            int r19 = r3.write(r2)     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            if (r19 > 0) goto L_0x02f6
            r2.clear()     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            long r19 = r16.currentTimeMillis()     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            long r19 = r19 - r17
            r21 = 1000(0x3e8, double:4.94E-321)
            long r21 = r21 * r13
            int r23 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
            if (r23 > 0) goto L_0x0383
            r19 = r2
            boolean r2 = r8.zzdpa     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            if (r2 != 0) goto L_0x0373
            boolean r2 = r1.tryAcquire()     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            if (r2 == 0) goto L_0x034f
            java.lang.String r20 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            android.os.Handler r2 = com.google.android.gms.internal.zzako.zzaju     // Catch:{ IOException -> 0x03c9, RuntimeException -> 0x03c7 }
            r21 = r15
            com.google.android.gms.internal.zzanz r15 = new com.google.android.gms.internal.zzanz     // Catch:{ IOException -> 0x0348, RuntimeException -> 0x0346 }
            r22 = 0
            r23 = r1
            r1 = r15
            r24 = r10
            r10 = r2
            r2 = r29
            r25 = r3
            r3 = r30
            r26 = r4
            r4 = r20
            r20 = r5
            r5 = r11
            r27 = r6
            r6 = r7
            r28 = r7
            r7 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            r10.post(r15)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            goto L_0x035f
        L_0x0346:
            r0 = move-exception
            goto L_0x0349
        L_0x0348:
            r0 = move-exception
        L_0x0349:
            r26 = r4
            r2 = r0
            r1 = r10
            goto L_0x0452
        L_0x034f:
            r23 = r1
            r25 = r3
            r26 = r4
            r20 = r5
            r27 = r6
            r28 = r7
            r24 = r10
            r21 = r15
        L_0x035f:
            r2 = r19
            r5 = r20
            r15 = r21
            r1 = r23
            r10 = r24
            r3 = r25
            r4 = r26
            r6 = r27
            r7 = r28
            goto L_0x02b4
        L_0x0373:
            r26 = r4
            r24 = r10
            r21 = r15
            java.lang.String r15 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            java.lang.String r1 = "abort requested"
            r0.<init>(r1)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            throw r0     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
        L_0x0383:
            r26 = r4
            r24 = r10
            r21 = r15
            java.lang.String r15 = "downloadTimeout"
            java.lang.String r0 = java.lang.Long.toString(r13)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            int r1 = r1.length()     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            int r1 = r1 + 29
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            java.lang.String r1 = "Timeout exceeded. Limit: "
            r2.append(r1)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            r2.append(r0)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            java.lang.String r0 = " sec"
            r2.append(r0)     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            java.lang.String r10 = r2.toString()     // Catch:{ IOException -> 0x03c4, RuntimeException -> 0x03c2 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x03b9, RuntimeException -> 0x03b7 }
            java.lang.String r1 = "stream cache time limit exceeded"
            r0.<init>(r1)     // Catch:{ IOException -> 0x03b9, RuntimeException -> 0x03b7 }
            throw r0     // Catch:{ IOException -> 0x03b9, RuntimeException -> 0x03b7 }
        L_0x03b7:
            r0 = move-exception
            goto L_0x03ba
        L_0x03b9:
            r0 = move-exception
        L_0x03ba:
            r2 = r0
            r0 = r10
            r1 = r24
            r10 = r26
            goto L_0x0472
        L_0x03c2:
            r0 = move-exception
            goto L_0x03c5
        L_0x03c4:
            r0 = move-exception
        L_0x03c5:
            r2 = r0
            goto L_0x041f
        L_0x03c7:
            r0 = move-exception
            goto L_0x03ca
        L_0x03c9:
            r0 = move-exception
        L_0x03ca:
            r26 = r4
            r21 = r15
            r2 = r0
            r1 = r10
            goto L_0x0465
        L_0x03d2:
            r26 = r4
            r24 = r10
            r21 = r15
            r26.close()     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            r1 = 3
            boolean r1 = com.google.android.gms.internal.zzahw.zzae(r1)     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            if (r1 == 0) goto L_0x0422
            java.text.DecimalFormat r1 = zzdoy     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            long r2 = (long) r11     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            java.lang.String r1 = r1.format(r2)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            int r2 = r2 + 22
            java.lang.String r3 = java.lang.String.valueOf(r30)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            java.lang.String r2 = "Preloaded "
            r3.append(r2)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            r3.append(r1)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            java.lang.String r1 = " bytes from "
            r3.append(r1)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            r3.append(r9)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            com.google.android.gms.internal.zzahw.zzby(r1)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            goto L_0x0422
        L_0x0419:
            r0 = move-exception
            goto L_0x041c
        L_0x041b:
            r0 = move-exception
        L_0x041c:
            r2 = r0
            r15 = r21
        L_0x041f:
            r1 = r24
            goto L_0x0465
        L_0x0422:
            r1 = 1
            r2 = 0
            r12.setReadable(r1, r2)     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            boolean r1 = r0.isFile()     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            if (r1 == 0) goto L_0x0435
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            r0.setLastModified(r1)     // Catch:{ IOException -> 0x041b, RuntimeException -> 0x0419 }
            goto L_0x0438
        L_0x0435:
            r0.createNewFile()     // Catch:{ IOException -> 0x0438, RuntimeException -> 0x0419 }
        L_0x0438:
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            r8.zza((java.lang.String) r9, (java.lang.String) r0, (int) r11)     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            java.util.Set<java.lang.String> r0 = zzdox     // Catch:{ IOException -> 0x044e, RuntimeException -> 0x044c }
            r1 = r24
            r0.remove(r1)     // Catch:{ IOException -> 0x044a, RuntimeException -> 0x0448 }
            r0 = 1
            return r0
        L_0x0448:
            r0 = move-exception
            goto L_0x0451
        L_0x044a:
            r0 = move-exception
            goto L_0x0451
        L_0x044c:
            r0 = move-exception
            goto L_0x044f
        L_0x044e:
            r0 = move-exception
        L_0x044f:
            r1 = r24
        L_0x0451:
            r2 = r0
        L_0x0452:
            r15 = r21
            goto L_0x0465
        L_0x0455:
            r0 = move-exception
            goto L_0x0458
        L_0x0457:
            r0 = move-exception
        L_0x0458:
            r26 = r4
            r1 = r10
            goto L_0x0462
        L_0x045c:
            r0 = move-exception
            goto L_0x045f
        L_0x045e:
            r0 = move-exception
        L_0x045f:
            r26 = r4
            r1 = r14
        L_0x0462:
            r21 = r15
            r2 = r0
        L_0x0465:
            r10 = r26
            r0 = 0
            goto L_0x0472
        L_0x0469:
            r0 = move-exception
            goto L_0x046c
        L_0x046b:
            r0 = move-exception
        L_0x046c:
            r1 = r14
            r21 = r15
            r2 = r0
            r0 = 0
            r10 = 0
        L_0x0472:
            boolean r3 = r2 instanceof java.lang.RuntimeException
            if (r3 == 0) goto L_0x047f
            com.google.android.gms.internal.zzahi r3 = com.google.android.gms.ads.internal.zzbt.zzep()
            java.lang.String r4 = "VideoStreamFullFileCache.preload"
            r3.zza(r2, r4)
        L_0x047f:
            r10.close()     // Catch:{ IOException | NullPointerException -> 0x0483 }
            goto L_0x0484
        L_0x0483:
        L_0x0484:
            boolean r3 = r8.zzdpa
            if (r3 == 0) goto L_0x04ac
            java.lang.String r2 = java.lang.String.valueOf(r30)
            int r2 = r2.length()
            int r2 = r2 + 26
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Preload aborted for URL \""
            r3.append(r2)
            r3.append(r9)
            java.lang.String r2 = "\""
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.google.android.gms.internal.zzahw.zzcy(r2)
            goto L_0x04cf
        L_0x04ac:
            java.lang.String r3 = java.lang.String.valueOf(r30)
            int r3 = r3.length()
            int r3 = r3 + 25
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Preload failed for URL \""
            r4.append(r3)
            r4.append(r9)
            java.lang.String r3 = "\""
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.google.android.gms.internal.zzahw.zzc(r3, r2)
        L_0x04cf:
            boolean r2 = r12.exists()
            if (r2 == 0) goto L_0x04f9
            boolean r2 = r12.delete()
            if (r2 != 0) goto L_0x04f9
            java.lang.String r2 = "Could not delete partial cache file at "
            java.lang.String r3 = r12.getAbsolutePath()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x04f0
            java.lang.String r2 = r2.concat(r3)
            goto L_0x04f6
        L_0x04f0:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x04f6:
            com.google.android.gms.internal.zzahw.zzcz(r2)
        L_0x04f9:
            java.lang.String r2 = r12.getAbsolutePath()
            r8.zza(r9, r2, r15, r0)
            java.util.Set<java.lang.String> r0 = zzdox
            r0.remove(r1)
            r1 = 0
            return r1
        L_0x0507:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0507 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaoe.zzdc(java.lang.String):boolean");
    }
}
