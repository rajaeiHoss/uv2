package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelIdValue extends zzbgl {
    public static final ChannelIdValue ABSENT = new ChannelIdValue();
    public static final Parcelable.Creator<ChannelIdValue> CREATOR = new zzc();
    public static final ChannelIdValue UNAVAILABLE = new ChannelIdValue("unavailable");
    public static final ChannelIdValue UNUSED = new ChannelIdValue("unused");
    private final String zzhfs;
    private final String zzhft;
    private final ChannelIdValueType zzhgb;

    public enum ChannelIdValueType implements Parcelable {
        ABSENT(0),
        STRING(1),
        OBJECT(2);
        
        public static final Parcelable.Creator<ChannelIdValueType> CREATOR = new zzb();
        /* access modifiers changed from: private */
        public final int zzenu;

        private ChannelIdValueType(int i) {
            this.zzenu = i;
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.zzenu);
        }
    }

    public static class UnsupportedChannelIdValueTypeException extends Exception {
        public UnsupportedChannelIdValueTypeException(int i) {
            super(String.format("ChannelIdValueType %s not supported", new Object[]{Integer.valueOf(i)}));
        }
    }

    private ChannelIdValue() {
        this.zzhgb = ChannelIdValueType.ABSENT;
        this.zzhft = null;
        this.zzhfs = null;
    }

    ChannelIdValue(int i, String str, String str2) {
        try {
            this.zzhgb = toChannelIdValueType(i);
            this.zzhfs = str;
            this.zzhft = str2;
        } catch (UnsupportedChannelIdValueTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private ChannelIdValue(String str) {
        this.zzhfs = (String) zzbq.checkNotNull(str);
        this.zzhgb = ChannelIdValueType.STRING;
        this.zzhft = null;
    }

    public ChannelIdValue(JSONObject jSONObject) {
        this.zzhft = (String) zzbq.checkNotNull(jSONObject.toString());
        this.zzhgb = ChannelIdValueType.OBJECT;
        this.zzhfs = null;
    }

    public static ChannelIdValueType toChannelIdValueType(int i) throws UnsupportedChannelIdValueTypeException {
        for (ChannelIdValueType channelIdValueType : ChannelIdValueType.values()) {
            if (i == channelIdValueType.zzenu) {
                return channelIdValueType;
            }
        }
        throw new UnsupportedChannelIdValueTypeException(i);
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChannelIdValue)) {
            return false;
        }
        ChannelIdValue channelIdValue = (ChannelIdValue) obj;
        if (!this.zzhgb.equals(channelIdValue.zzhgb)) {
            return false;
        }
        int i = zza.zzhgc[this.zzhgb.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            str = this.zzhfs;
            str2 = channelIdValue.zzhfs;
        } else if (i != 3) {
            return false;
        } else {
            str = this.zzhft;
            str2 = channelIdValue.zzhft;
        }
        return str.equals(str2);
    }

    public JSONObject getObjectValue() {
        if (this.zzhft == null) {
            return null;
        }
        try {
            return new JSONObject(this.zzhft);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getObjectValueAsString() {
        return this.zzhft;
    }

    public String getStringValue() {
        return this.zzhfs;
    }

    public ChannelIdValueType getType() {
        return this.zzhgb;
    }

    public int getTypeAsInt() {
        return this.zzhgb.zzenu;
    }

    public int hashCode() {
        String str;
        int i;
        int hashCode = this.zzhgb.hashCode() + 31;
        int i2 = zza.zzhgc[this.zzhgb.ordinal()];
        if (i2 == 2) {
            i = hashCode * 31;
            str = this.zzhfs;
        } else if (i2 != 3) {
            return hashCode;
        } else {
            i = hashCode * 31;
            str = this.zzhft;
        }
        return i + str.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getTypeAsInt());
        zzbgo.zza(parcel, 3, getStringValue(), false);
        zzbgo.zza(parcel, 4, getObjectValueAsString(), false);
        zzbgo.zzai(parcel, zze);
    }
}
