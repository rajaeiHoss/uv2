package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import kotlin.text.Typography;
import org.jivesoftware.smackx.GroupChatInvitation;

public final class Field extends zzbgl {
    public static final Parcelable.Creator<Field> CREATOR = new zzq();
    public static final Field FIELD_ACCURACY = zzhq("accuracy");
    public static final Field FIELD_ACTIVITY = zzho("activity");
    public static final Field FIELD_ACTIVITY_CONFIDENCE = zzhs("activity_confidence");
    public static final Field FIELD_ALTITUDE = new Field("altitude", 2, true);
    public static final Field FIELD_AVERAGE = zzhq("average");
    public static final Field FIELD_BPM = zzhq("bpm");
    public static final Field FIELD_CALORIES = zzhq("calories");
    public static final Field FIELD_CIRCUMFERENCE = zzhq("circumference");
    public static final Field FIELD_CONFIDENCE = zzhq("confidence");
    public static final Field FIELD_DISTANCE = zzhq("distance");
    public static final Field FIELD_DURATION = zzho("duration");
    public static final Field FIELD_EXERCISE = zzhr("exercise");
    public static final Field FIELD_FOOD_ITEM = zzhr("food_item");
    public static final Field FIELD_HEIGHT = zzhq("height");
    public static final Field FIELD_HIGH_LATITUDE = zzhq("high_latitude");
    public static final Field FIELD_HIGH_LONGITUDE = zzhq("high_longitude");
    public static final Field FIELD_LATITUDE = zzhq("latitude");
    public static final Field FIELD_LONGITUDE = zzhq("longitude");
    public static final Field FIELD_LOW_LATITUDE = zzhq("low_latitude");
    public static final Field FIELD_LOW_LONGITUDE = zzhq("low_longitude");
    public static final Field FIELD_MAX = zzhq("max");
    public static final Field FIELD_MEAL_TYPE = zzho("meal_type");
    public static final Field FIELD_MIN = zzhq("min");
    public static final Field FIELD_NUM_SEGMENTS = zzho("num_segments");
    public static final Field FIELD_NUTRIENTS = zzhs("nutrients");
    public static final Field FIELD_OCCURRENCES = zzho("occurrences");
    public static final Field FIELD_PERCENTAGE = zzhq("percentage");
    public static final Field FIELD_REPETITIONS = zzho("repetitions");
    public static final Field FIELD_RESISTANCE = zzhq("resistance");
    public static final Field FIELD_RESISTANCE_TYPE = zzho("resistance_type");
    public static final Field FIELD_REVOLUTIONS = zzho("revolutions");
    public static final Field FIELD_RPM = zzhq("rpm");
    public static final Field FIELD_SPEED = zzhq("speed");
    public static final Field FIELD_STEPS = zzho("steps");
    public static final Field FIELD_STEP_LENGTH = zzhq("step_length");
    public static final Field FIELD_VOLUME = zzhq("volume");
    public static final Field FIELD_WATTS = zzhq("watts");
    public static final Field FIELD_WEIGHT = zzhq("weight");
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    public static final String NUTRIENT_CALCIUM = "calcium";
    public static final String NUTRIENT_CALORIES = "calories";
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    public static final String NUTRIENT_IRON = "iron";
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    public static final String NUTRIENT_POTASSIUM = "potassium";
    public static final String NUTRIENT_PROTEIN = "protein";
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    public static final String NUTRIENT_SODIUM = "sodium";
    public static final String NUTRIENT_SUGAR = "sugar";
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    public static final Field zzhjx = zzhp("duration");
    private static Field zzhjy = zzhs("activity_duration");
    public static final Field zzhjz = zzhs("activity_duration.ascending");
    public static final Field zzhka = zzhs("activity_duration.descending");
    public static final Field zzhkb = zzht("google.android.fitness.GoalV2");
    public static final Field zzhkc = zzht("google.android.fitness.StrideModel");
    public static final Field zzhkd = zzhq("elevation.change");
    public static final Field zzhke = zzhs("elevation.gain");
    public static final Field zzhkf = zzhs("elevation.loss");
    public static final Field zzhkg = zzhq("floors");
    public static final Field zzhkh = zzhs("floor.gain");
    public static final Field zzhki = zzhs("floor.loss");
    public static final Field zzhkj = zzho("sensor_type");
    public static final Field zzhkk = zzho("sensor_types");
    public static final Field zzhkl = new Field("timestamps", 5);
    public static final Field zzhkm = zzho("sample_period");
    public static final Field zzhkn = zzho("num_samples");
    public static final Field zzhko = zzho("num_dimensions");
    public static final Field zzhkp = new Field("sensor_values", 6);
    public static final Field zzhkq = zzhq("intensity");
    public static final Field zzhkr = zzhq("probability");
    private final int format;
    private final String name;
    private final Boolean zzhks;

    public static class zza {
        public static final Field zzhkt = Field.zzhq(GroupChatInvitation.ELEMENT_NAME);
        public static final Field zzhku = Field.zzhq("y");
        public static final Field zzhkv = Field.zzhq("z");
        public static final Field zzhkw = Field.zzhu("debug_session");
        public static final Field zzhkx = Field.zzhu("google.android.fitness.SessionV2");
    }

    private Field(String str, int i) {
        this(str, i, (Boolean) null);
    }

    Field(String str, int i, Boolean bool) {
        this.name = (String) zzbq.checkNotNull(str);
        this.format = i;
        this.zzhks = bool;
    }

    private static Field zzho(String str) {
        return new Field(str, 1);
    }

    static Field zzhp(String str) {
        return new Field(str, 1, true);
    }

    static Field zzhq(String str) {
        return new Field(str, 2);
    }

    private static Field zzhr(String str) {
        return new Field(str, 3);
    }

    private static Field zzhs(String str) {
        return new Field(str, 4);
    }

    private static Field zzht(String str) {
        return new Field(str, 7);
    }

    static Field zzhu(String str) {
        return new Field(str, 7, true);
    }

    public static Field zzo(String str, int i) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2131707655:
                if (str.equals("accuracy")) {
                    c = 0;
                    break;
                }
                break;
            case -2083865430:
                if (str.equals("debug_session")) {
                    c = 1;
                    break;
                }
                break;
            case -2006370880:
                if (str.equals("body_temperature_measurement_location")) {
                    c = 2;
                    break;
                }
                break;
            case -1992012396:
                if (str.equals("duration")) {
                    c = 3;
                    break;
                }
                break;
            case -1859447186:
                if (str.equals("blood_glucose_level")) {
                    c = 4;
                    break;
                }
                break;
            case -1655966961:
                if (str.equals("activity")) {
                    c = 5;
                    break;
                }
                break;
            case -1595712862:
                if (str.equals("cervical_dilation")) {
                    c = 6;
                    break;
                }
                break;
            case -1579612127:
                if (str.equals("floor.gain")) {
                    c = 7;
                    break;
                }
                break;
            case -1579449403:
                if (str.equals("floor.loss")) {
                    c = 8;
                    break;
                }
                break;
            case -1569430471:
                if (str.equals("num_segments")) {
                    c = 9;
                    break;
                }
                break;
            case -1531570079:
                if (str.equals("elevation.change")) {
                    c = 10;
                    break;
                }
                break;
            case -1440707631:
                if (str.equals("oxygen_saturation")) {
                    c = 11;
                    break;
                }
                break;
            case -1439978388:
                if (str.equals("latitude")) {
                    c = 12;
                    break;
                }
                break;
            case -1352492506:
                if (str.equals("num_dimensions")) {
                    c = 13;
                    break;
                }
                break;
            case -1290561483:
                if (str.equals("probability")) {
                    c = 14;
                    break;
                }
                break;
            case -1271636505:
                if (str.equals("floors")) {
                    c = 15;
                    break;
                }
                break;
            case -1248595573:
                if (str.equals("supplemental_oxygen_flow_rate_average")) {
                    c = 16;
                    break;
                }
                break;
            case -1221029593:
                if (str.equals("height")) {
                    c = 17;
                    break;
                }
                break;
            case -1220952307:
                if (str.equals("blood_pressure_measurement_location")) {
                    c = 18;
                    break;
                }
                break;
            case -1133736764:
                if (str.equals("activity_duration")) {
                    c = 19;
                    break;
                }
                break;
            case -1129337776:
                if (str.equals("num_samples")) {
                    c = 20;
                    break;
                }
                break;
            case -1110756780:
                if (str.equals("food_item")) {
                    c = 21;
                    break;
                }
                break;
            case -921832806:
                if (str.equals("percentage")) {
                    c = 22;
                    break;
                }
                break;
            case -918978307:
                if (str.equals("cervical_position")) {
                    c = 23;
                    break;
                }
                break;
            case -810883302:
                if (str.equals("volume")) {
                    c = 24;
                    break;
                }
                break;
            case -803244749:
                if (str.equals("blood_pressure_systolic")) {
                    c = 25;
                    break;
                }
                break;
            case -791592328:
                if (str.equals("weight")) {
                    c = 26;
                    break;
                }
                break;
            case -631448035:
                if (str.equals("average")) {
                    c = 27;
                    break;
                }
                break;
            case -626344110:
                if (str.equals("high_longitude")) {
                    c = 28;
                    break;
                }
                break;
            case -619868540:
                if (str.equals("low_longitude")) {
                    c = 29;
                    break;
                }
                break;
            case -511934137:
                if (str.equals("sensor_values")) {
                    c = 30;
                    break;
                }
                break;
            case -494782871:
                if (str.equals("high_latitude")) {
                    c = 31;
                    break;
                }
                break;
            case -452643911:
                if (str.equals("step_length")) {
                    c = ' ';
                    break;
                }
                break;
            case -437053898:
                if (str.equals("meal_type")) {
                    c = '!';
                    break;
                }
                break;
            case -277306353:
                if (str.equals("circumference")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -266093204:
                if (str.equals("nutrients")) {
                    c = '#';
                    break;
                }
                break;
            case -228366862:
                if (str.equals("oxygen_saturation_measurement_method")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -168965370:
                if (str.equals(NUTRIENT_CALORIES)) {
                    c = '%';
                    break;
                }
                break;
            case -126538880:
                if (str.equals("resistance_type")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case -28590302:
                if (str.equals("ovulation_test_result")) {
                    c = '\'';
                    break;
                }
                break;
            case 120:
                if (str.equals(GroupChatInvitation.ELEMENT_NAME)) {
                    c = '(';
                    break;
                }
                break;
            case 121:
                if (str.equals("y")) {
                    c = ')';
                    break;
                }
                break;
            case 122:
                if (str.equals("z")) {
                    c = '*';
                    break;
                }
                break;
            case 97759:
                if (str.equals("bpm")) {
                    c = '+';
                    break;
                }
                break;
            case 107876:
                if (str.equals("max")) {
                    c = ',';
                    break;
                }
                break;
            case 108114:
                if (str.equals("min")) {
                    c = '-';
                    break;
                }
                break;
            case 113135:
                if (str.equals("rpm")) {
                    c = '.';
                    break;
                }
                break;
            case 66639641:
                if (str.equals("temporal_relation_to_sleep")) {
                    c = '/';
                    break;
                }
                break;
            case 109641799:
                if (str.equals("speed")) {
                    c = '0';
                    break;
                }
                break;
            case 109761319:
                if (str.equals("steps")) {
                    c = '1';
                    break;
                }
                break;
            case 112903913:
                if (str.equals("watts")) {
                    c = '2';
                    break;
                }
                break;
            case 120904628:
                if (str.equals("sensor_types")) {
                    c = '3';
                    break;
                }
                break;
            case 137365935:
                if (str.equals("longitude")) {
                    c = '4';
                    break;
                }
                break;
            case 198162679:
                if (str.equals("low_latitude")) {
                    c = '5';
                    break;
                }
                break;
            case 220648413:
                if (str.equals("blood_pressure_diastolic_average")) {
                    c = '6';
                    break;
                }
                break;
            case 248891292:
                if (str.equals("blood_glucose_specimen_source")) {
                    c = '7';
                    break;
                }
                break;
            case 286612066:
                if (str.equals("activity_duration.descending")) {
                    c = '8';
                    break;
                }
                break;
            case 288459765:
                if (str.equals("distance")) {
                    c = '9';
                    break;
                }
                break;
            case 306600408:
                if (str.equals("google.android.fitness.SessionV2")) {
                    c = ':';
                    break;
                }
                break;
            case 320627489:
                if (str.equals("cervical_mucus_texture")) {
                    c = ';';
                    break;
                }
                break;
            case 455965230:
                if (str.equals("activity_duration.ascending")) {
                    c = Typography.less;
                    break;
                }
                break;
            case 475560024:
                if (str.equals("blood_pressure_systolic_max")) {
                    c = '=';
                    break;
                }
                break;
            case 475560262:
                if (str.equals("blood_pressure_systolic_min")) {
                    c = Typography.greater;
                    break;
                }
                break;
            case 499324979:
                if (str.equals("intensity")) {
                    c = '?';
                    break;
                }
                break;
            case 514168969:
                if (str.equals("google.android.fitness.GoalV2")) {
                    c = '@';
                    break;
                }
                break;
            case 581888402:
                if (str.equals("cervical_mucus_amount")) {
                    c = 'A';
                    break;
                }
                break;
            case 623947695:
                if (str.equals("oxygen_saturation_average")) {
                    c = 'B';
                    break;
                }
                break;
            case 738210934:
                if (str.equals("google.android.fitness.StrideModel")) {
                    c = 'C';
                    break;
                }
                break;
            case 784486594:
                if (str.equals("occurrences")) {
                    c = 'D';
                    break;
                }
                break;
            case 811264586:
                if (str.equals("revolutions")) {
                    c = 'E';
                    break;
                }
                break;
            case 815736413:
                if (str.equals("oxygen_saturation_system")) {
                    c = 'F';
                    break;
                }
                break;
            case 829251210:
                if (str.equals("confidence")) {
                    c = 'G';
                    break;
                }
                break;
            case 833248065:
                if (str.equals("temporal_relation_to_meal")) {
                    c = 'H';
                    break;
                }
                break;
            case 883161687:
                if (str.equals("body_temperature")) {
                    c = 'I';
                    break;
                }
                break;
            case 984367650:
                if (str.equals("repetitions")) {
                    c = 'J';
                    break;
                }
                break;
            case 998412730:
                if (str.equals("activity_confidence")) {
                    c = 'K';
                    break;
                }
                break;
            case 1136011766:
                if (str.equals("sample_period")) {
                    c = 'L';
                    break;
                }
                break;
            case 1276952063:
                if (str.equals("blood_pressure_diastolic")) {
                    c = 'M';
                    break;
                }
                break;
            case 1284575222:
                if (str.equals("oxygen_saturation_max")) {
                    c = 'N';
                    break;
                }
                break;
            case 1284575460:
                if (str.equals("oxygen_saturation_min")) {
                    c = 'O';
                    break;
                }
                break;
            case 1403812644:
                if (str.equals("blood_pressure_diastolic_max")) {
                    c = 'P';
                    break;
                }
                break;
            case 1403812882:
                if (str.equals("blood_pressure_diastolic_min")) {
                    c = 'Q';
                    break;
                }
                break;
            case 1527920799:
                if (str.equals("sensor_type")) {
                    c = 'R';
                    break;
                }
                break;
            case 1708915229:
                if (str.equals("timestamps")) {
                    c = 'S';
                    break;
                }
                break;
            case 1857734768:
                if (str.equals("elevation.gain")) {
                    c = 'T';
                    break;
                }
                break;
            case 1857897492:
                if (str.equals("elevation.loss")) {
                    c = 'U';
                    break;
                }
                break;
            case 1863800889:
                if (str.equals("resistance")) {
                    c = 'V';
                    break;
                }
                break;
            case 1880897007:
                if (str.equals("oxygen_therapy_administration_mode")) {
                    c = 'W';
                    break;
                }
                break;
            case 1892583496:
                if (str.equals("menstrual_flow")) {
                    c = 'X';
                    break;
                }
                break;
            case 1958191058:
                if (str.equals("supplemental_oxygen_flow_rate_max")) {
                    c = 'Y';
                    break;
                }
                break;
            case 1958191296:
                if (str.equals("supplemental_oxygen_flow_rate_min")) {
                    c = 'Z';
                    break;
                }
                break;
            case 1983072038:
                if (str.equals("body_position")) {
                    c = '[';
                    break;
                }
                break;
            case 2020153105:
                if (str.equals("blood_pressure_systolic_average")) {
                    c = '\\';
                    break;
                }
                break;
            case 2036550306:
                if (str.equals("altitude")) {
                    c = ']';
                    break;
                }
                break;
            case 2056323544:
                if (str.equals("exercise")) {
                    c = '^';
                    break;
                }
                break;
            case 2072582505:
                if (str.equals("cervical_firmness")) {
                    c = '_';
                    break;
                }
                break;
            case 2078370221:
                if (str.equals("supplemental_oxygen_flow_rate")) {
                    c = '`';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return FIELD_ACCURACY;
            case 1:
                return zza.zzhkw;
            case 2:
                return HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION;
            case 3:
                return FIELD_DURATION;
            case 4:
                return HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL;
            case 5:
                return FIELD_ACTIVITY;
            case 6:
                return HealthFields.FIELD_CERVICAL_DILATION;
            case 7:
                return zzhkh;
            case 8:
                return zzhki;
            case 9:
                return FIELD_NUM_SEGMENTS;
            case 10:
                return zzhkd;
            case 11:
                return HealthFields.FIELD_OXYGEN_SATURATION;
            case 12:
                return FIELD_LATITUDE;
            case 13:
                return zzhko;
            case 14:
                return zzhkr;
            case 15:
                return zzhkg;
            case 16:
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE;
            case 17:
                return FIELD_HEIGHT;
            case 18:
                return HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION;
            case 19:
                return zzhjy;
            case 20:
                return zzhkn;
            case 21:
                return FIELD_FOOD_ITEM;
            case 22:
                return FIELD_PERCENTAGE;
            case 23:
                return HealthFields.FIELD_CERVICAL_POSITION;
            case 24:
                return FIELD_VOLUME;
            case 25:
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC;
            case 26:
                return FIELD_WEIGHT;
            case 27:
                return FIELD_AVERAGE;
            case 28:
                return FIELD_HIGH_LONGITUDE;
            case 29:
                return FIELD_LOW_LONGITUDE;
            case 30:
                return zzhkp;
            case 31:
                return FIELD_HIGH_LATITUDE;
            case ' ':
                return FIELD_STEP_LENGTH;
            case '!':
                return FIELD_MEAL_TYPE;
            case '\"':
                return FIELD_CIRCUMFERENCE;
            case '#':
                return FIELD_NUTRIENTS;
            case '$':
                return HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD;
            case '%':
                return FIELD_CALORIES;
            case '&':
                return FIELD_RESISTANCE_TYPE;
            case '\'':
                return HealthFields.FIELD_OVULATION_TEST_RESULT;
            case '(':
                return zza.zzhkt;
            case ')':
                return zza.zzhku;
            case '*':
                return zza.zzhkv;
            case '+':
                return FIELD_BPM;
            case ',':
                return FIELD_MAX;
            case '-':
                return FIELD_MIN;
            case '.':
                return FIELD_RPM;
            case '/':
                return HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP;
            case '0':
                return FIELD_SPEED;
            case '1':
                return FIELD_STEPS;
            case '2':
                return FIELD_WATTS;
            case '3':
                return zzhkk;
            case '4':
                return FIELD_LONGITUDE;
            case '5':
                return FIELD_LOW_LATITUDE;
            case '6':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE;
            case '7':
                return HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE;
            case '8':
                return zzhka;
            case '9':
                return FIELD_DISTANCE;
            case ':':
                return zza.zzhkx;
            case ';':
                return HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE;
            case '<':
                return zzhjz;
            case '=':
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX;
            case '>':
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN;
            case '?':
                return zzhkq;
            case '@':
                return zzhkb;
            case 'A':
                return HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT;
            case 'B':
                return HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE;
            case 'C':
                return zzhkc;
            case 'D':
                return FIELD_OCCURRENCES;
            case 'E':
                return FIELD_REVOLUTIONS;
            case 'F':
                return HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM;
            case 'G':
                return FIELD_CONFIDENCE;
            case 'H':
                return HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL;
            case 'I':
                return HealthFields.FIELD_BODY_TEMPERATURE;
            case 'J':
                return FIELD_REPETITIONS;
            case 'K':
                return FIELD_ACTIVITY_CONFIDENCE;
            case 'L':
                return zzhkm;
            case 'M':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC;
            case 'N':
                return HealthFields.FIELD_OXYGEN_SATURATION_MAX;
            case 'O':
                return HealthFields.FIELD_OXYGEN_SATURATION_MIN;
            case 'P':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX;
            case 'Q':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN;
            case 'R':
                return zzhkj;
            case 'S':
                return zzhkl;
            case 'T':
                return zzhke;
            case 'U':
                return zzhkf;
            case 'V':
                return FIELD_RESISTANCE;
            case 'W':
                return HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE;
            case 'X':
                return HealthFields.FIELD_MENSTRUAL_FLOW;
            case 'Y':
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX;
            case 'Z':
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN;
            case '[':
                return HealthFields.FIELD_BODY_POSITION;
            case '\\':
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE;
            case ']':
                return FIELD_ALTITUDE;
            case '^':
                return FIELD_EXERCISE;
            case '_':
                return HealthFields.FIELD_CERVICAL_FIRMNESS;
            case '`':
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE;
            default:
                return new Field(str, i, (Boolean) null);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        return this.name.equals(field.name) && this.format == field.format;
    }

    public final int getFormat() {
        return this.format;
    }

    public final String getName() {
        return this.name;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final Boolean isOptional() {
        return this.zzhks;
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.name;
        objArr[1] = this.format == 1 ? "i" : "f";
        return String.format("%s(%s)", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getName(), false);
        zzbgo.zzc(parcel, 2, getFormat());
        zzbgo.zza(parcel, 3, isOptional(), false);
        zzbgo.zzai(parcel, zze);
    }
}
