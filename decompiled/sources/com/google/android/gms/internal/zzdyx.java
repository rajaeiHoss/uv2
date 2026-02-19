package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzdyx {
    public static long zza(long j, List<byte[]> list) {
        zzfmv zzam;
        if (list == null) {
            return j;
        }
        for (byte[] next : list) {
            if (!(next == null || (zzam = zzam(next)) == null || zzam.zzpzu <= j)) {
                j = zzam.zzpzu;
            }
        }
        return j;
    }

    private static Bundle zza(zzfmv zzfmv) {
        return zzay(zzfmv.zzpzs, zzfmv.zzpzt);
    }

    private static Object zza(zzfmv zzfmv, String str, zzdyw zzdyw) {
        Object obj = null;
        try {
            Class cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle zza = zza(zzfmv);
            Object newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(newInstance, str);
                cls.getField("mCreationTimestamp").set(newInstance, Long.valueOf(zzfmv.zzpzu));
                cls.getField("mName").set(newInstance, zzfmv.zzpzs);
                cls.getField("mValue").set(newInstance, zzfmv.zzpzt);
                if (!TextUtils.isEmpty(zzfmv.zzpzv)) {
                    obj = zzfmv.zzpzv;
                }
                cls.getField("mTriggerEventName").set(newInstance, obj);
                cls.getField("mTimedOutEventName").set(newInstance, !TextUtils.isEmpty(zzfmv.zzqaa) ? zzfmv.zzqaa : zzdyw.zzbta());
                cls.getField("mTimedOutEventParams").set(newInstance, zza);
                cls.getField("mTriggerTimeout").set(newInstance, Long.valueOf(zzfmv.zzpzw));
                cls.getField("mTriggeredEventName").set(newInstance, !TextUtils.isEmpty(zzfmv.zzpzy) ? zzfmv.zzpzy : zzdyw.zzbsz());
                cls.getField("mTriggeredEventParams").set(newInstance, zza);
                cls.getField("mTimeToLive").set(newInstance, Long.valueOf(zzfmv.zzgoc));
                cls.getField("mExpiredEventName").set(newInstance, !TextUtils.isEmpty(zzfmv.zzqab) ? zzfmv.zzqab : zzdyw.zzbtb());
                cls.getField("mExpiredEventParams").set(newInstance, zza);
                return newInstance;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                obj = newInstance;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return obj;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return obj;
        }
    }

    private static String zza(zzfmv zzfmv, zzdyw zzdyw) {
        return (zzfmv == null || TextUtils.isEmpty(zzfmv.zzpzz)) ? zzdyw.zzbtc() : zzfmv.zzpzz;
    }

    private static List<Object> zza(AppMeasurement appMeasurement, String str) {
        List arrayList = new ArrayList();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class});
            declaredMethod.setAccessible(true);
            arrayList = (List) declaredMethod.invoke(appMeasurement, new Object[]{str, ""});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            int size = arrayList.size();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 55);
            sb.append("Number of currently set _Es for origin: ");
            sb.append(str);
            sb.append(" is ");
            sb.append(size);
            Log.v("FirebaseAbtUtil", sb.toString());
        }
        return arrayList;
    }

    private static void zza(Context context, String str, String str2, String str3, String str4) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(str);
            Log.v("FirebaseAbtUtil", valueOf.length() != 0 ? "_CE(experimentId) called by ".concat(valueOf) : new String("_CE(experimentId) called by "));
        }
        if (zzey(context)) {
            AppMeasurement zzde = zzde(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class});
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 17 + String.valueOf(str3).length());
                    sb.append("Clearing _E: [");
                    sb.append(str2);
                    sb.append(", ");
                    sb.append(str3);
                    sb.append("]");
                    Log.v("FirebaseAbtUtil", sb.toString());
                }
                declaredMethod.invoke(zzde, new Object[]{str2, str4, zzay(str2, str3)});
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(Context context, String str, List<byte[]> list, int i, zzdyw zzdyw, long j) {
        AppMeasurement appMeasurement;
        String str2 = str;
        List<byte[]> list2 = list;
        long j2 = j;
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String valueOf = String.valueOf(str);
            Log.v("FirebaseAbtUtil", valueOf.length() != 0 ? "_UE called by ".concat(valueOf) : new String("_UE called by "));
        }
        if (zzey(context)) {
            AppMeasurement zzde = zzde(context);
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                List<Object> zza = zza(zzde, str2);
                new ArrayList();
                List<zzfmv> zzb = zzb(list2, zza);
                for (Object next : zzc(list2, zza)) {
                    String zzbe = zzbe(next);
                    String zzbf = zzbf(next);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        StringBuilder sb = new StringBuilder(String.valueOf(zzbe).length() + 30);
                        sb.append("Clearing _E as part of _UE: [");
                        sb.append(zzbe);
                        sb.append("]");
                        Log.v("FirebaseAbtUtil", sb.toString());
                    }
                    zza(context, str2, zzbe, zzbf, zza((zzfmv) null, zzdyw));
                }
                Context context2 = context;
                zzdyw zzdyw2 = zzdyw;
                for (zzfmv next2 : zzb) {
                    if (next2.zzpzu > j2) {
                        String str3 = next2.zzpzs;
                        String str4 = next2.zzpzt;
                        appMeasurement = zzde;
                        long j3 = next2.zzpzu;
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 106 + String.valueOf(str4).length());
                        sb2.append("Setting _E as part of _UE: [");
                        sb2.append(str3);
                        sb2.append(", ");
                        sb2.append(str4);
                        sb2.append(", ");
                        sb2.append(j3);
                        sb2.append("], latestOriginKnownExpStartTime: ");
                        sb2.append(j2);
                        Log.v("FirebaseAbtUtil", sb2.toString());
                        zza(appMeasurement, context, str, next2, zzdyw, 1);
                    } else {
                        appMeasurement = zzde;
                        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                            String str5 = next2.zzpzs;
                            String str6 = next2.zzpzt;
                            long j4 = next2.zzpzu;
                            StringBuilder sb3 = new StringBuilder(String.valueOf(str5).length() + 118 + String.valueOf(str6).length());
                            sb3.append("Not setting _E, due to lastUpdateTime: [");
                            sb3.append(str5);
                            sb3.append(", ");
                            sb3.append(str6);
                            sb3.append(", ");
                            sb3.append(j4);
                            sb3.append("], latestOriginKnownExpStartTime: ");
                            sb3.append(j2);
                            Log.v("FirebaseAbtUtil", sb3.toString());
                        }
                    }
                    String str7 = str;
                    zzde = appMeasurement;
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x020c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        android.util.Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0216, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0217, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0217 A[ExcHandler: NoSuchFieldException (e java.lang.NoSuchFieldException), Splitter:B:3:0x0048] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.measurement.AppMeasurement r16, android.content.Context r17, java.lang.String r18, com.google.android.gms.internal.zzfmv r19, com.google.android.gms.internal.zzdyw r20, int r21) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            java.lang.String r4 = "Could not complete the operation due to an internal error."
            java.lang.String r5 = "com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty"
            java.lang.String r6 = "FirebaseAbtUtil"
            r7 = 2
            boolean r8 = android.util.Log.isLoggable(r6, r7)
            if (r8 == 0) goto L_0x0048
            java.lang.String r8 = r3.zzpzs
            java.lang.String r9 = r3.zzpzt
            java.lang.String r10 = java.lang.String.valueOf(r8)
            int r10 = r10.length()
            int r10 = r10 + 7
            java.lang.String r11 = java.lang.String.valueOf(r9)
            int r11 = r11.length()
            int r10 = r10 + r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r10)
            java.lang.String r10 = "_SEI: "
            r11.append(r10)
            r11.append(r8)
            java.lang.String r8 = " "
            r11.append(r8)
            r11.append(r9)
            java.lang.String r8 = r11.toString()
            android.util.Log.v(r6, r8)
        L_0x0048:
            java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.util.List r8 = zza((com.google.android.gms.measurement.AppMeasurement) r0, (java.lang.String) r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r9 = zzb((com.google.android.gms.measurement.AppMeasurement) r0, (java.lang.String) r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.util.List r10 = zza((com.google.android.gms.measurement.AppMeasurement) r0, (java.lang.String) r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r10 = r10.size()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r11 = "]"
            r12 = 0
            java.lang.String r13 = ", "
            r14 = 1
            if (r10 < r9) goto L_0x00e3
            int r9 = r3.zzqac     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r9 == 0) goto L_0x006a
            int r9 = r3.zzqac     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            goto L_0x006b
        L_0x006a:
            r9 = 1
        L_0x006b:
            if (r9 != r14) goto L_0x00a8
            java.lang.Object r9 = r8.get(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r10 = zzbe(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r9 = zzbf(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            boolean r15 = android.util.Log.isLoggable(r6, r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r15 == 0) goto L_0x00a0
            java.lang.String r15 = java.lang.String.valueOf(r10)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r15 = r15.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r15 = r15 + 38
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r12.<init>(r15)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r15 = "Clearing _E due to overflow policy: ["
            r12.append(r15)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r12.append(r10)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r12.append(r11)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = r12.toString()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            android.util.Log.v(r6, r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
        L_0x00a0:
            java.lang.String r12 = zza((com.google.android.gms.internal.zzfmv) r19, (com.google.android.gms.internal.zzdyw) r20)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            zza(r1, r2, r10, r9, r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            goto L_0x00e3
        L_0x00a8:
            boolean r0 = android.util.Log.isLoggable(r6, r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r0 == 0) goto L_0x00e2
            java.lang.String r0 = r3.zzpzs     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r1 = r3.zzpzt     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r2 = r2.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r2 = r2 + 44
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r3 = r3.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.<init>(r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r2 = "_E won't be set due to overflow policy. ["
            r3.append(r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r13)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r1)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r11)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r0 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            android.util.Log.v(r6, r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
        L_0x00e2:
            return
        L_0x00e3:
            java.util.Iterator r8 = r8.iterator()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
        L_0x00e7:
            boolean r9 = r8.hasNext()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r9 == 0) goto L_0x0149
            java.lang.Object r9 = r8.next()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r10 = zzbe(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r9 = zzbf(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = r3.zzpzs     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            boolean r12 = r10.equals(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r12 == 0) goto L_0x00e7
            java.lang.String r12 = r3.zzpzt     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            boolean r12 = r9.equals(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r12 != 0) goto L_0x00e7
            boolean r12 = android.util.Log.isLoggable(r6, r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r12 == 0) goto L_0x00e7
            java.lang.String r12 = java.lang.String.valueOf(r10)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12 + 77
            java.lang.String r15 = java.lang.String.valueOf(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r15 = r15.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12 + r15
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.<init>(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = "Clearing _E, as only one _V of the same _E can be set atany given time: ["
            r15.append(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r10)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r13)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = "]."
            r15.append(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = r15.toString()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            android.util.Log.v(r6, r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = zza((com.google.android.gms.internal.zzfmv) r19, (com.google.android.gms.internal.zzdyw) r20)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            zza(r1, r2, r10, r9, r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            goto L_0x00e7
        L_0x0149:
            r1 = r20
            java.lang.Object r8 = zza(r3, r2, r1)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r8 != 0) goto L_0x018e
            boolean r0 = android.util.Log.isLoggable(r6, r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r0 == 0) goto L_0x018d
            java.lang.String r0 = r3.zzpzs     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r1 = r3.zzpzt     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r2 = r2.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r2 = r2 + 42
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r3 = r3.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.<init>(r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r2 = "Could not create _CUP for: ["
            r3.append(r2)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r13)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r3.append(r1)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r0 = "]. Skipping."
            r3.append(r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r0 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            android.util.Log.v(r6, r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
        L_0x018d:
            return
        L_0x018e:
            boolean r7 = android.util.Log.isLoggable(r6, r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            if (r7 == 0) goto L_0x01d9
            java.lang.String r7 = r3.zzpzs     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r9 = r3.zzpzt     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r10 = r3.zzpzv     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = java.lang.String.valueOf(r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12 + 27
            java.lang.String r15 = java.lang.String.valueOf(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r15 = r15.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12 + r15
            java.lang.String r15 = java.lang.String.valueOf(r10)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r15 = r15.length()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            int r12 = r12 + r15
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.<init>(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r12 = "Setting _CUP for _E: ["
            r15.append(r12)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r13)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r9)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r13)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r10)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            r15.append(r11)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            java.lang.String r7 = r15.toString()     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            android.util.Log.v(r6, r7)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
        L_0x01d9:
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            java.lang.Class<com.google.android.gms.measurement.AppMeasurement> r7 = com.google.android.gms.measurement.AppMeasurement.class
            java.lang.String r9 = "setConditionalUserProperty"
            java.lang.Class[] r10 = new java.lang.Class[r14]     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            r11 = 0
            r10[r11] = r5     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            java.lang.reflect.Method r5 = r7.getDeclaredMethod(r9, r10)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            r5.setAccessible(r14)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            java.lang.String r7 = r3.zzpzx     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            if (r7 != 0) goto L_0x01f8
            java.lang.String r1 = r3.zzpzx     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            goto L_0x01fc
        L_0x01f8:
            java.lang.String r1 = r20.zzbsy()     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
        L_0x01fc:
            android.os.Bundle r3 = zza(r19)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            r0.logEventInternal(r2, r1, r3)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            java.lang.Object[] r1 = new java.lang.Object[r14]     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            r2 = 0
            r1[r2] = r8     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            r5.invoke(r0, r1)     // Catch:{ ClassNotFoundException -> 0x0212, NoSuchMethodException -> 0x0210, IllegalAccessException -> 0x020e, InvocationTargetException -> 0x020c, NoSuchFieldException -> 0x0217 }
            return
        L_0x020c:
            r0 = move-exception
            goto L_0x0213
        L_0x020e:
            r0 = move-exception
            goto L_0x0213
        L_0x0210:
            r0 = move-exception
            goto L_0x0213
        L_0x0212:
            r0 = move-exception
        L_0x0213:
            android.util.Log.e(r6, r4, r0)     // Catch:{ ClassNotFoundException -> 0x021b, IllegalAccessException -> 0x0219, NoSuchFieldException -> 0x0217 }
            return
        L_0x0217:
            r0 = move-exception
            goto L_0x021c
        L_0x0219:
            r0 = move-exception
            goto L_0x021c
        L_0x021b:
            r0 = move-exception
        L_0x021c:
            android.util.Log.e(r6, r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdyx.zza(com.google.android.gms.measurement.AppMeasurement, android.content.Context, java.lang.String, com.google.android.gms.internal.zzfmv, com.google.android.gms.internal.zzdyw, int):void");
    }

    private static zzfmv zzam(byte[] bArr) {
        try {
            return zzfmv.zzbi(bArr);
        } catch (zzflr unused) {
            return null;
        }
    }

    private static Bundle zzay(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    private static int zzb(AppMeasurement appMeasurement, String str) {
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, new Object[]{str})).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    private static List<zzfmv> zzb(List<byte[]> list, List<Object> list2) {
        ArrayList arrayList = new ArrayList();
        for (byte[] zzam : list) {
            zzfmv zzam2 = zzam(zzam);
            if (zzam2 != null) {
                boolean z = false;
                Iterator<Object> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    try {
                        Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                        zzbe(next);
                        String zzbf = zzbf(next);
                        if (zzam2.zzpzs.equals(zzbe(next)) && zzam2.zzpzt.equals(zzbf)) {
                            z = true;
                            break;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                    }
                }
                if (!z) {
                    arrayList.add(zzam2);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "Couldn't deserialize the payload; skipping.");
            }
        }
        return arrayList;
    }

    private static String zzbe(Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    private static String zzbf(Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    private static List<Object> zzc(List<byte[]> list, List<Object> list2) {
        ArrayList arrayList = new ArrayList();
        for (Object next : list2) {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                String zzbe = zzbe(next);
                String zzbf = zzbf(next);
                boolean z = true;
                Iterator<byte[]> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        zzfmv zzam = zzam(it.next());
                        if (zzam != null) {
                            if (zzam.zzpzs.equals(zzbe) && zzam.zzpzt.equals(zzbf)) {
                                z = false;
                                break;
                            }
                        } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                            Log.v("FirebaseAbtUtil", "Couldn't deserialize the payload; skipping.");
                        }
                    } else {
                        break;
                    }
                }
                if (z) {
                    arrayList.add(next);
                }
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
        return arrayList;
    }

    private static AppMeasurement zzde(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }

    private static boolean zzey(Context context) {
        if (zzde(context) == null) {
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            }
            return false;
        }
        try {
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            return true;
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
            }
            return false;
        }
    }
}
