package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.tasks.Task;

public abstract class DriveClient extends GoogleApi<Drive.zza> {
    public DriveClient(Activity activity, Drive.zza zza) {
        super(activity, Drive.zzgpo, zza, GoogleApi.zza.zzfsr);
    }

    public DriveClient(Context context, Drive.zza zza) {
        super(context, Drive.zzgpo, zza, GoogleApi.zza.zzfsr);
    }

    public abstract Task<DriveId> getDriveId(String str);

    public abstract Task<TransferPreferences> getUploadPreferences();

    public abstract Task<IntentSender> newCreateFileActivityIntentSender(CreateFileActivityOptions createFileActivityOptions);

    public abstract Task<IntentSender> newOpenFileActivityIntentSender(OpenFileActivityOptions openFileActivityOptions);

    public abstract Task<Void> requestSync();

    public abstract Task<Void> setUploadPreferences(TransferPreferences transferPreferences);
}
