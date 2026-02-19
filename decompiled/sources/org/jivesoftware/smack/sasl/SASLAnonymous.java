package org.jivesoftware.smack.sasl;

import java.io.IOException;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.sasl.SASLMechanism;

public class SASLAnonymous extends SASLMechanism {
    /* access modifiers changed from: protected */
    public String getName() {
        return "ANONYMOUS";
    }

    public SASLAnonymous(SASLAuthentication sASLAuthentication) {
        super(sASLAuthentication);
    }

    public void authenticate(String str, String str2, CallbackHandler callbackHandler) throws IOException {
        authenticate();
    }

    public void authenticate(String str, String str2, String str3) throws IOException {
        authenticate();
    }

    /* access modifiers changed from: protected */
    public void authenticate() throws IOException {
        getSASLAuthentication().send(new SASLMechanism.AuthMechanism(getName(), (String) null));
    }

    public void challengeReceived(String str) throws IOException {
        getSASLAuthentication().send(new SASLMechanism.Response());
    }
}
