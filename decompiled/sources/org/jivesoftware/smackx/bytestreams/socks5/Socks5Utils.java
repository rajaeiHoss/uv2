package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.IOException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.util.StringUtils;

class Socks5Utils {
    Socks5Utils() {
    }

    public static String createDigest(String str, String str2, String str3) {
        return StringUtils.hash(str + str2 + str3);
    }

    public static byte[] receiveSocks5Message(DataInputStream dataInputStream) throws IOException, XMPPException {
        byte[] bArr = new byte[5];
        dataInputStream.readFully(bArr, 0, 5);
        if (bArr[3] == 3) {
            byte b = bArr[4];
            byte[] bArr2 = new byte[(b + 7)];
            System.arraycopy(bArr, 0, bArr2, 0, 5);
            dataInputStream.readFully(bArr2, 5, b + 2);
            return bArr2;
        }
        throw new XMPPException("Unsupported SOCKS5 address type");
    }
}
