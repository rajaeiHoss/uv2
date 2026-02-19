package org.jivesoftware.smack.sasl;

import org.apache.qpid.management.common.sasl.Constants;
import org.jivesoftware.smack.SASLAuthentication;

public class SASLCramMD5Mechanism extends SASLMechanism {
    /* access modifiers changed from: protected */
    public String getName() {
        return Constants.MECH_CRAMMD5;
    }

    public SASLCramMD5Mechanism(SASLAuthentication sASLAuthentication) {
        super(sASLAuthentication);
    }
}
