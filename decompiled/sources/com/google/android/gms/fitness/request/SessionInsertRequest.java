package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbwh;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smackx.workgroup.packet.SessionID;

public class SessionInsertRequest extends zzbgl {
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new zzau();
    private final Session zzhhs;
    private final List<DataSet> zzhib;
    private final zzbzt zzhnu;
    private final List<DataPoint> zzhph;

    public static class Builder {
        /* access modifiers changed from: private */
        public Session zzhhs;
        /* access modifiers changed from: private */
        public List<DataSet> zzhib = new ArrayList();
        /* access modifiers changed from: private */
        public List<DataPoint> zzhph = new ArrayList();
        private List<DataSource> zzhpi = new ArrayList();

        private final void zzd(DataPoint dataPoint) {
            DataPoint dataPoint2 = dataPoint;
            long startTime = this.zzhhs.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.zzhhs.getEndTime(TimeUnit.NANOSECONDS);
            long timestamp = dataPoint2.getTimestamp(TimeUnit.NANOSECONDS);
            if (timestamp != 0) {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                if (timestamp < startTime || timestamp > endTime) {
                    timestamp = zzbwh.zza(timestamp, TimeUnit.NANOSECONDS, timeUnit);
                }
                zzbq.zza(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint2, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint2.getTimestamp(TimeUnit.NANOSECONDS) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[]{Long.valueOf(dataPoint2.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(timestamp), timeUnit}));
                    dataPoint2.setTimestamp(timestamp, TimeUnit.NANOSECONDS);
                }
            }
            long startTime2 = this.zzhhs.getStartTime(TimeUnit.NANOSECONDS);
            long endTime2 = this.zzhhs.getEndTime(TimeUnit.NANOSECONDS);
            long startTime3 = dataPoint2.getStartTime(TimeUnit.NANOSECONDS);
            long endTime3 = dataPoint2.getEndTime(TimeUnit.NANOSECONDS);
            if (startTime3 != 0 && endTime3 != 0) {
                TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
                if (endTime3 > endTime2) {
                    endTime3 = zzbwh.zza(endTime3, TimeUnit.NANOSECONDS, timeUnit2);
                }
                zzbq.zza(startTime3 >= startTime2 && endTime3 <= endTime2, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint2, Long.valueOf(startTime2), Long.valueOf(endTime2));
                if (endTime3 != dataPoint2.getEndTime(TimeUnit.NANOSECONDS)) {
                    Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[]{Long.valueOf(dataPoint2.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(endTime3), timeUnit2}));
                    dataPoint.setTimeInterval(startTime3, endTime3, TimeUnit.NANOSECONDS);
                }
            }
        }

        public Builder addAggregateDataPoint(DataPoint dataPoint) {
            zzbq.checkArgument(dataPoint != null, "Must specify a valid aggregate data point.");
            DataSource dataSource = dataPoint.getDataSource();
            zzbq.zza(!this.zzhpi.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            DataSet.zzb(dataPoint);
            this.zzhpi.add(dataSource);
            this.zzhph.add(dataPoint);
            return this;
        }

        public Builder addDataSet(DataSet dataSet) {
            zzbq.checkArgument(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            zzbq.zza(!this.zzhpi.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            zzbq.checkArgument(!dataSet.getDataPoints().isEmpty(), "No data points specified in the input data set.");
            this.zzhpi.add(dataSource);
            this.zzhib.add(dataSet);
            return this;
        }

        public SessionInsertRequest build() {
            boolean z = true;
            zzbq.zza(this.zzhhs != null, (Object) "Must specify a valid session.");
            if (this.zzhhs.getEndTime(TimeUnit.MILLISECONDS) == 0) {
                z = false;
            }
            zzbq.zza(z, (Object) "Must specify a valid end time, cannot insert a continuing session.");
            for (DataSet dataPoints : this.zzhib) {
                for (DataPoint zzd : dataPoints.getDataPoints()) {
                    zzd(zzd);
                }
            }
            for (DataPoint zzd2 : this.zzhph) {
                zzd(zzd2);
            }
            return new SessionInsertRequest(this);
        }

        public Builder setSession(Session session) {
            this.zzhhs = session;
            return this;
        }
    }

    SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, IBinder iBinder) {
        this.zzhhs = session;
        this.zzhib = Collections.unmodifiableList(list);
        this.zzhph = Collections.unmodifiableList(list2);
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    private SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, zzbzt zzbzt) {
        this.zzhhs = session;
        this.zzhib = Collections.unmodifiableList(list);
        this.zzhph = Collections.unmodifiableList(list2);
        this.zzhnu = zzbzt;
    }

    private SessionInsertRequest(Builder builder) {
        this(builder.zzhhs, (List<DataSet>) builder.zzhib, (List<DataPoint>) builder.zzhph, (zzbzt) null);
    }

    public SessionInsertRequest(SessionInsertRequest sessionInsertRequest, zzbzt zzbzt) {
        this(sessionInsertRequest.zzhhs, sessionInsertRequest.zzhib, sessionInsertRequest.zzhph, zzbzt);
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof SessionInsertRequest) {
                SessionInsertRequest sessionInsertRequest = (SessionInsertRequest) obj;
                if (zzbg.equal(this.zzhhs, sessionInsertRequest.zzhhs) && zzbg.equal(this.zzhib, sessionInsertRequest.zzhib) && zzbg.equal(this.zzhph, sessionInsertRequest.zzhph)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<DataPoint> getAggregateDataPoints() {
        return this.zzhph;
    }

    public List<DataSet> getDataSets() {
        return this.zzhib;
    }

    public Session getSession() {
        return this.zzhhs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhs, this.zzhib, this.zzhph});
    }

    public String toString() {
        return zzbg.zzx(this).zzg(SessionID.ELEMENT_NAME, this.zzhhs).zzg("dataSets", this.zzhib).zzg("aggregateDataPoints", this.zzhph).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getSession(), i, false);
        zzbgo.zzc(parcel, 2, getDataSets(), false);
        zzbgo.zzc(parcel, 3, getAggregateDataPoints(), false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 4, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
