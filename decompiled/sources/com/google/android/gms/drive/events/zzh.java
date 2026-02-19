package com.google.android.gms.drive.events;

import android.os.Looper;
import com.google.android.gms.drive.events.DriveEventService;
import java.util.concurrent.CountDownLatch;

final class zzh extends Thread {
    private /* synthetic */ CountDownLatch zzgse;
    private /* synthetic */ DriveEventService zzgsf;

    zzh(DriveEventService driveEventService, CountDownLatch countDownLatch) {
        this.zzgsf = driveEventService;
        this.zzgse = countDownLatch;
    }

    public final void run() {
        try {
            Looper.prepare();
            this.zzgsf.zzgsb = this.zzgsf.new zza();
            this.zzgsf.zzgsc = false;
            this.zzgse.countDown();
            DriveEventService.zzgpv.zzu("DriveEventService", "Bound and starting loop");
            Looper.loop();
            DriveEventService.zzgpv.zzu("DriveEventService", "Finished loop");
        } finally {
            if (this.zzgsf.zzgsa != null) {
                this.zzgsf.zzgsa.countDown();
            }
        }
    }
}
