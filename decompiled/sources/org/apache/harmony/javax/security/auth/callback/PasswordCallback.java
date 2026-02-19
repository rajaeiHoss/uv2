package org.apache.harmony.javax.security.auth.callback;

import java.io.Serializable;
import java.util.Arrays;

public class PasswordCallback implements Callback, Serializable {
    private static final long serialVersionUID = 2267422647454909926L;
    boolean echoOn;
    private char[] inputPassword;
    private String prompt;

    private void setPrompt(String str) throws IllegalArgumentException {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        }
        this.prompt = str;
    }

    public PasswordCallback(String str, boolean z) {
        setPrompt(str);
        this.echoOn = z;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public boolean isEchoOn() {
        return this.echoOn;
    }

    public void setPassword(char[] cArr) {
        if (cArr == null) {
            this.inputPassword = cArr;
            return;
        }
        char[] cArr2 = new char[cArr.length];
        this.inputPassword = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr2.length);
    }

    public char[] getPassword() {
        char[] cArr = this.inputPassword;
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        char[] cArr2 = new char[length];
        System.arraycopy(cArr, 0, cArr2, 0, length);
        return cArr2;
    }

    public void clearPassword() {
        char[] cArr = this.inputPassword;
        if (cArr != null) {
            Arrays.fill(cArr, '\0');
        }
    }
}
