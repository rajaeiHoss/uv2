package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzz;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> zzoma = new ArrayDeque(10);

    static boolean zzal(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "1".equals(bundle.getString("google.c.a.e"));
    }

    static void zzr(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bb, code lost:
        if (r3.equals("gcm") == false) goto L_0x009f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleIntent(android.content.Intent r12) {
        /*
            r11 = this;
            java.lang.String r0 = r12.getAction()
            if (r0 != 0) goto L_0x0008
            java.lang.String r0 = ""
        L_0x0008:
            r0.hashCode()
            java.lang.String r1 = "com.google.firebase.messaging.NOTIFICATION_DISMISS"
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L_0x0161
            java.lang.String r1 = "com.google.android.c2dm.intent.RECEIVE"
            boolean r0 = r0.equals(r1)
            java.lang.String r1 = "FirebaseMessaging"
            if (r0 != 0) goto L_0x003c
            java.lang.String r0 = "Unknown intent action: "
            java.lang.String r12 = r12.getAction()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            int r2 = r12.length()
            if (r2 == 0) goto L_0x0032
            java.lang.String r12 = r0.concat(r12)
            goto L_0x0037
        L_0x0032:
            java.lang.String r12 = new java.lang.String
            r12.<init>(r0)
        L_0x0037:
            android.util.Log.d(r1, r12)
            goto L_0x016e
        L_0x003c:
            java.lang.String r0 = "google.message_id"
            java.lang.String r2 = r12.getStringExtra(r0)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r4 = 1
            r5 = 3
            r6 = 0
            if (r3 == 0) goto L_0x004d
        L_0x004b:
            r3 = 0
            goto L_0x0086
        L_0x004d:
            java.util.Queue<java.lang.String> r3 = zzoma
            boolean r7 = r3.contains(r2)
            if (r7 == 0) goto L_0x0077
            boolean r3 = android.util.Log.isLoggable(r1, r5)
            if (r3 == 0) goto L_0x0075
            java.lang.String r3 = "Received duplicate message: "
            java.lang.String r7 = java.lang.String.valueOf(r2)
            int r8 = r7.length()
            if (r8 == 0) goto L_0x006c
            java.lang.String r3 = r3.concat(r7)
            goto L_0x0072
        L_0x006c:
            java.lang.String r7 = new java.lang.String
            r7.<init>(r3)
            r3 = r7
        L_0x0072:
            android.util.Log.d(r1, r3)
        L_0x0075:
            r3 = 1
            goto L_0x0086
        L_0x0077:
            int r7 = r3.size()
            r8 = 10
            if (r7 < r8) goto L_0x0082
            r3.remove()
        L_0x0082:
            r3.add(r2)
            goto L_0x004b
        L_0x0086:
            r7 = 2
            if (r3 != 0) goto L_0x014b
            java.lang.String r3 = "message_type"
            java.lang.String r3 = r12.getStringExtra(r3)
            java.lang.String r8 = "gcm"
            if (r3 != 0) goto L_0x0094
            r3 = r8
        L_0x0094:
            r3.hashCode()
            r9 = -1
            int r10 = r3.hashCode()
            switch(r10) {
                case -2062414158: goto L_0x00be;
                case 102161: goto L_0x00b7;
                case 814694033: goto L_0x00ac;
                case 814800675: goto L_0x00a1;
                default: goto L_0x009f;
            }
        L_0x009f:
            r4 = -1
            goto L_0x00c8
        L_0x00a1:
            java.lang.String r4 = "send_event"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x00aa
            goto L_0x009f
        L_0x00aa:
            r4 = 3
            goto L_0x00c8
        L_0x00ac:
            java.lang.String r4 = "send_error"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x00b5
            goto L_0x009f
        L_0x00b5:
            r4 = 2
            goto L_0x00c8
        L_0x00b7:
            boolean r5 = r3.equals(r8)
            if (r5 != 0) goto L_0x00c8
            goto L_0x009f
        L_0x00be:
            java.lang.String r4 = "deleted_messages"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x00c7
            goto L_0x009f
        L_0x00c7:
            r4 = 0
        L_0x00c8:
            switch(r4) {
                case 0: goto L_0x0148;
                case 1: goto L_0x0109;
                case 2: goto L_0x00ee;
                case 3: goto L_0x00e6;
                default: goto L_0x00cb;
            }
        L_0x00cb:
            java.lang.String r12 = "Received message with unknown type: "
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x00dc
            java.lang.String r12 = r12.concat(r3)
            goto L_0x00e2
        L_0x00dc:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r12)
            r12 = r3
        L_0x00e2:
            android.util.Log.w(r1, r12)
            goto L_0x014b
        L_0x00e6:
            java.lang.String r12 = r12.getStringExtra(r0)
            r11.onMessageSent(r12)
            goto L_0x014b
        L_0x00ee:
            java.lang.String r1 = r12.getStringExtra(r0)
            if (r1 != 0) goto L_0x00fa
            java.lang.String r1 = "message_id"
            java.lang.String r1 = r12.getStringExtra(r1)
        L_0x00fa:
            com.google.firebase.messaging.SendException r3 = new com.google.firebase.messaging.SendException
            java.lang.String r4 = "error"
            java.lang.String r12 = r12.getStringExtra(r4)
            r3.<init>(r12)
            r11.onSendError(r1, r3)
            goto L_0x014b
        L_0x0109:
            android.os.Bundle r1 = r12.getExtras()
            boolean r1 = zzal(r1)
            if (r1 == 0) goto L_0x0116
            com.google.firebase.messaging.zzd.zzf(r11, r12)
        L_0x0116:
            android.os.Bundle r1 = r12.getExtras()
            if (r1 != 0) goto L_0x0121
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
        L_0x0121:
            java.lang.String r3 = "androidx.contentpager.content.wakelockid"
            r1.remove(r3)
            boolean r3 = com.google.firebase.messaging.zza.zzai(r1)
            if (r3 == 0) goto L_0x013f
            com.google.firebase.messaging.zza r3 = com.google.firebase.messaging.zza.zzfc(r11)
            boolean r3 = r3.zzt(r1)
            if (r3 != 0) goto L_0x014b
            boolean r3 = zzal(r1)
            if (r3 == 0) goto L_0x013f
            com.google.firebase.messaging.zzd.zzi(r11, r12)
        L_0x013f:
            com.google.firebase.messaging.RemoteMessage r12 = new com.google.firebase.messaging.RemoteMessage
            r12.<init>(r1)
            r11.onMessageReceived(r12)
            goto L_0x014b
        L_0x0148:
            r11.onDeletedMessages()
        L_0x014b:
            boolean r12 = android.text.TextUtils.isEmpty(r2)
            if (r12 != 0) goto L_0x0160
            android.os.Bundle r12 = new android.os.Bundle
            r12.<init>()
            r12.putString(r0, r2)
            com.google.firebase.iid.zzk r0 = com.google.firebase.iid.zzk.zzfa(r11)
            r0.zzm(r7, r12)
        L_0x0160:
            return
        L_0x0161:
            android.os.Bundle r0 = r12.getExtras()
            boolean r0 = zzal(r0)
            if (r0 == 0) goto L_0x016e
            com.google.firebase.messaging.zzd.zzh(r11, r12)
        L_0x016e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.handleIntent(android.content.Intent):void");
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, Exception exc) {
    }

    /* access modifiers changed from: protected */
    public final Intent zzp(Intent intent) {
        return zzz.zzclq().zzclr();
    }

    public final boolean zzq(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (!zzal(intent.getExtras())) {
            return true;
        }
        zzd.zzg(this, intent);
        return true;
    }
}
