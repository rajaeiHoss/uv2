package com.google.android.gms.internal;

import android.os.Handler;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;

final class zzedh extends zzeou {
    private /* synthetic */ zzemm zzmxu;
    private /* synthetic */ zzedg zzmxv;

    zzedh(zzedg zzedg, zzemm zzemm) {
        this.zzmxv = zzedg;
        this.zzmxu = zzemm;
    }

    public final void zzj(Throwable th) {
        String str;
        if (th instanceof OutOfMemoryError) {
            str = "Firebase Database encountered an OutOfMemoryError. You may need to reduce the amount of data you are syncing to the client (e.g. by using queries or syncing a deeper path). See https://firebase.google.com/docs/database/ios/structure-data#best_practices_for_data_structure and https://firebase.google.com/docs/database/android/retrieve-data#filtering_data";
        } else if (th instanceof DatabaseException) {
            str = "";
        } else {
            String sdkVersion = FirebaseDatabase.getSdkVersion();
            StringBuilder sb = new StringBuilder(String.valueOf(sdkVersion).length() + 104);
            sb.append("Uncaught exception in Firebase Database runloop (");
            sb.append(sdkVersion);
            sb.append("). Please report to firebase-database-client@google.com");
            str = sb.toString();
        }
        this.zzmxu.zze(str, th);
        new Handler(this.zzmxv.zzajv.getMainLooper()).post(new zzedi(this, str, th));
        zzbwm().shutdownNow();
    }
}
