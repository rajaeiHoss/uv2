package com.google.android.gms.internal;

import android.media.MediaCodecInfo;
import android.util.Range;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzabh
public final class zzakm {
    private static Map<String, List<Map<String, Object>>> zzdia = new HashMap();
    private static List<MediaCodecInfo> zzdib;
    private static final Object zzdic = new Object();

    private static Integer[] zza(Range<Integer> range) {
        return new Integer[]{range.getLower(), range.getUpper()};
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> zzct(java.lang.String r14) {
        /*
            java.lang.Object r0 = zzdic
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r1 = zzdia     // Catch:{ all -> 0x015d }
            boolean r1 = r1.containsKey(r14)     // Catch:{ all -> 0x015d }
            if (r1 == 0) goto L_0x0015
            java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r1 = zzdia     // Catch:{ all -> 0x015d }
            java.lang.Object r14 = r1.get(r14)     // Catch:{ all -> 0x015d }
            java.util.List r14 = (java.util.List) r14     // Catch:{ all -> 0x015d }
            monitor-exit(r0)     // Catch:{ all -> 0x015d }
            return r14
        L_0x0015:
            monitor-enter(r0)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.util.List<android.media.MediaCodecInfo> r1 = zzdib     // Catch:{ all -> 0x0136 }
            r2 = 21
            r3 = 0
            if (r1 == 0) goto L_0x001f
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            goto L_0x0058
        L_0x001f:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0136 }
            if (r1 < r2) goto L_0x0033
            android.media.MediaCodecList r1 = new android.media.MediaCodecList     // Catch:{ all -> 0x0136 }
            r1.<init>(r3)     // Catch:{ all -> 0x0136 }
            android.media.MediaCodecInfo[] r1 = r1.getCodecInfos()     // Catch:{ all -> 0x0136 }
            java.util.List r1 = java.util.Arrays.asList(r1)     // Catch:{ all -> 0x0136 }
        L_0x0030:
            zzdib = r1     // Catch:{ all -> 0x0136 }
            goto L_0x001d
        L_0x0033:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0136 }
            r4 = 16
            if (r1 < r4) goto L_0x0053
            int r1 = android.media.MediaCodecList.getCodecCount()     // Catch:{ all -> 0x0136 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0136 }
            r4.<init>(r1)     // Catch:{ all -> 0x0136 }
            zzdib = r4     // Catch:{ all -> 0x0136 }
            r4 = 0
        L_0x0045:
            if (r4 >= r1) goto L_0x001d
            android.media.MediaCodecInfo r5 = android.media.MediaCodecList.getCodecInfoAt(r4)     // Catch:{ all -> 0x0136 }
            java.util.List<android.media.MediaCodecInfo> r6 = zzdib     // Catch:{ all -> 0x0136 }
            r6.add(r5)     // Catch:{ all -> 0x0136 }
            int r4 = r4 + 1
            goto L_0x0045
        L_0x0053:
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0136 }
            goto L_0x0030
        L_0x0058:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r1.<init>()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.util.List<android.media.MediaCodecInfo> r4 = zzdib     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
        L_0x0063:
            boolean r5 = r4.hasNext()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            if (r5 == 0) goto L_0x012f
            java.lang.Object r5 = r4.next()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            android.media.MediaCodecInfo r5 = (android.media.MediaCodecInfo) r5     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            boolean r6 = r5.isEncoder()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            if (r6 != 0) goto L_0x0063
            java.lang.String[] r6 = r5.getSupportedTypes()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            boolean r6 = r6.contains(r14)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            if (r6 == 0) goto L_0x0063
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.<init>()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r7 = "codecName"
            java.lang.String r8 = r5.getName()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r7, r8)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            android.media.MediaCodecInfo$CodecCapabilities r5 = r5.getCapabilitiesForType(r14)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r7.<init>()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            android.media.MediaCodecInfo$CodecProfileLevel[] r8 = r5.profileLevels     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            int r9 = r8.length     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r10 = 0
        L_0x009e:
            if (r10 >= r9) goto L_0x00bc
            r11 = r8[r10]     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r12 = 2
            java.lang.Integer[] r12 = new java.lang.Integer[r12]     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            int r13 = r11.profile     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r12[r3] = r13     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            int r11 = r11.level     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r13 = 1
            r12[r13] = r11     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r7.add(r12)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            int r10 = r10 + 1
            goto L_0x009e
        L_0x00bc:
            java.lang.String r8 = "profileLevels"
            r6.put(r8, r7)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            if (r7 < r2) goto L_0x0117
            android.media.MediaCodecInfo$VideoCapabilities r7 = r5.getVideoCapabilities()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r8 = "bitRatesBps"
            android.util.Range r9 = r7.getBitrateRange()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer[] r9 = zza(r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r8, r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r8 = "widthAlignment"
            int r9 = r7.getWidthAlignment()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r8, r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r8 = "heightAlignment"
            int r9 = r7.getHeightAlignment()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r8, r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r8 = "frameRates"
            android.util.Range r9 = r7.getSupportedFrameRates()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer[] r9 = zza(r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r8, r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r8 = "widths"
            android.util.Range r9 = r7.getSupportedWidths()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer[] r9 = zza(r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r8, r9)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.String r8 = "heights"
            android.util.Range r7 = r7.getSupportedHeights()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer[] r7 = zza(r7)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r8, r7)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
        L_0x0117:
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r8 = 23
            if (r7 < r8) goto L_0x012a
            java.lang.String r7 = "instancesLimit"
            int r5 = r5.getMaxSupportedInstances()     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r6.put(r7, r5)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
        L_0x012a:
            r1.add(r6)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            goto L_0x0063
        L_0x012f:
            java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r2 = zzdia     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            r2.put(r14, r1)     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
            monitor-exit(r0)     // Catch:{ all -> 0x015d }
            return r1
        L_0x0136:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            throw r1     // Catch:{ RuntimeException -> 0x013b, LinkageError -> 0x0139 }
        L_0x0139:
            r1 = move-exception
            goto L_0x013c
        L_0x013b:
            r1 = move-exception
        L_0x013c:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x015d }
            r2.<init>()     // Catch:{ all -> 0x015d }
            java.lang.String r3 = "error"
            java.lang.Class r1 = r1.getClass()     // Catch:{ all -> 0x015d }
            java.lang.String r1 = r1.getSimpleName()     // Catch:{ all -> 0x015d }
            r2.put(r3, r1)     // Catch:{ all -> 0x015d }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x015d }
            r1.<init>()     // Catch:{ all -> 0x015d }
            r1.add(r2)     // Catch:{ all -> 0x015d }
            java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r2 = zzdia     // Catch:{ all -> 0x015d }
            r2.put(r14, r1)     // Catch:{ all -> 0x015d }
            monitor-exit(r0)     // Catch:{ all -> 0x015d }
            return r1
        L_0x015d:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x015d }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzakm.zzct(java.lang.String):java.util.List");
    }
}
