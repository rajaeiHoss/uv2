package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import org.json.JSONException;
import org.json.JSONObject;

public class TokenBindingIdValue extends zzbgl {
    public static final TokenBindingIdValue ABSENT = new TokenBindingIdValue();
    public static final Parcelable.Creator<TokenBindingIdValue> CREATOR = new zzt();
    public static final TokenBindingIdValue UNAVAILABLE = new TokenBindingIdValue("unavailable");
    public static final TokenBindingIdValue UNUSED = new TokenBindingIdValue("unused");
    private final TokenBindingIdValueType zzhfr;
    private final String zzhfs;
    private final String zzhft;

    public enum TokenBindingIdValueType implements Parcelable {
        ABSENT(0),
        STRING(1),
        OBJECT(2);
        
        public static final Parcelable.Creator<TokenBindingIdValueType> CREATOR = new zzs();
        /* access modifiers changed from: private */
        public final int zzenu;

        private TokenBindingIdValueType(int i) {
            this.zzenu = i;
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.zzenu);
        }
    }

    public static class UnsupportedTokenBindingIdValueTypeException extends Exception {
        public UnsupportedTokenBindingIdValueTypeException(int i) {
            super(String.format("TokenBindingIdValueType %s not supported", new Object[]{Integer.valueOf(i)}));
        }
    }

    private TokenBindingIdValue() {
        this.zzhfr = TokenBindingIdValueType.ABSENT;
        this.zzhft = null;
        this.zzhfs = null;
    }

    TokenBindingIdValue(int i, String str, String str2) {
        try {
            this.zzhfr = toTokenBindingIdValueType(i);
            this.zzhfs = str;
            this.zzhft = str2;
        } catch (UnsupportedTokenBindingIdValueTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private TokenBindingIdValue(String str) {
        this.zzhfs = (String) zzbq.checkNotNull(str);
        this.zzhfr = TokenBindingIdValueType.STRING;
        this.zzhft = null;
    }

    public TokenBindingIdValue(JSONObject jSONObject) {
        this.zzhft = (String) zzbq.checkNotNull(jSONObject.toString());
        this.zzhfr = TokenBindingIdValueType.OBJECT;
        this.zzhfs = null;
    }

    public static TokenBindingIdValueType toTokenBindingIdValueType(int i) throws UnsupportedTokenBindingIdValueTypeException {
        for (TokenBindingIdValueType tokenBindingIdValueType : TokenBindingIdValueType.values()) {
            if (i == tokenBindingIdValueType.zzenu) {
                return tokenBindingIdValueType;
            }
        }
        throw new UnsupportedTokenBindingIdValueTypeException(i);
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TokenBindingIdValue)) {
            return false;
        }
        TokenBindingIdValue tokenBindingIdValue = (TokenBindingIdValue) obj;
        if (!this.zzhfr.equals(tokenBindingIdValue.zzhfr)) {
            return false;
        }
        int i = zzr.zzhfu[this.zzhfr.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            str = this.zzhfs;
            str2 = tokenBindingIdValue.zzhfs;
        } else if (i != 3) {
            return false;
        } else {
            str = this.zzhft;
            str2 = tokenBindingIdValue.zzhft;
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

    public TokenBindingIdValueType getType() {
        return this.zzhfr;
    }

    public int getTypeAsInt() {
        return this.zzhfr.zzenu;
    }

    public int hashCode() {
        String str;
        int i;
        int hashCode = this.zzhfr.hashCode() + 31;
        int i2 = zzr.zzhfu[this.zzhfr.ordinal()];
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
