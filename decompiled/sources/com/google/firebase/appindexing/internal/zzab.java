package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.appindexing.FirebaseAppIndexingException;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseAppIndexingTooManyArgumentsException;
import com.google.firebase.appindexing.zza;

public final class zzab {
    public static FirebaseAppIndexingException zzb(Status status, String str) {
        zzbq.checkNotNull(status);
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null && !statusMessage.isEmpty()) {
            str = statusMessage;
        }
        int statusCode = status.getStatusCode();
        return statusCode != 17510 ? statusCode != 17511 ? statusCode != 17513 ? new FirebaseAppIndexingException(str) : new zza(str) : new FirebaseAppIndexingTooManyArgumentsException(str) : new FirebaseAppIndexingInvalidArgumentException(str);
    }
}
