package com.google.android.gms.tagmanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class TagManagerService extends Service {
    public static void initialize(Context context) {
        zzbf.zzej(context);
    }

    public IBinder onBind(Intent intent) {
        return zzbf.zzei(this);
    }
}
