package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class Open extends IQ {
    private final int blockSize;
    private final String sessionID;
    private final InBandBytestreamManager.StanzaType stanza;

    public Open(String str, int i, InBandBytestreamManager.StanzaType stanzaType) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        } else if (i > 0) {
            this.sessionID = str;
            this.blockSize = i;
            this.stanza = stanzaType;
            setType(IQ.Type.SET);
        } else {
            throw new IllegalArgumentException("Block size must be greater than zero");
        }
    }

    public Open(String str, int i) {
        this(str, i, InBandBytestreamManager.StanzaType.IQ);
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public InBandBytestreamManager.StanzaType getStanza() {
        return this.stanza;
    }

    public String getChildElementXML() {
        return "<open " + "xmlns=\"" + InBandBytestreamManager.NAMESPACE + "\" " + "block-size=\"" + this.blockSize + "\" " + "sid=\"" + this.sessionID + "\" " + "stanza=\"" + this.stanza.toString().toLowerCase() + "\"" + "/>";
    }
}
