package org.jivesoftware.smack.sasl;

import org.jivesoftware.smack.SASLAuthentication;

public class SASLExternalMechanism extends SASLMechanism {
    /* access modifiers changed from: protected */
    public String getName() {
        return "EXTERNAL";
    }

    public SASLExternalMechanism(SASLAuthentication sASLAuthentication) {
        super(sASLAuthentication);
    }
}
