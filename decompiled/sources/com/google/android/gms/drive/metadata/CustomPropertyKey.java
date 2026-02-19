package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomPropertyKey extends zzbgl {
    public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new zzc();
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 0;
    private static final Pattern zzgym = Pattern.compile("[\\w.!@$%^&*()/-]+");
    private int mVisibility;
    private String zzbkr;

    public CustomPropertyKey(String str, int i) {
        zzbq.checkNotNull(str, "key");
        zzbq.checkArgument(zzgym.matcher(str).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        boolean z = true;
        if (!(i == 0 || i == 1)) {
            z = false;
        }
        zzbq.checkArgument(z, "visibility must be either PUBLIC or PRIVATE");
        this.zzbkr = str;
        this.mVisibility = i;
    }

    public static CustomPropertyKey fromJson(JSONObject jSONObject) throws JSONException {
        return new CustomPropertyKey(jSONObject.getString("key"), jSONObject.getInt("visibility"));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
            return customPropertyKey.getKey().equals(this.zzbkr) && customPropertyKey.getVisibility() == this.mVisibility;
        }
        return false;
    }

    public String getKey() {
        return this.zzbkr;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int hashCode() {
        String str = this.zzbkr;
        int i = this.mVisibility;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11);
        sb.append(str);
        sb.append(i);
        return sb.toString().hashCode();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", getKey());
        jSONObject.put("visibility", getVisibility());
        return jSONObject;
    }

    public String toString() {
        String str = this.zzbkr;
        int i = this.mVisibility;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 31);
        sb.append("CustomPropertyKey(");
        sb.append(str);
        sb.append(",");
        sb.append(i);
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzbkr, false);
        zzbgo.zzc(parcel, 3, this.mVisibility);
        zzbgo.zzai(parcel, zze);
    }
}
