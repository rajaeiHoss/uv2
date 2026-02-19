package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;

final class zzc implements Runnable {
    private /* synthetic */ BroadcastReceiver.PendingResult zzdue;

    zzc(CampaignTrackingReceiver campaignTrackingReceiver, BroadcastReceiver.PendingResult pendingResult) {
        this.zzdue = pendingResult;
    }

    public final void run() {
        BroadcastReceiver.PendingResult pendingResult = this.zzdue;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}
