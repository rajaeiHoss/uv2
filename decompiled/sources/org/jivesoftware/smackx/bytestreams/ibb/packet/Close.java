package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class Close extends IQ {
    private final String sessionID;

    public Close(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        }
        this.sessionID = str;
        setType(IQ.Type.SET);
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public String getChildElementXML() {
        return "<close " + "xmlns=\"" + InBandBytestreamManager.NAMESPACE + "\" " + "sid=\"" + this.sessionID + "\"" + "/>";
    }
}
