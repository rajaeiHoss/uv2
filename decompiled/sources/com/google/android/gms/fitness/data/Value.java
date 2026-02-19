package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzn;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzfmk;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Value extends zzbgl {
    public static final Parcelable.Creator<Value> CREATOR = new zzai();
    private final int format;
    private float value;
    private String zzgim;
    private boolean zzhlt;
    private Map<String, MapValue> zzhlu;
    private int[] zzhlv;
    private float[] zzhlw;
    private byte[] zzhlx;

    public Value(int i) {
        this(i, false, 0.0f, (String) null, (Bundle) null, (int[]) null, (float[]) null, (byte[]) null);
    }

    Value(int i, boolean z, float f, String str, Bundle bundle, int[] iArr, float[] fArr, byte[] bArr) {
        ArrayMap arrayMap;
        this.format = i;
        this.zzhlt = z;
        this.value = f;
        this.zzgim = str;
        if (bundle == null) {
            arrayMap = null;
        } else {
            bundle.setClassLoader(MapValue.class.getClassLoader());
            arrayMap = new ArrayMap(bundle.size());
            for (String str2 : bundle.keySet()) {
                arrayMap.put(str2, (MapValue) bundle.getParcelable(str2));
            }
        }
        this.zzhlu = arrayMap;
        this.zzhlv = iArr;
        this.zzhlw = fArr;
        this.zzhlx = bArr;
    }

    public final String asActivity() {
        return zzfmk.getName(asInt());
    }

    public final float asFloat() {
        zzbq.zza(this.format == 2, (Object) "Value is not in float format");
        return this.value;
    }

    public final int asInt() {
        boolean z = true;
        if (this.format != 1) {
            z = false;
        }
        zzbq.zza(z, (Object) "Value is not in int format");
        return Float.floatToRawIntBits(this.value);
    }

    public final String asString() {
        zzbq.zza(this.format == 3, (Object) "Value is not in string format");
        return this.zzgim;
    }

    public final void clearKey(String str) {
        zzbq.zza(this.format == 4, (Object) "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        Map<String, MapValue> map = this.zzhlu;
        if (map != null) {
            map.remove(str);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value2 = (Value) obj;
        int i = this.format;
        if (i == value2.format && this.zzhlt == value2.zzhlt) {
            switch (i) {
                case 1:
                    if (asInt() == value2.asInt()) {
                        return true;
                    }
                    break;
                case 2:
                    return this.value == value2.value;
                case 3:
                    return zzbg.equal(this.zzgim, value2.zzgim);
                case 4:
                    return zzbg.equal(this.zzhlu, value2.zzhlu);
                case 5:
                    return Arrays.equals(this.zzhlv, value2.zzhlv);
                case 6:
                    return Arrays.equals(this.zzhlw, value2.zzhlw);
                case 7:
                    return Arrays.equals(this.zzhlx, value2.zzhlx);
                default:
                    if (this.value == value2.value) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public final int getFormat() {
        return this.format;
    }

    public final Float getKeyValue(String str) {
        zzbq.zza(this.format == 4, (Object) "Value is not in float map format");
        Map<String, MapValue> map = this.zzhlu;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return Float.valueOf(this.zzhlu.get(str).asFloat());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.value), this.zzgim, this.zzhlu, this.zzhlv, this.zzhlw, this.zzhlx});
    }

    public final boolean isSet() {
        return this.zzhlt;
    }

    public final void setActivity(String str) {
        setInt(zzfmk.zzuc(str));
    }

    public final void setFloat(float f) {
        zzbq.zza(this.format == 2, (Object) "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.zzhlt = true;
        this.value = f;
    }

    public final void setInt(int i) {
        zzbq.zza(this.format == 1, (Object) "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.zzhlt = true;
        this.value = Float.intBitsToFloat(i);
    }

    public final void setKeyValue(String str, float f) {
        zzbq.zza(this.format == 4, (Object) "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzhlt = true;
        if (this.zzhlu == null) {
            this.zzhlu = new HashMap();
        }
        this.zzhlu.put(str, new MapValue(2, f));
    }

    public final void setString(String str) {
        zzbq.zza(this.format == 3, (Object) "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
        this.zzhlt = true;
        this.zzgim = str;
    }

    public final String toString() {
        if (!this.zzhlt) {
            return "unset";
        }
        switch (this.format) {
            case 1:
                return Integer.toString(asInt());
            case 2:
                return Float.toString(this.value);
            case 3:
                return this.zzgim;
            case 4:
                return new TreeMap(this.zzhlu).toString();
            case 5:
                return Arrays.toString(this.zzhlv);
            case 6:
                return Arrays.toString(this.zzhlw);
            case 7:
                byte[] bArr = this.zzhlx;
                return zzn.zza(bArr, 0, bArr.length, false);
            default:
                return "unknown";
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle;
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getFormat());
        zzbgo.zza(parcel, 2, isSet());
        zzbgo.zza(parcel, 3, this.value);
        zzbgo.zza(parcel, 4, this.zzgim, false);
        if (this.zzhlu == null) {
            bundle = null;
        } else {
            bundle = new Bundle(this.zzhlu.size());
            for (Map.Entry next : this.zzhlu.entrySet()) {
                bundle.putParcelable((String) next.getKey(), (Parcelable) next.getValue());
            }
        }
        zzbgo.zza(parcel, 5, bundle, false);
        zzbgo.zza(parcel, 6, this.zzhlv, false);
        zzbgo.zza(parcel, 7, this.zzhlw, false);
        zzbgo.zza(parcel, 8, this.zzhlx, false);
        zzbgo.zzai(parcel, zze);
    }
}
