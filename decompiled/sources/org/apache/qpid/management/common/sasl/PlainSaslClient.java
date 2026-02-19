package org.apache.qpid.management.common.sasl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;

public class PlainSaslClient implements SaslClient {
    private static byte SEPARATOR;
    private String authenticationID;
    private String authorizationID;
    private CallbackHandler cbh;
    private boolean completed = false;
    private byte[] password;

    public String getMechanismName() {
        return Constants.MECH_PLAIN;
    }

    public boolean hasInitialResponse() {
        return true;
    }

    public PlainSaslClient(String str, CallbackHandler callbackHandler) throws SaslException {
        this.cbh = callbackHandler;
        Object[] userInfo = getUserInfo();
        this.authorizationID = str;
        String str2 = (String) userInfo[0];
        this.authenticationID = str2;
        byte[] bArr = (byte[]) userInfo[1];
        this.password = bArr;
        if (str2 == null || bArr == null) {
            throw new SaslException("PLAIN: authenticationID and password must be specified");
        }
    }

    public byte[] evaluateChallenge(byte[] bArr) throws SaslException {
        int i;
        if (!this.completed) {
            this.completed = true;
            try {
                String str = this.authorizationID;
                byte[] bytes = str == null ? null : str.getBytes("UTF8");
                byte[] bytes2 = this.authenticationID.getBytes("UTF8");
                byte[] bArr2 = new byte[(this.password.length + bytes2.length + 2 + (bytes != null ? bytes.length : 0))];
                if (bytes != null) {
                    System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                    i = bytes.length;
                } else {
                    i = 0;
                }
                int i2 = i + 1;
                bArr2[i] = SEPARATOR;
                System.arraycopy(bytes2, 0, bArr2, i2, bytes2.length);
                int length = i2 + bytes2.length;
                bArr2[length] = SEPARATOR;
                byte[] bArr3 = this.password;
                System.arraycopy(bArr3, 0, bArr2, length + 1, bArr3.length);
                clearPassword();
                return bArr2;
            } catch (UnsupportedEncodingException e) {
                throw new SaslException("PLAIN: Cannot get UTF-8 encoding of ids", e);
            }
        } else {
            throw new IllegalStateException("PLAIN: authentication already completed");
        }
    }

    public boolean isComplete() {
        return this.completed;
    }

    public byte[] unwrap(byte[] bArr, int i, int i2) throws SaslException {
        if (this.completed) {
            throw new IllegalStateException("PLAIN: this mechanism supports neither integrity nor privacy");
        }
        throw new IllegalStateException("PLAIN: authentication not completed");
    }

    public byte[] wrap(byte[] bArr, int i, int i2) throws SaslException {
        if (this.completed) {
            throw new IllegalStateException("PLAIN: this mechanism supports neither integrity nor privacy");
        }
        throw new IllegalStateException("PLAIN: authentication not completed");
    }

    public Object getNegotiatedProperty(String str) {
        if (!this.completed) {
            throw new IllegalStateException("PLAIN: authentication not completed");
        } else if (str.equals("javax.security.sasl.qop")) {
            return "auth";
        } else {
            return null;
        }
    }

    private void clearPassword() {
        if (this.password != null) {
            int i = 0;
            while (true) {
                byte[] bArr = this.password;
                if (i < bArr.length) {
                    bArr[i] = 0;
                    i++;
                } else {
                    this.password = null;
                    return;
                }
            }
        }
    }

    public void dispose() throws SaslException {
        clearPassword();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        clearPassword();
    }

    private Object[] getUserInfo() throws SaslException {
        byte[] bArr;
        try {
            NameCallback nameCallback = new NameCallback("PLAIN authentication id: ");
            PasswordCallback passwordCallback = new PasswordCallback("PLAIN password: ", false);
            this.cbh.handle(new Callback[]{nameCallback, passwordCallback});
            String name = nameCallback.getName();
            char[] password2 = passwordCallback.getPassword();
            if (password2 != null) {
                bArr = new String(password2).getBytes("UTF8");
                passwordCallback.clearPassword();
            } else {
                bArr = null;
            }
            return new Object[]{name, bArr};
        } catch (IOException e) {
            throw new SaslException("Cannot get password", e);
        } catch (UnsupportedCallbackException e2) {
            throw new SaslException("Cannot get userid/password", e2);
        }
    }
}
