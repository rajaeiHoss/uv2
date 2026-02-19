package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityOptions;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.tasks.Task;

public final class zzbnv extends DriveClient {
    private final DriveApi zzguh = new zzbmu();

    public zzbnv(Activity activity, Drive.zza zza) {
        super(activity, zza);
    }

    public zzbnv(Context context, Drive.zza zza) {
        super(context, zza);
    }

    public final Task<DriveId> getDriveId(String str) {
        return zzbj.zza(this.zzguh.fetchDriveId(zzahw(), str), zzbnw.zzgui);
    }

    public final Task<TransferPreferences> getUploadPreferences() {
        return zzbj.zza(this.zzguh.zze(zzahw()), zzbnx.zzgui);
    }

    public final Task<IntentSender> newCreateFileActivityIntentSender(CreateFileActivityOptions createFileActivityOptions) {
        return zza(new zzbnz(this, createFileActivityOptions));
    }

    public final Task<IntentSender> newOpenFileActivityIntentSender(OpenFileActivityOptions openFileActivityOptions) {
        return zza(new zzbny(this, openFileActivityOptions));
    }

    public final Task<Void> requestSync() {
        return zzbj.zzb(this.zzguh.requestSync(zzahw()));
    }

    public final Task<Void> setUploadPreferences(TransferPreferences transferPreferences) {
        return zzbj.zzb(this.zzguh.zza(zzahw(), transferPreferences));
    }
}
