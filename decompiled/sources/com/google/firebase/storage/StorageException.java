package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.FirebaseException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class StorageException extends FirebaseException {
    public static final int ERROR_BUCKET_NOT_FOUND = -13011;
    public static final int ERROR_CANCELED = -13040;
    public static final int ERROR_INVALID_CHECKSUM = -13031;
    public static final int ERROR_NOT_AUTHENTICATED = -13020;
    public static final int ERROR_NOT_AUTHORIZED = -13021;
    public static final int ERROR_OBJECT_NOT_FOUND = -13010;
    public static final int ERROR_PROJECT_NOT_FOUND = -13012;
    public static final int ERROR_QUOTA_EXCEEDED = -13013;
    public static final int ERROR_RETRY_LIMIT_EXCEEDED = -13030;
    public static final int ERROR_UNKNOWN = -13000;
    private static IOException zzoub = new IOException("The operation was canceled.");
    private final int mErrorCode;
    private final int zzouc;
    private String zzoud;
    private Throwable zzoue;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    private StorageException(int i, Throwable th, int i2) {
        String str = "An unknown error occurred, please check the HTTP result code and inner exception for server response.";
        if (i == -13040) {
            str = "The operation was cancelled.";
        } else if (i != -13000) {
            if (i == -13031) {
                str = "Object has a checksum which does not match. Please retry the operation.";
            } else if (i == -13030) {
                str = "The operation retry limit has been exceeded.";
            } else if (i == -13021) {
                str = "User does not have permission to access this object.";
            } else if (i != -13020) {
                switch (i) {
                    case ERROR_QUOTA_EXCEEDED /*-13013*/:
                        str = "Quota for bucket exceeded, please view quota on www.firebase.google.com/storage.";
                        break;
                    case ERROR_PROJECT_NOT_FOUND /*-13012*/:
                        str = "Project does not exist.";
                        break;
                    case ERROR_BUCKET_NOT_FOUND /*-13011*/:
                        str = "Bucket does not exist.";
                        break;
                    case ERROR_OBJECT_NOT_FOUND /*-13010*/:
                        str = "Object does not exist at location.";
                        break;
                }
            } else {
                str = "User is not authenticated, please authenticate using Firebase Authentication and try again.";
            }
        }
        this.zzoud = str;
        this.zzoue = th;
        this.mErrorCode = i;
        this.zzouc = i2;
        String num = Integer.toString(i);
        String num2 = Integer.toString(i2);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 52 + String.valueOf(num).length() + String.valueOf(num2).length());
        sb.append("StorageException has occurred.\n");
        sb.append(str);
        sb.append("\n Code: ");
        sb.append(num);
        sb.append(" HttpResult: ");
        sb.append(num2);
        Log.e("StorageException", sb.toString());
        Throwable th2 = this.zzoue;
        if (th2 != null) {
            Log.e("StorageException", th2.getMessage(), this.zzoue);
        }
    }

    public static StorageException fromErrorStatus(Status status) {
        zzbq.checkNotNull(status);
        zzbq.checkArgument(!status.isSuccess());
        return new StorageException(status.isCanceled() ? ERROR_CANCELED : status == Status.zzftt ? ERROR_RETRY_LIMIT_EXCEEDED : ERROR_UNKNOWN, (Throwable) null, 0);
    }

    public static StorageException fromException(Throwable th) {
        return fromExceptionAndHttpCode(th, 0);
    }

    public static StorageException fromExceptionAndHttpCode(Throwable th, int i) {
        if (th instanceof StorageException) {
            return (StorageException) th;
        }
        if ((i == 0 || (i >= 200 && i < 300)) && th == null) {
            return null;
        }
        return new StorageException(th instanceof zza ? ERROR_CANCELED : i != -2 ? i != 401 ? i != 409 ? i != 403 ? i != 404 ? ERROR_UNKNOWN : ERROR_OBJECT_NOT_FOUND : ERROR_NOT_AUTHORIZED : ERROR_INVALID_CHECKSUM : ERROR_NOT_AUTHENTICATED : ERROR_RETRY_LIMIT_EXCEEDED, th, i);
    }

    public Throwable getCause() {
        Throwable th = this.zzoue;
        if (th == this) {
            return null;
        }
        return th;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public int getHttpResultCode() {
        return this.zzouc;
    }

    public boolean getIsRecoverableException() {
        return getErrorCode() == -13030;
    }

    public String getMessage() {
        return this.zzoud;
    }
}
