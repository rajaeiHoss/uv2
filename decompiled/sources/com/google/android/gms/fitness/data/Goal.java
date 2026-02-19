package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzfmk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Goal extends zzbgl {
    public static final Parcelable.Creator<Goal> CREATOR = new zzs();
    public static final int OBJECTIVE_TYPE_DURATION = 2;
    public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
    public static final int OBJECTIVE_TYPE_METRIC = 1;
    private final long zzhky;
    private final long zzhkz;
    private final List<Integer> zzhla;
    private final Recurrence zzhlb;
    private final int zzhlc;
    private final MetricObjective zzhld;
    private final DurationObjective zzhle;
    private final FrequencyObjective zzhlf;

    public static class DurationObjective extends zzbgl {
        public static final Parcelable.Creator<DurationObjective> CREATOR = new zzp();
        private final long zzhlg;

        DurationObjective(long j) {
            this.zzhlg = j;
        }

        public DurationObjective(long j, TimeUnit timeUnit) {
            this(timeUnit.toNanos(j));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof DurationObjective) && this.zzhlg == ((DurationObjective) obj).zzhlg;
        }

        public long getDuration(TimeUnit timeUnit) {
            return timeUnit.convert(this.zzhlg, TimeUnit.NANOSECONDS);
        }

        public int hashCode() {
            return (int) this.zzhlg;
        }

        public String toString() {
            return zzbg.zzx(this).zzg("duration", Long.valueOf(this.zzhlg)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbgo.zze(parcel);
            zzbgo.zza(parcel, 1, this.zzhlg);
            zzbgo.zzai(parcel, zze);
        }
    }

    public static class FrequencyObjective extends zzbgl {
        public static final Parcelable.Creator<FrequencyObjective> CREATOR = new zzr();
        private final int frequency;

        public FrequencyObjective(int i) {
            this.frequency = i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof FrequencyObjective) && this.frequency == ((FrequencyObjective) obj).frequency;
        }

        public int getFrequency() {
            return this.frequency;
        }

        public int hashCode() {
            return this.frequency;
        }

        public String toString() {
            return zzbg.zzx(this).zzg("frequency", Integer.valueOf(this.frequency)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbgo.zze(parcel);
            zzbgo.zzc(parcel, 1, getFrequency());
            zzbgo.zzai(parcel, zze);
        }
    }

    public static class MetricObjective extends zzbgl {
        public static final Parcelable.Creator<MetricObjective> CREATOR = new zzx();
        private final double value;
        private final String zzhlh;
        private final double zzhli;

        public MetricObjective(String str, double d) {
            this(str, d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }

        public MetricObjective(String str, double d, double d2) {
            this.zzhlh = str;
            this.value = d;
            this.zzhli = d2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricObjective)) {
                return false;
            }
            MetricObjective metricObjective = (MetricObjective) obj;
            return zzbg.equal(this.zzhlh, metricObjective.zzhlh) && this.value == metricObjective.value && this.zzhli == metricObjective.zzhli;
        }

        public String getDataTypeName() {
            return this.zzhlh;
        }

        public double getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.zzhlh.hashCode();
        }

        public String toString() {
            return zzbg.zzx(this).zzg("dataTypeName", this.zzhlh).zzg(FirebaseAnalytics.Param.VALUE, Double.valueOf(this.value)).zzg("initialValue", Double.valueOf(this.zzhli)).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbgo.zze(parcel);
            zzbgo.zza(parcel, 1, getDataTypeName(), false);
            zzbgo.zza(parcel, 2, getValue());
            zzbgo.zza(parcel, 3, this.zzhli);
            zzbgo.zzai(parcel, zze);
        }
    }

    public static class MismatchedGoalException extends IllegalStateException {
        public MismatchedGoalException(String str) {
            super(str);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectiveType {
    }

    public static class Recurrence extends zzbgl {
        public static final Parcelable.Creator<Recurrence> CREATOR = new zzab();
        public static final int UNIT_DAY = 1;
        public static final int UNIT_MONTH = 3;
        public static final int UNIT_WEEK = 2;
        private final int count;
        /* access modifiers changed from: private */
        public final int zzhlj;

        @Retention(RetentionPolicy.SOURCE)
        public @interface RecurrenceUnit {
        }

        public Recurrence(int i, int i2) {
            this.count = i;
            zzbq.checkState(i2 > 0 && i2 <= 3);
            this.zzhlj = i2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            return this.count == recurrence.count && this.zzhlj == recurrence.zzhlj;
        }

        public int getCount() {
            return this.count;
        }

        public int getUnit() {
            return this.zzhlj;
        }

        public int hashCode() {
            return this.zzhlj;
        }

        public String toString() {
            String str;
            zzbi zzg = zzbg.zzx(this).zzg("count", Integer.valueOf(this.count));
            int i = this.zzhlj;
            if (i == 1) {
                str = "day";
            } else if (i == 2) {
                str = "week";
            } else if (i == 3) {
                str = "month";
            } else {
                throw new IllegalArgumentException("invalid unit value");
            }
            return zzg.zzg("unit", str).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            int zze = zzbgo.zze(parcel);
            zzbgo.zzc(parcel, 1, getCount());
            zzbgo.zzc(parcel, 2, getUnit());
            zzbgo.zzai(parcel, zze);
        }
    }

    Goal(long j, long j2, List<Integer> list, Recurrence recurrence, int i, MetricObjective metricObjective, DurationObjective durationObjective, FrequencyObjective frequencyObjective) {
        this.zzhky = j;
        this.zzhkz = j2;
        this.zzhla = list;
        this.zzhlb = recurrence;
        this.zzhlc = i;
        this.zzhld = metricObjective;
        this.zzhle = durationObjective;
        this.zzhlf = frequencyObjective;
    }

    private static String zzde(int i) {
        if (i == 0) {
            return "unknown";
        }
        if (i == 1) {
            return "metric";
        }
        if (i == 2) {
            return "duration";
        }
        if (i == 3) {
            return "frequency";
        }
        throw new IllegalArgumentException("invalid objective type value");
    }

    private final void zzdf(int i) throws MismatchedGoalException {
        if (i != this.zzhlc) {
            throw new MismatchedGoalException(String.format("%s goal does not have %s objective", new Object[]{zzde(this.zzhlc), zzde(i)}));
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) obj;
        return this.zzhky == goal.zzhky && this.zzhkz == goal.zzhkz && zzbg.equal(this.zzhla, goal.zzhla) && zzbg.equal(this.zzhlb, goal.zzhlb) && this.zzhlc == goal.zzhlc && zzbg.equal(this.zzhld, goal.zzhld) && zzbg.equal(this.zzhle, goal.zzhle) && zzbg.equal(this.zzhlf, goal.zzhlf);
    }

    public String getActivityName() {
        if (this.zzhla.isEmpty() || this.zzhla.size() > 1) {
            return null;
        }
        return zzfmk.getName(this.zzhla.get(0).intValue());
    }

    public long getCreateTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzhky, TimeUnit.NANOSECONDS);
    }

    public DurationObjective getDurationObjective() {
        zzdf(2);
        return this.zzhle;
    }

    public long getEndTime(Calendar calendar, TimeUnit timeUnit) {
        long j;
        TimeUnit timeUnit2;
        if (this.zzhlb != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(calendar.getTime());
            int zza = this.zzhlb.zzhlj;
            if (zza == 1) {
                instance.add(5, 1);
            } else if (zza == 2) {
                instance.add(4, 1);
                instance.set(7, 2);
            } else if (zza == 3) {
                instance.add(2, 1);
                instance.set(5, 1);
            } else {
                int zza2 = this.zzhlb.zzhlj;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Invalid unit ");
                sb.append(zza2);
                throw new IllegalArgumentException(sb.toString());
            }
            instance.set(11, 0);
            j = instance.getTimeInMillis();
            timeUnit2 = TimeUnit.MILLISECONDS;
        } else {
            j = this.zzhkz;
            timeUnit2 = TimeUnit.NANOSECONDS;
        }
        return timeUnit.convert(j, timeUnit2);
    }

    public FrequencyObjective getFrequencyObjective() {
        zzdf(3);
        return this.zzhlf;
    }

    public MetricObjective getMetricObjective() {
        zzdf(1);
        return this.zzhld;
    }

    public int getObjectiveType() {
        return this.zzhlc;
    }

    public Recurrence getRecurrence() {
        return this.zzhlb;
    }

    public long getStartTime(Calendar calendar, TimeUnit timeUnit) {
        long j;
        TimeUnit timeUnit2;
        if (this.zzhlb != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(calendar.getTime());
            int zza = this.zzhlb.zzhlj;
            if (zza != 1) {
                if (zza == 2) {
                    instance.set(7, 2);
                } else if (zza == 3) {
                    instance.set(5, 1);
                } else {
                    int zza2 = this.zzhlb.zzhlj;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Invalid unit ");
                    sb.append(zza2);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            instance.set(11, 0);
            j = instance.getTimeInMillis();
            timeUnit2 = TimeUnit.MILLISECONDS;
        } else {
            j = this.zzhky;
            timeUnit2 = TimeUnit.NANOSECONDS;
        }
        return timeUnit.convert(j, timeUnit2);
    }

    public int hashCode() {
        return this.zzhlc;
    }

    public String toString() {
        return zzbg.zzx(this).zzg("activity", getActivityName()).zzg("recurrence", this.zzhlb).zzg("metricObjective", this.zzhld).zzg("durationObjective", this.zzhle).zzg("frequencyObjective", this.zzhlf).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhky);
        zzbgo.zza(parcel, 2, this.zzhkz);
        zzbgo.zzd(parcel, 3, this.zzhla, false);
        zzbgo.zza(parcel, 4, (Parcelable) getRecurrence(), i, false);
        zzbgo.zzc(parcel, 5, getObjectiveType());
        zzbgo.zza(parcel, 6, (Parcelable) this.zzhld, i, false);
        zzbgo.zza(parcel, 7, (Parcelable) this.zzhle, i, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzhlf, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
