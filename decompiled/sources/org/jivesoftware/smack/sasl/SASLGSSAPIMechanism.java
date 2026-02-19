package org.jivesoftware.smack.sasl;

import de.measite.smack.Sasl;
import java.io.IOException;
import java.util.HashMap;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;

public class SASLGSSAPIMechanism extends SASLMechanism {
    /* access modifiers changed from: protected */
    public String getName() {
        return "GSSAPI";
    }

    public SASLGSSAPIMechanism(SASLAuthentication sASLAuthentication) {
        super(sASLAuthentication);
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty("java.security.auth.login.config", "gss.conf");
    }

    public void authenticate(String str, String str2, CallbackHandler callbackHandler) throws IOException, XMPPException {
        String[] strArr = {getName()};
        HashMap hashMap = new HashMap();
        hashMap.put("javax.security.sasl.server.authentication", "TRUE");
        this.sc = Sasl.createSaslClient(strArr, str, "xmpp", str2, hashMap, callbackHandler);
        authenticate();
    }

    public void authenticate(String str, String str2, String str3) throws IOException, XMPPException {
        String[] strArr = {getName()};
        HashMap hashMap = new HashMap();
        hashMap.put("javax.security.sasl.server.authentication", "TRUE");
        this.sc = Sasl.createSaslClient(strArr, str, "xmpp", str2, hashMap, this);
        authenticate();
    }
}
