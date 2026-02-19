package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ;

public class Session extends IQ {
    public String getChildElementXML() {
        return "<session xmlns=\"urn:ietf:params:xml:ns:xmpp-session\"/>";
    }

    public Session() {
        setType(IQ.Type.SET);
    }
}
