package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;

public final class zzeaw {
    private static SparseArray<Pair<String, String>> zzmrd;

    static {
        SparseArray<Pair<String, String>> sparseArray = new SparseArray<>();
        zzmrd = sparseArray;
        sparseArray.put(17000, new Pair("ERROR_INVALID_CUSTOM_TOKEN", "The custom token format is incorrect. Please check the documentation."));
        zzmrd.put(17002, new Pair("ERROR_CUSTOM_TOKEN_MISMATCH", "The custom token corresponds to a different audience."));
        zzmrd.put(17004, new Pair("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
        zzmrd.put(17008, new Pair("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
        zzmrd.put(17009, new Pair("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
        zzmrd.put(17024, new Pair("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
        zzmrd.put(17014, new Pair("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
        zzmrd.put(17012, new Pair("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
        zzmrd.put(17007, new Pair("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
        zzmrd.put(17025, new Pair("ERROR_CREDENTIAL_ALREADY_IN_USE", "This credential is already associated with a different user account."));
        zzmrd.put(17005, new Pair("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
        zzmrd.put(17021, new Pair("ERROR_USER_TOKEN_EXPIRED", "The user's credential is no longer valid. The user must sign in again."));
        zzmrd.put(17011, new Pair("ERROR_USER_NOT_FOUND", "There is no user record corresponding to this identifier. The user may have been deleted."));
        zzmrd.put(17017, new Pair("ERROR_INVALID_USER_TOKEN", "The user's credential is no longer valid. The user must sign in again."));
        zzmrd.put(17006, new Pair("ERROR_OPERATION_NOT_ALLOWED", "This operation is not allowed. You must enable this service in the console."));
        zzmrd.put(17026, new Pair("ERROR_WEAK_PASSWORD", "The given password is invalid."));
        zzmrd.put(17029, new Pair("ERROR_EXPIRED_ACTION_CODE", "The out of band code has expired."));
        zzmrd.put(17030, new Pair("ERROR_INVALID_ACTION_CODE", "The out of band code is invalid. This can happen if the code is malformed, expired, or has already been used."));
        zzmrd.put(17031, new Pair("ERROR_INVALID_MESSAGE_PAYLOAD", "The email template corresponding to this action contains invalid characters in its message. Please fix by going to the Auth email templates section in the Firebase Console."));
        zzmrd.put(17033, new Pair("ERROR_INVALID_RECIPIENT_EMAIL", "The email corresponding to this action failed to send as the provided recipient email address is invalid."));
        zzmrd.put(17032, new Pair("ERROR_INVALID_SENDER", "The email template corresponding to this action contains an invalid sender email or name. Please fix by going to the Auth email templates section in the Firebase Console."));
        zzmrd.put(17034, new Pair("ERROR_MISSING_EMAIL", "An email address must be provided."));
        zzmrd.put(17035, new Pair("ERROR_MISSING_PASSWORD", "A password must be provided."));
        zzmrd.put(17041, new Pair("ERROR_MISSING_PHONE_NUMBER", "To send verification codes, provide a phone number for the recipient."));
        zzmrd.put(17042, new Pair("ERROR_INVALID_PHONE_NUMBER", "The format of the phone number provided is incorrect. Please enter the phone number in a format that can be parsed into E.164 format. E.164 phone numbers are written in the format [+][country code][subscriber number including area code]."));
        zzmrd.put(17043, new Pair("ERROR_MISSING_VERIFICATION_CODE", "The Phone Auth Credential was created with an empty sms verification Code"));
        zzmrd.put(17044, new Pair("ERROR_INVALID_VERIFICATION_CODE", "The sms verification code used to create the phone auth credential is invalid. Please resend the verification code sms and be sure use the verification code provided by the user."));
        zzmrd.put(17045, new Pair("ERROR_MISSING_VERIFICATION_ID", "The Phone Auth Credential was created with an empty verification ID"));
        zzmrd.put(17046, new Pair("ERROR_INVALID_VERIFICATION_ID", "The verification ID used to create the phone auth credential is invalid."));
        zzmrd.put(17049, new Pair("ERROR_RETRY_PHONE_AUTH", "An error occurred during authentication using the PhoneAuthCredential. Please retry authentication."));
        zzmrd.put(17051, new Pair("ERROR_SESSION_EXPIRED", "The sms code has expired. Please re-send the verification code to try again."));
        zzmrd.put(17052, new Pair("ERROR_QUOTA_EXCEEDED", "The sms quota for this project has been exceeded."));
        zzmrd.put(17028, new Pair("ERROR_APP_NOT_AUTHORIZED", "This app is not authorized to use Firebase Authentication. Please verifythat the correct package name and SHA-1 are configured in the Firebase Console."));
        zzmrd.put(17063, new Pair("ERROR_API_NOT_AVAILABLE", "The API that you are calling is not available on devices without Google Play Services."));
        zzmrd.put(17062, new Pair("ERROR_WEB_INTERNAL_ERROR", "There was an internal error in the web widget."));
        zzmrd.put(17064, new Pair("ERROR_INVALID_CERT_HASH", "There was an error while trying to get your package certificate hash."));
        zzmrd.put(17065, new Pair("ERROR_WEB_STORAGE_UNSUPPORTED", "This browser is not supported or 3rd party cookies and data may be disabled."));
    }

    private static String zza(String str, Status status) {
        if (TextUtils.isEmpty(status.getStatusMessage())) {
            return str;
        }
        return String.format(String.valueOf(str).concat(" [ %s ]"), new Object[]{status.getStatusMessage()});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00dd, code lost:
        return new com.google.firebase.auth.FirebaseAuthUserCollisionException(zzhd(r0), r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.firebase.FirebaseException zzaw(com.google.android.gms.common.api.Status r4) {
        /*
            int r0 = r4.getStatusCode()
            java.lang.String r1 = zzhe(r0)
            java.lang.String r1 = zza(r1, r4)
            r2 = 17000(0x4268, float:2.3822E-41)
            if (r0 == r2) goto L_0x012e
            r2 = 17002(0x426a, float:2.3825E-41)
            if (r0 == r2) goto L_0x0124
            r2 = 17049(0x4299, float:2.3891E-41)
            if (r0 == r2) goto L_0x011a
            r2 = 17495(0x4457, float:2.4516E-41)
            if (r0 == r2) goto L_0x010e
            r2 = 17499(0x445b, float:2.4521E-41)
            java.lang.String r3 = "An internal error has occurred."
            if (r0 == r2) goto L_0x0104
            r2 = 17020(0x427c, float:2.385E-41)
            if (r0 == r2) goto L_0x00f8
            r2 = 17021(0x427d, float:2.3852E-41)
            if (r0 == r2) goto L_0x00ee
            r2 = 17051(0x429b, float:2.3894E-41)
            if (r0 == r2) goto L_0x011a
            r2 = 17052(0x429c, float:2.3895E-41)
            if (r0 == r2) goto L_0x00e8
            switch(r0) {
                case 17004: goto L_0x0124;
                case 17005: goto L_0x00ee;
                case 17006: goto L_0x00de;
                case 17007: goto L_0x00d4;
                case 17008: goto L_0x0124;
                case 17009: goto L_0x0124;
                case 17010: goto L_0x00c8;
                case 17011: goto L_0x00ee;
                case 17012: goto L_0x00d4;
                default: goto L_0x0035;
            }
        L_0x0035:
            switch(r0) {
                case 17014: goto L_0x00be;
                case 17015: goto L_0x00b2;
                case 17016: goto L_0x00a6;
                case 17017: goto L_0x00ee;
                default: goto L_0x0038;
            }
        L_0x0038:
            switch(r0) {
                case 17024: goto L_0x0124;
                case 17025: goto L_0x00d4;
                case 17026: goto L_0x0098;
                default: goto L_0x003b;
            }
        L_0x003b:
            switch(r0) {
                case 17028: goto L_0x008e;
                case 17029: goto L_0x0084;
                case 17030: goto L_0x0084;
                case 17031: goto L_0x007a;
                case 17032: goto L_0x007a;
                case 17033: goto L_0x007a;
                case 17034: goto L_0x011a;
                case 17035: goto L_0x011a;
                default: goto L_0x003e;
            }
        L_0x003e:
            switch(r0) {
                case 17041: goto L_0x011a;
                case 17042: goto L_0x011a;
                case 17043: goto L_0x011a;
                case 17044: goto L_0x011a;
                case 17045: goto L_0x011a;
                case 17046: goto L_0x011a;
                default: goto L_0x0041;
            }
        L_0x0041:
            switch(r0) {
                case 17061: goto L_0x006e;
                case 17062: goto L_0x0064;
                case 17063: goto L_0x005e;
                case 17064: goto L_0x0054;
                case 17065: goto L_0x004a;
                default: goto L_0x0044;
            }
        L_0x0044:
            com.google.firebase.FirebaseException r4 = new com.google.firebase.FirebaseException
            r4.<init>(r3)
            return r4
        L_0x004a:
            com.google.firebase.auth.FirebaseAuthException r4 = new com.google.firebase.auth.FirebaseAuthException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x0054:
            com.google.firebase.auth.FirebaseAuthException r4 = new com.google.firebase.auth.FirebaseAuthException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x005e:
            com.google.firebase.FirebaseApiNotAvailableException r4 = new com.google.firebase.FirebaseApiNotAvailableException
            r4.<init>(r1)
            return r4
        L_0x0064:
            com.google.firebase.auth.FirebaseAuthException r4 = new com.google.firebase.auth.FirebaseAuthException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x006e:
            java.lang.String r0 = "There was a failure in the connection between the web widget and the Firebase Auth backend."
            java.lang.String r4 = zza(r0, r4)
            com.google.firebase.FirebaseNetworkException r0 = new com.google.firebase.FirebaseNetworkException
            r0.<init>(r4)
            return r0
        L_0x007a:
            com.google.firebase.auth.FirebaseAuthEmailException r4 = new com.google.firebase.auth.FirebaseAuthEmailException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x0084:
            com.google.firebase.auth.FirebaseAuthActionCodeException r4 = new com.google.firebase.auth.FirebaseAuthActionCodeException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x008e:
            com.google.firebase.auth.FirebaseAuthException r4 = new com.google.firebase.auth.FirebaseAuthException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x0098:
            com.google.firebase.auth.FirebaseAuthWeakPasswordException r2 = new com.google.firebase.auth.FirebaseAuthWeakPasswordException
            java.lang.String r0 = zzhd(r0)
            java.lang.String r4 = r4.getStatusMessage()
            r2.<init>(r0, r1, r4)
            return r2
        L_0x00a6:
            java.lang.String r0 = "User was not linked to an account with the given provider."
            java.lang.String r4 = zza(r0, r4)
            com.google.firebase.FirebaseException r0 = new com.google.firebase.FirebaseException
            r0.<init>(r4)
            return r0
        L_0x00b2:
            java.lang.String r0 = "User has already been linked to the given provider."
            java.lang.String r4 = zza(r0, r4)
            com.google.firebase.FirebaseException r0 = new com.google.firebase.FirebaseException
            r0.<init>(r4)
            return r0
        L_0x00be:
            com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException r4 = new com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x00c8:
            java.lang.String r0 = "We have blocked all requests from this device due to unusual activity. Try again later."
            java.lang.String r4 = zza(r0, r4)
            com.google.firebase.FirebaseTooManyRequestsException r0 = new com.google.firebase.FirebaseTooManyRequestsException
            r0.<init>(r4)
            return r0
        L_0x00d4:
            com.google.firebase.auth.FirebaseAuthUserCollisionException r4 = new com.google.firebase.auth.FirebaseAuthUserCollisionException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x00de:
            com.google.firebase.auth.FirebaseAuthException r4 = new com.google.firebase.auth.FirebaseAuthException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x00e8:
            com.google.firebase.FirebaseTooManyRequestsException r4 = new com.google.firebase.FirebaseTooManyRequestsException
            r4.<init>(r1)
            return r4
        L_0x00ee:
            com.google.firebase.auth.FirebaseAuthInvalidUserException r4 = new com.google.firebase.auth.FirebaseAuthInvalidUserException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x00f8:
            java.lang.String r0 = "A network error (such as timeout, interrupted connection or unreachable host) has occurred."
            java.lang.String r4 = zza(r0, r4)
            com.google.firebase.FirebaseNetworkException r0 = new com.google.firebase.FirebaseNetworkException
            r0.<init>(r4)
            return r0
        L_0x0104:
            java.lang.String r4 = zza(r3, r4)
            com.google.firebase.FirebaseException r0 = new com.google.firebase.FirebaseException
            r0.<init>(r4)
            return r0
        L_0x010e:
            java.lang.String r0 = "Please sign in before trying to get a token."
            java.lang.String r4 = zza(r0, r4)
            com.google.firebase.internal.api.FirebaseNoSignedInUserException r0 = new com.google.firebase.internal.api.FirebaseNoSignedInUserException
            r0.<init>(r4)
            return r0
        L_0x011a:
            com.google.firebase.auth.FirebaseAuthInvalidCredentialsException r4 = new com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x0124:
            com.google.firebase.auth.FirebaseAuthInvalidCredentialsException r4 = new com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        L_0x012e:
            com.google.firebase.auth.FirebaseAuthInvalidCredentialsException r4 = new com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
            java.lang.String r0 = zzhd(r0)
            r4.<init>(r0, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzeaw.zzaw(com.google.android.gms.common.api.Status):com.google.firebase.FirebaseException");
    }

    public static FirebaseAuthUserCollisionException zzb(Status status, PhoneAuthCredential phoneAuthCredential) {
        int statusCode = status.getStatusCode();
        return new FirebaseAuthUserCollisionException(zzhd(statusCode), zza(zzhe(statusCode), status), phoneAuthCredential);
    }

    private static String zzhd(int i) {
        Pair pair = zzmrd.get(i);
        return pair != null ? (String) pair.first : "INTERNAL_ERROR";
    }

    private static String zzhe(int i) {
        Pair pair = zzmrd.get(i);
        return pair != null ? (String) pair.second : "An internal error happened";
    }
}
