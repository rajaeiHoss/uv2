package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayDeque;
import java.util.Queue;

public final class zzz {
    private static zzz zzolj;
    private final SimpleArrayMap<String, String> zzolk = new SimpleArrayMap<>();
    private Boolean zzoll = null;
    final Queue<Intent> zzolm = new ArrayDeque();
    private Queue<Intent> zzoln = new ArrayDeque();

    private zzz() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction("com.google.firebase.MESSAGING_EVENT");
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, BasicMeasure.EXACTLY);
    }

    public static synchronized zzz zzclq() {
        zzz zzz;
        synchronized (zzz.class) {
            if (zzolj == null) {
                zzolj = new zzz();
            }
            zzz = zzolj;
        }
        return zzz;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00db A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f2 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f7 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0104 A[Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zze(android.content.Context r7, android.content.Intent r8) {
        /*
            r6 = this;
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.String> r0 = r6.zzolk
            monitor-enter(r0)
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.String> r1 = r6.zzolk     // Catch:{ all -> 0x0143 }
            java.lang.String r2 = r8.getAction()     // Catch:{ all -> 0x0143 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0143 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0143 }
            monitor-exit(r0)     // Catch:{ all -> 0x0143 }
            r0 = 0
            if (r1 != 0) goto L_0x00ab
            android.content.pm.PackageManager r1 = r7.getPackageManager()
            android.content.pm.ResolveInfo r1 = r1.resolveService(r8, r0)
            if (r1 == 0) goto L_0x00a3
            android.content.pm.ServiceInfo r2 = r1.serviceInfo
            if (r2 != 0) goto L_0x0023
            goto L_0x00a3
        L_0x0023:
            android.content.pm.ServiceInfo r1 = r1.serviceInfo
            java.lang.String r2 = r7.getPackageName()
            java.lang.String r3 = r1.packageName
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x006d
            java.lang.String r2 = r1.name
            if (r2 != 0) goto L_0x0036
            goto L_0x006d
        L_0x0036:
            java.lang.String r1 = r1.name
            java.lang.String r2 = "."
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x005c
            java.lang.String r2 = r7.getPackageName()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x0057
            java.lang.String r1 = r2.concat(r1)
            goto L_0x005c
        L_0x0057:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x005c:
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.String> r2 = r6.zzolk
            monitor-enter(r2)
            androidx.collection.SimpleArrayMap<java.lang.String, java.lang.String> r3 = r6.zzolk     // Catch:{ all -> 0x006a }
            java.lang.String r4 = r8.getAction()     // Catch:{ all -> 0x006a }
            r3.put(r4, r1)     // Catch:{ all -> 0x006a }
            monitor-exit(r2)     // Catch:{ all -> 0x006a }
            goto L_0x00ab
        L_0x006a:
            r7 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x006a }
            throw r7
        L_0x006d:
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = r1.packageName
            java.lang.String r1 = r1.name
            java.lang.String r4 = java.lang.String.valueOf(r3)
            int r4 = r4.length()
            int r4 = r4 + 94
            java.lang.String r5 = java.lang.String.valueOf(r1)
            int r5 = r5.length()
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "Error resolving target intent service, skipping classname enforcement. Resolved service was: "
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = "/"
            r5.append(r3)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            android.util.Log.e(r2, r1)
            goto L_0x00d7
        L_0x00a3:
            java.lang.String r1 = "FirebaseInstanceId"
            java.lang.String r2 = "Failed to resolve target intent service, skipping classname enforcement"
            android.util.Log.e(r1, r2)
            goto L_0x00d7
        L_0x00ab:
            java.lang.String r2 = "FirebaseInstanceId"
            r3 = 3
            boolean r2 = android.util.Log.isLoggable(r2, r3)
            if (r2 == 0) goto L_0x00d0
            java.lang.String r2 = "FirebaseInstanceId"
            java.lang.String r3 = "Restricting intent to a specific service: "
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r5 = r4.length()
            if (r5 == 0) goto L_0x00c7
            java.lang.String r3 = r3.concat(r4)
            goto L_0x00cd
        L_0x00c7:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r3)
            r3 = r4
        L_0x00cd:
            android.util.Log.d(r2, r3)
        L_0x00d0:
            java.lang.String r2 = r7.getPackageName()
            r8.setClassName(r2, r1)
        L_0x00d7:
            java.lang.Boolean r1 = r6.zzoll     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            if (r1 != 0) goto L_0x00ea
            java.lang.String r1 = "android.permission.WAKE_LOCK"
            int r1 = r7.checkCallingOrSelfPermission(r1)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            if (r1 != 0) goto L_0x00e4
            r0 = 1
        L_0x00e4:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            r6.zzoll = r0     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
        L_0x00ea:
            java.lang.Boolean r0 = r6.zzoll     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            boolean r0 = r0.booleanValue()     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            if (r0 == 0) goto L_0x00f7
            android.content.ComponentName r7 = androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService(r7, r8)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            goto L_0x0102
        L_0x00f7:
            android.content.ComponentName r7 = r7.startService(r8)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            java.lang.String r8 = "FirebaseInstanceId"
            java.lang.String r0 = "Missing wake lock permission, service start may be delayed"
            android.util.Log.d(r8, r0)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
        L_0x0102:
            if (r7 != 0) goto L_0x010e
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r8 = "Error while delivering the message: ServiceIntent not found."
            android.util.Log.e(r7, r8)     // Catch:{ SecurityException -> 0x0138, IllegalStateException -> 0x0110 }
            r7 = 404(0x194, float:5.66E-43)
            return r7
        L_0x010e:
            r7 = -1
            return r7
        L_0x0110:
            r7 = move-exception
            java.lang.String r8 = "FirebaseInstanceId"
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r0 = java.lang.String.valueOf(r7)
            int r0 = r0.length()
            int r0 = r0 + 45
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Failed to start service while in background: "
            r1.append(r0)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            android.util.Log.e(r8, r7)
            r7 = 402(0x192, float:5.63E-43)
            return r7
        L_0x0138:
            r7 = move-exception
            java.lang.String r8 = "FirebaseInstanceId"
            java.lang.String r0 = "Error while delivering the message to the serviceIntent"
            android.util.Log.e(r8, r0, r7)
            r7 = 401(0x191, float:5.62E-43)
            return r7
        L_0x0143:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0143 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzz.zze(android.content.Context, android.content.Intent):int");
    }

    public final int zzb(Context context, String str, Intent intent) {
        Queue<Intent> queue;
        str.hashCode();
        if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
            queue = this.zzolm;
        } else if (!str.equals("com.google.firebase.MESSAGING_EVENT")) {
            String valueOf = String.valueOf(str);
            Log.w("FirebaseInstanceId", valueOf.length() != 0 ? "Unknown service action: ".concat(valueOf) : new String("Unknown service action: "));
            return 500;
        } else {
            queue = this.zzoln;
        }
        queue.offer(intent);
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zze(context, intent2);
    }

    public final Intent zzclr() {
        return this.zzoln.poll();
    }
}
