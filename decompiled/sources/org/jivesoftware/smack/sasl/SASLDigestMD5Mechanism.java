package org.jivesoftware.smack.sasl;

import org.jivesoftware.smack.SASLAuthentication;

public class SASLDigestMD5Mechanism extends SASLMechanism {
    /* access modifiers changed from: protected */
    public String getName() {
        return "DIGEST-MD5";
    }

    public SASLDigestMD5Mechanism(SASLAuthentication sASLAuthentication) {
        super(sASLAuthentication);
    }
}
