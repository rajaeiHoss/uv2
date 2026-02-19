package org.apache.qpid.management.common.sasl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;

public class UsernameHashedPasswordCallbackHandler implements CallbackHandler {
    private char[] pwchars;
    private String user;

    public UsernameHashedPasswordCallbackHandler(String str, String str2) throws Exception {
        this.user = str;
        this.pwchars = getHash(str2);
    }

    public void handle(Callback[] callbackArr) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbackArr.length; i++) {
            if (callbackArr[i] instanceof NameCallback) {
                ((NameCallback) callbackArr[i]).setName(this.user);
            } else if (callbackArr[i] instanceof PasswordCallback) {
                ((PasswordCallback) callbackArr[i]).setPassword(this.pwchars);
            } else {
                throw new UnsupportedCallbackException(callbackArr[i]);
            }
        }
    }

    private void clearPassword() {
        if (this.pwchars != null) {
            int i = 0;
            while (true) {
                char[] cArr = this.pwchars;
                if (i < cArr.length) {
                    cArr[i] = 0;
                    i++;
                } else {
                    this.pwchars = null;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        clearPassword();
    }

    public static char[] getHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes = str.getBytes("utf-8");
        MessageDigest instance = MessageDigest.getInstance("MD5");
        int i = 0;
        for (byte update : bytes) {
            instance.update(update);
        }
        byte[] digest = instance.digest();
        char[] cArr = new char[digest.length];
        int length = digest.length;
        int i2 = 0;
        while (i < length) {
            cArr[i2] = (char) digest[i];
            i++;
            i2++;
        }
        return cArr;
    }
}
