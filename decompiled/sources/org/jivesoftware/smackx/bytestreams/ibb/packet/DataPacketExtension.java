package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class DataPacketExtension implements PacketExtension {
    public static final String ELEMENT_NAME = "data";
    private final String data;
    private byte[] decodedData;
    private final long seq;
    private final String sessionID;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return InBandBytestreamManager.NAMESPACE;
    }

    public DataPacketExtension(String str, long j, String str2) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        } else if (j < 0 || j > 65535) {
            throw new IllegalArgumentException("Sequence must not be between 0 and 65535");
        } else if (str2 != null) {
            this.sessionID = str;
            this.seq = j;
            this.data = str2;
        } else {
            throw new IllegalArgumentException("Data must not be null");
        }
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public long getSeq() {
        return this.seq;
    }

    public String getData() {
        return this.data;
    }

    public byte[] getDecodedData() {
        byte[] bArr = this.decodedData;
        if (bArr != null) {
            return bArr;
        }
        if (this.data.matches(".*={1,2}+.+")) {
            return null;
        }
        byte[] decodeBase64 = StringUtils.decodeBase64(this.data);
        this.decodedData = decodeBase64;
        return decodeBase64;
    }

    public String toXML() {
        return "<" + getElementName() + " " + "xmlns=\"" + InBandBytestreamManager.NAMESPACE + "\" " + "seq=\"" + this.seq + "\" " + "sid=\"" + this.sessionID + "\">" + this.data + "</" + getElementName() + ">";
    }
}
