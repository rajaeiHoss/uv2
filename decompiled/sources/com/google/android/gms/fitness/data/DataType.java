package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class DataType extends zzbgl implements ReflectedParcelable {
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    @Deprecated
    public static final DataType AGGREGATE_CALORIES_CONSUMED;
    public static final DataType AGGREGATE_CALORIES_EXPENDED;
    public static final DataType AGGREGATE_DISTANCE_DELTA;
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_HYDRATION;
    @Deprecated
    public static final Set<DataType> AGGREGATE_INPUT_TYPES;
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
    public static final DataType AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE);
    public static final DataType AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType AGGREGATE_STEP_COUNT_DELTA;
    public static final DataType AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final Parcelable.Creator<DataType> CREATOR = new zzl();
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    @Deprecated
    public static final DataType TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE);
    public static final DataType TYPE_ACTIVITY_SAMPLES = new DataType("com.google.activity.samples", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY_CONFIDENCE);
    public static final DataType TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY);
    public static final DataType TYPE_BASAL_METABOLIC_RATE;
    public static final DataType TYPE_BODY_FAT_PERCENTAGE;
    @Deprecated
    public static final DataType TYPE_CALORIES_CONSUMED;
    public static final DataType TYPE_CALORIES_EXPENDED;
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_REVOLUTIONS);
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_REVOLUTIONS);
    public static final DataType TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_RPM);
    public static final DataType TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    public static final DataType TYPE_DISTANCE_DELTA;
    public static final DataType TYPE_HEART_RATE_BPM;
    public static final DataType TYPE_HEIGHT = new DataType("com.google.height", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_HEIGHT);
    public static final DataType TYPE_HYDRATION;
    public static final DataType TYPE_LOCATION_SAMPLE;
    public static final DataType TYPE_LOCATION_TRACK = new DataType("com.google.location.track", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    public static final DataType TYPE_NUTRITION;
    public static final DataType TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_WATTS);
    public static final DataType TYPE_SPEED;
    public static final DataType TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    public static final DataType TYPE_STEP_COUNT_DELTA;
    public static final DataType TYPE_WEIGHT;
    public static final DataType TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.FIELD_DURATION, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);
    public static final DataType zzhiu = new DataType("com.google.step_length", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEP_LENGTH);
    public static final DataType zzhiv = new DataType("com.google.internal.goal", Field.zzhkb);
    public static final DataType zzhiw = new DataType("com.google.stride_model", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzhkc);
    public static final DataType zzhix = new DataType("com.google.floor_change", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE, Field.zzhkd, Field.zzhkg);
    public static final DataType zzhiy = new DataType("com.google.accelerometer", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzhkt, Field.zza.zzhku, Field.zza.zzhkv);
    public static final DataType zzhiz = new DataType("com.google.sensor.events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzhkj, Field.zzhkl, Field.zzhkp);
    public static final DataType zzhja = new DataType("com.google.sensor.const_rate_events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzhkk, Field.zzhkm, Field.zzhkn, Field.zzhko, Field.zzhkp);
    public static final DataType zzhjb = new DataType("com.google.moves", Field.zzhjx);
    public static final DataType zzhjc;
    public static final DataType zzhjd;
    public static final DataType zzhje = new DataType("com.google.active_minutes", Field.zzhjx);
    public static final DataType zzhjf = new DataType("com.google.device_on_body", Field.zzhkr);
    public static final DataType zzhjg = new DataType("com.google.floor_change.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzhjz, Field.zzhka, Field.zzhke, Field.zzhkf, Field.zzhkh, Field.zzhki);
    public static final DataType zzhjh = new DataType("com.google.heart_minutes", Field.zzhkq);
    public static final DataType zzhji = new DataType("com.google.body.hip.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    public static final DataType zzhjj = new DataType("com.google.body.waist.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
    private final String name;
    private final List<Field> zzhjk;
    private final String zzhjl;
    private final String zzhjm;

    public static final class zza {
        public static final DataType zzhjn = new DataType("com.google.internal.session.debug", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzhkw);
        public static final DataType zzhjo = new DataType("com.google.internal.session.v2", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzhkx);
    }

    static {
        DataType dataType = new DataType("com.google.step_count.delta", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
        TYPE_STEP_COUNT_DELTA = dataType;
        DataType dataType2 = new DataType("com.google.calories.consumed", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        TYPE_CALORIES_CONSUMED = dataType2;
        DataType dataType3 = new DataType("com.google.calories.expended", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        TYPE_CALORIES_EXPENDED = dataType3;
        DataType dataType4 = new DataType("com.google.calories.bmr", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        TYPE_BASAL_METABOLIC_RATE = dataType4;
        DataType dataType5 = new DataType("com.google.heart_rate.bpm", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ, Field.FIELD_BPM);
        TYPE_HEART_RATE_BPM = dataType5;
        DataType dataType6 = new DataType("com.google.location.sample", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
        TYPE_LOCATION_SAMPLE = dataType6;
        DataType dataType7 = new DataType("com.google.distance.delta", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
        TYPE_DISTANCE_DELTA = dataType7;
        DataType dataType8 = new DataType("com.google.speed", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_SPEED);
        TYPE_SPEED = dataType8;
        DataType dataType9 = new DataType("com.google.weight", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_WEIGHT);
        TYPE_WEIGHT = dataType9;
        DataType dataType10 = dataType9;
        DataType dataType11 = new DataType("com.google.body.fat.percentage", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_PERCENTAGE);
        TYPE_BODY_FAT_PERCENTAGE = dataType11;
        DataType dataType12 = dataType5;
        DataType dataType13 = new DataType("com.google.body.waist.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
        zzhjc = dataType13;
        DataType dataType14 = dataType6;
        DataType dataType15 = new DataType("com.google.body.hip.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
        zzhjd = dataType15;
        DataType dataType16 = dataType13;
        DataType dataType17 = new DataType("com.google.nutrition", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM);
        TYPE_NUTRITION = dataType17;
        DataType dataType18 = dataType17;
        DataType dataType19 = new DataType("com.google.hydration", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_VOLUME);
        TYPE_HYDRATION = dataType19;
        DataType dataType20 = dataType4;
        DataType dataType21 = dataType19;
        AGGREGATE_STEP_COUNT_DELTA = dataType;
        AGGREGATE_DISTANCE_DELTA = dataType7;
        AGGREGATE_CALORIES_CONSUMED = dataType2;
        AGGREGATE_CALORIES_EXPENDED = dataType3;
        AGGREGATE_HYDRATION = dataType21;
        ArraySet arraySet = new ArraySet();
        AGGREGATE_INPUT_TYPES = arraySet;
        arraySet.add(TYPE_ACTIVITY_SEGMENT);
        arraySet.add(dataType20);
        arraySet.add(dataType11);
        arraySet.add(dataType15);
        arraySet.add(dataType16);
        arraySet.add(TYPE_CALORIES_CONSUMED);
        arraySet.add(TYPE_CALORIES_EXPENDED);
        arraySet.add(dataType7);
        arraySet.add(zzhix);
        arraySet.add(dataType14);
        arraySet.add(dataType18);
        arraySet.add(dataType21);
        arraySet.add(dataType12);
        arraySet.add(TYPE_POWER_SAMPLE);
        arraySet.add(dataType8);
        arraySet.add(TYPE_STEP_COUNT_DELTA);
        arraySet.add(dataType10);
    }

    public DataType(String str, String str2, String str3, Field... fieldArr) {
        this(str, (List<Field>) Arrays.asList(fieldArr), str2, str3);
    }

    DataType(String str, List<Field> list, String str2, String str3) {
        this.name = str;
        this.zzhjk = Collections.unmodifiableList(list);
        this.zzhjl = str2;
        this.zzhjm = str3;
    }

    private DataType(String str, Field... fieldArr) {
        this(str, (List<Field>) Arrays.asList(fieldArr), (String) null, (String) null);
    }

    public static List<DataType> getAggregatesForInput(DataType dataType) {
        List list = com.google.android.gms.fitness.data.zza.zzhhv.get(dataType);
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public static String getMimeType(DataType dataType) {
        String valueOf = String.valueOf(dataType.getName());
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        return this.name.equals(dataType.name) && this.zzhjk.equals(dataType.zzhjk);
    }

    public final List<Field> getFields() {
        return this.zzhjk;
    }

    public final String getName() {
        return this.name;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final int indexOf(Field field) {
        int indexOf = this.zzhjk.indexOf(field);
        zzbq.zzb(indexOf >= 0, "%s not a field of %s", field, this);
        return indexOf;
    }

    public final String toString() {
        return String.format("DataType{%s%s}", new Object[]{this.name, this.zzhjk});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getName(), false);
        zzbgo.zzc(parcel, 2, getFields(), false);
        zzbgo.zza(parcel, 3, this.zzhjl, false);
        zzbgo.zza(parcel, 4, this.zzhjm, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzarw() {
        return this.zzhjl;
    }

    public final String zzarx() {
        return this.zzhjm;
    }

    public final String zzary() {
        return this.name.startsWith("com.google.") ? this.name.substring(11) : this.name;
    }
}
