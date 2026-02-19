package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    private final DataType zzhhj;
    private final DataSource zzhhk;
    private final long zzhlr;
    private final int zzhls;
    private final long zzhpa;
    private final long zzhpb;
    private final long zzhpf;

    public static class Builder {
        /* access modifiers changed from: private */
        public DataType zzhhj;
        /* access modifiers changed from: private */
        public DataSource zzhhk;
        /* access modifiers changed from: private */
        public long zzhlr = -1;
        /* access modifiers changed from: private */
        public int zzhls = 2;
        /* access modifiers changed from: private */
        public long zzhpa = 0;
        /* access modifiers changed from: private */
        public long zzhpb = 0;
        /* access modifiers changed from: private */
        public long zzhpf = LongCompanionObject.MAX_VALUE;
        private boolean zzhpg = false;

        public SensorRequest build() {
            DataSource dataSource;
            boolean z = false;
            zzbq.zza((this.zzhhk == null && this.zzhhj == null) ? false : true, (Object) "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzhhj;
            if (dataType == null || (dataSource = this.zzhhk) == null || dataType.equals(dataSource.getDataType())) {
                z = true;
            }
            zzbq.zza(z, (Object) "Specified data type is incompatible with specified data source");
            return new SensorRequest(this);
        }

        public Builder setAccuracyMode(int i) {
            if (!(i == 1 || i == 3)) {
                i = 2;
            }
            this.zzhls = i;
            return this;
        }

        public Builder setDataSource(DataSource dataSource) {
            this.zzhhk = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.zzhhj = dataType;
            return this;
        }

        public Builder setFastestRate(int i, TimeUnit timeUnit) {
            zzbq.checkArgument(i >= 0, "Cannot use a negative interval");
            this.zzhpg = true;
            this.zzhpb = timeUnit.toMicros((long) i);
            return this;
        }

        public Builder setMaxDeliveryLatency(int i, TimeUnit timeUnit) {
            zzbq.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.zzhpa = timeUnit.toMicros((long) i);
            return this;
        }

        public Builder setSamplingRate(long j, TimeUnit timeUnit) {
            zzbq.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            long micros = timeUnit.toMicros(j);
            this.zzhlr = micros;
            if (!this.zzhpg) {
                this.zzhpb = micros / 2;
            }
            return this;
        }

        public Builder setTimeout(long j, TimeUnit timeUnit) {
            boolean z = true;
            zzbq.zzb(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            if (timeUnit == null) {
                z = false;
            }
            zzbq.checkArgument(z, "Invalid time unit specified");
            this.zzhpf = timeUnit.toMicros(j);
            return this;
        }
    }

    private SensorRequest(DataSource dataSource, LocationRequest locationRequest) {
        long micros = TimeUnit.MILLISECONDS.toMicros(locationRequest.getInterval());
        this.zzhlr = micros;
        this.zzhpb = TimeUnit.MILLISECONDS.toMicros(locationRequest.getFastestInterval());
        this.zzhpa = micros;
        this.zzhhj = dataSource.getDataType();
        int priority = locationRequest.getPriority();
        this.zzhls = priority != 100 ? priority != 104 ? 2 : 1 : 3;
        this.zzhhk = dataSource;
        long expirationTime = locationRequest.getExpirationTime();
        if (expirationTime == LongCompanionObject.MAX_VALUE) {
            this.zzhpf = LongCompanionObject.MAX_VALUE;
        } else {
            this.zzhpf = TimeUnit.MILLISECONDS.toMicros(expirationTime - SystemClock.elapsedRealtime());
        }
    }

    private SensorRequest(Builder builder) {
        this.zzhhk = builder.zzhhk;
        this.zzhhj = builder.zzhhj;
        this.zzhlr = builder.zzhlr;
        this.zzhpb = builder.zzhpb;
        this.zzhpa = builder.zzhpa;
        this.zzhls = builder.zzhls;
        this.zzhpf = builder.zzhpf;
    }

    public static SensorRequest fromLocationRequest(DataSource dataSource, LocationRequest locationRequest) {
        return new SensorRequest(dataSource, locationRequest);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof SensorRequest) {
                SensorRequest sensorRequest = (SensorRequest) obj;
                if (zzbg.equal(this.zzhhk, sensorRequest.zzhhk) && zzbg.equal(this.zzhhj, sensorRequest.zzhhj) && this.zzhlr == sensorRequest.zzhlr && this.zzhpb == sensorRequest.zzhpb && this.zzhpa == sensorRequest.zzhpa && this.zzhls == sensorRequest.zzhls && this.zzhpf == sensorRequest.zzhpf) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int getAccuracyMode() {
        return this.zzhls;
    }

    public DataSource getDataSource() {
        return this.zzhhk;
    }

    public DataType getDataType() {
        return this.zzhhj;
    }

    public long getFastestRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhpb, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhpa, TimeUnit.MICROSECONDS);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhlr, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk, this.zzhhj, Long.valueOf(this.zzhlr), Long.valueOf(this.zzhpb), Long.valueOf(this.zzhpa), Integer.valueOf(this.zzhls), Long.valueOf(this.zzhpf)});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("dataSource", this.zzhhk).zzg("dataType", this.zzhhj).zzg("samplingRateMicros", Long.valueOf(this.zzhlr)).zzg("deliveryLatencyMicros", Long.valueOf(this.zzhpa)).zzg("timeOutMicros", Long.valueOf(this.zzhpf)).toString();
    }

    public final long zzasi() {
        return this.zzhpf;
    }
}
