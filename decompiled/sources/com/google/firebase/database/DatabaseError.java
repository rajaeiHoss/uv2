package com.google.firebase.database;

import com.google.android.gms.internal.zzdyq;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class DatabaseError {
    public static final int DATA_STALE = -1;
    public static final int DISCONNECTED = -4;
    public static final int EXPIRED_TOKEN = -6;
    public static final int INVALID_TOKEN = -7;
    public static final int MAX_RETRIES = -8;
    public static final int NETWORK_ERROR = -24;
    public static final int OPERATION_FAILED = -2;
    public static final int OVERRIDDEN_BY_SET = -9;
    public static final int PERMISSION_DENIED = -3;
    public static final int UNAVAILABLE = -10;
    public static final int UNKNOWN_ERROR = -999;
    public static final int USER_CODE_EXCEPTION = -11;
    public static final int WRITE_CANCELED = -25;
    private static final Map<Integer, String> zzmwd;
    private static final Map<String, Integer> zzmwe;
    private final int code;
    private final String message;
    private final String zzmwf;

    static {
        HashMap hashMap = new HashMap();
        zzmwd = hashMap;
        hashMap.put(-1, "The transaction needs to be run again with current data");
        hashMap.put(-2, "The server indicated that this operation failed");
        hashMap.put(-3, "This client does not have permission to perform this operation");
        hashMap.put(-4, "The operation had to be aborted due to a network disconnect");
        hashMap.put(-6, "The supplied auth token has expired");
        hashMap.put(-7, "The supplied auth token was invalid");
        hashMap.put(-8, "The transaction had too many retries");
        hashMap.put(-9, "The transaction was overridden by a subsequent set");
        hashMap.put(-10, "The service is unavailable");
        hashMap.put(-11, "User code called from the Firebase Database runloop threw an exception:\n");
        hashMap.put(-24, "The operation could not be performed due to a network error");
        hashMap.put(-25, "The write was canceled by the user.");
        hashMap.put(Integer.valueOf(UNKNOWN_ERROR), "An unknown error occurred");
        HashMap hashMap2 = new HashMap();
        zzmwe = hashMap2;
        hashMap2.put("datastale", -1);
        hashMap2.put("failure", -2);
        hashMap2.put("permission_denied", -3);
        hashMap2.put("disconnected", -4);
        hashMap2.put("expired_token", -6);
        hashMap2.put("invalid_token", -7);
        hashMap2.put("maxretries", -8);
        hashMap2.put("overriddenbyset", -9);
        hashMap2.put("unavailable", -10);
        hashMap2.put("network_error", -24);
        hashMap2.put("write_canceled", -25);
    }

    private DatabaseError(int i, String str) {
        this(-11, str, (String) null);
    }

    private DatabaseError(int i, String str, String str2) {
        this.code = i;
        this.message = str;
        this.zzmwf = "";
    }

    public static DatabaseError fromException(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        zzdyq.zza(th, new PrintWriter(stringWriter));
        String valueOf = String.valueOf(zzmwd.get(-11));
        String valueOf2 = String.valueOf(stringWriter.toString());
        return new DatabaseError(-11, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public static DatabaseError zzbb(String str, String str2) {
        Integer num = zzmwe.get(str.toLowerCase());
        if (num == null) {
            num = Integer.valueOf(UNKNOWN_ERROR);
        }
        if (str2 == null) {
            str2 = zzmwd.get(num);
        }
        return new DatabaseError(num.intValue(), str2, (String) null);
    }

    public static DatabaseError zzhg(int i) {
        Map<Integer, String> map = zzmwd;
        if (map.containsKey(-25)) {
            return new DatabaseError(-25, map.get(-25), (String) null);
        }
        StringBuilder sb = new StringBuilder(49);
        sb.append("Invalid Firebase Database error code: -25");
        throw new IllegalArgumentException(sb.toString());
    }

    public static DatabaseError zzpm(String str) {
        Integer num = zzmwe.get(str.toLowerCase());
        if (num == null) {
            num = Integer.valueOf(UNKNOWN_ERROR);
        }
        return new DatabaseError(num.intValue(), zzmwd.get(num), (String) null);
    }

    public int getCode() {
        return this.code;
    }

    public String getDetails() {
        return this.zzmwf;
    }

    public String getMessage() {
        return this.message;
    }

    public DatabaseException toException() {
        String valueOf = String.valueOf(this.message);
        return new DatabaseException(valueOf.length() != 0 ? "Firebase Database error: ".concat(valueOf) : new String("Firebase Database error: "));
    }

    public String toString() {
        String valueOf = String.valueOf(this.message);
        return valueOf.length() != 0 ? "DatabaseError: ".concat(valueOf) : new String("DatabaseError: ");
    }
}
