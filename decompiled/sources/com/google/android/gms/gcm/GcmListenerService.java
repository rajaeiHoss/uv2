package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.iid.zzb;
import java.util.Iterator;
import org.jivesoftware.smack.packet.PrivacyItem;

public class GcmListenerService extends zzb {
    private static final String SERVICE_DATA_ERROR = "error";

    static void zzr(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    public void handleIntent(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            String valueOf = String.valueOf(intent.getAction());
            Log.w("GcmListenerService", valueOf.length() != 0 ? "Unknown intent action: ".concat(valueOf) : new String("Unknown intent action: "));
            return;
        }
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        stringExtra.hashCode();
        char c = 65535;
        boolean z = true;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
                    c = 0;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals("gcm")) {
                    c = 1;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
                    c = 2;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                onDeletedMessages();
                return;
            case 1:
                Bundle extras = intent.getExtras();
                extras.remove("message_type");
                extras.remove("androidx.contentpager.content.wakelockid");
                if (!"1".equals(zza.zzd(extras, "gcm.n.e")) && zza.zzd(extras, "gcm.n.icon") == null) {
                    z = false;
                }
                if (z) {
                    if (!zza.zzdm(this)) {
                        zza.zzdl(this).zzt(extras);
                        return;
                    }
                    zza.zzs(extras);
                }
                String string = extras.getString(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM);
                extras.remove(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM);
                zzr(extras);
                onMessageReceived(string, extras);
                return;
            case 2:
                String stringExtra2 = intent.getStringExtra("google.message_id");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("message_id");
                }
                onSendError(stringExtra2, intent.getStringExtra(SERVICE_DATA_ERROR));
                return;
            case 3:
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            default:
                String valueOf2 = String.valueOf(stringExtra);
                Log.w("GcmListenerService", valueOf2.length() != 0 ? "Received message with unknown type: ".concat(valueOf2) : new String("Received message with unknown type: "));
                return;
        }
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }
}
