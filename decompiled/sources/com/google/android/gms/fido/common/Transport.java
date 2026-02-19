package com.google.android.gms.fido.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public enum Transport implements ReflectedParcelable {
    BLUETOOTH_CLASSIC("bt"),
    BLUETOOTH_LOW_ENERGY("ble"),
    NFC("nfc"),
    USB("usb");
    
    public static final Parcelable.Creator<Transport> CREATOR = new zza();
    private final String mValue;

    public static class UnsupportedTransportException extends Exception {
        public UnsupportedTransportException(String str) {
            super(String.format("Transport %s not supported", new Object[]{str}));
        }
    }

    private Transport(String str) {
        this.mValue = str;
    }

    public static Transport fromString(String str) throws UnsupportedTransportException {
        for (Transport transport : values()) {
            if (str.equals(transport.mValue)) {
                return transport;
            }
        }
        throw new UnsupportedTransportException(str);
    }

    public static List<Transport> parseTransports(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getString(i);
            if (string != null && !string.isEmpty()) {
                try {
                    hashSet.add(fromString(string));
                } catch (UnsupportedTransportException unused) {
                    String valueOf = String.valueOf(string);
                    Log.w("Transport", valueOf.length() != 0 ? "Ignoring unrecognized transport ".concat(valueOf) : new String("Ignoring unrecognized transport "));
                }
            }
        }
        return new ArrayList(hashSet);
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.mValue;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mValue);
    }
}
