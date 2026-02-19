package com.google.android.gms.cast.framework;

public class MediaNotificationManager {
    private final SessionManager zzezv;

    public MediaNotificationManager(SessionManager sessionManager) {
        this.zzezv = sessionManager;
    }

    public void updateNotification() {
        CastSession currentCastSession = this.zzezv.getCurrentCastSession();
        if (currentCastSession != null) {
            currentCastSession.zzaed().zzbc(true);
        }
    }
}
