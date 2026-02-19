package org.xbill.DNS;

import com.google.android.gms.nearby.messages.Strategy;
import java.security.PrivateKey;
import java.util.Date;
import org.xbill.DNS.DNSSEC;

public class SIG0 {
    private static final short VALIDITY = 300;

    private SIG0() {
    }

    public static void signMessage(Message message, KEYRecord kEYRecord, PrivateKey privateKey, SIGRecord sIGRecord) throws DNSSEC.DNSSECException {
        int intValue = Options.intValue("sig0validity");
        if (intValue < 0) {
            intValue = Strategy.TTL_SECONDS_DEFAULT;
        }
        long currentTimeMillis = System.currentTimeMillis();
        message.addRecord(DNSSEC.signMessage(message, sIGRecord, kEYRecord, privateKey, new Date(currentTimeMillis), new Date(currentTimeMillis + ((long) (intValue * 1000)))), 3);
    }

    public static void verifyMessage(Message message, byte[] bArr, KEYRecord kEYRecord, SIGRecord sIGRecord) throws DNSSEC.DNSSECException {
        SIGRecord sIGRecord2;
        Record[] sectionArray = message.getSectionArray(3);
        int i = 0;
        while (true) {
            if (i >= sectionArray.length) {
                sIGRecord2 = null;
                break;
            } else if (sectionArray[i].getType() == 24 && ((SIGRecord) sectionArray[i]).getTypeCovered() == 0) {
                sIGRecord2 = (SIGRecord) sectionArray[i];
                break;
            } else {
                i++;
            }
        }
        DNSSEC.verifyMessage(message, bArr, sIGRecord2, sIGRecord, kEYRecord);
    }
}
