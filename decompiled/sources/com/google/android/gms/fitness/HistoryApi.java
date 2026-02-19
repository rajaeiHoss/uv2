package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.internal.zzbgq;
import java.util.concurrent.TimeUnit;

public interface HistoryApi {

    public static class ViewIntentBuilder {
        private final Context zzaiq;
        private final DataType zzhhj;
        private DataSource zzhhk;
        private long zzhhl;
        private long zzhhm;
        private String zzhhn;

        public ViewIntentBuilder(Context context, DataType dataType) {
            this.zzaiq = context;
            this.zzhhj = dataType;
        }

        public Intent build() {
            ResolveInfo resolveActivity;
            boolean z = true;
            zzbq.zza(this.zzhhl > 0, (Object) "Start time must be set");
            if (this.zzhhm <= this.zzhhl) {
                z = false;
            }
            zzbq.zza(z, (Object) "End time must be set and after start time");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(DataType.getMimeType(this.zzhhk.getDataType()));
            intent2.putExtra(Fitness.EXTRA_START_TIME, this.zzhhl);
            intent2.putExtra(Fitness.EXTRA_END_TIME, this.zzhhm);
            zzbgq.zza(this.zzhhk, intent2, DataSource.EXTRA_DATA_SOURCE);
            if (this.zzhhn == null || (resolveActivity = this.zzaiq.getPackageManager().resolveActivity(intent2, 0)) == null) {
                return intent2;
            }
            Intent intent = new Intent(intent2).setPackage(this.zzhhn);
            intent.setComponent(new ComponentName(this.zzhhn, resolveActivity.activityInfo.name));
            return intent;
        }

        public ViewIntentBuilder setDataSource(DataSource dataSource) {
            zzbq.zzb(dataSource.getDataType().equals(this.zzhhj), "Data source %s is not for the data type %s", dataSource, this.zzhhj);
            this.zzhhk = dataSource;
            return this;
        }

        public ViewIntentBuilder setPreferredApplication(String str) {
            this.zzhhn = str;
            return this;
        }

        public ViewIntentBuilder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzhhl = timeUnit.toMillis(j);
            this.zzhhm = timeUnit.toMillis(j2);
            return this;
        }
    }

    PendingResult<Status> deleteData(GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest);

    PendingResult<Status> insertData(GoogleApiClient googleApiClient, DataSet dataSet);

    PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient googleApiClient, DataType dataType);

    PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(GoogleApiClient googleApiClient, DataType dataType);

    PendingResult<DataReadResult> readData(GoogleApiClient googleApiClient, DataReadRequest dataReadRequest);

    PendingResult<Status> registerDataUpdateListener(GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest);

    PendingResult<Status> unregisterDataUpdateListener(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> updateData(GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest);
}
