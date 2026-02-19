package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.IQ;

public class Data extends IQ {
    private final DataPacketExtension dataPacketExtension;

    public Data(DataPacketExtension dataPacketExtension2) {
        if (dataPacketExtension2 != null) {
            this.dataPacketExtension = dataPacketExtension2;
            addExtension(dataPacketExtension2);
            setType(IQ.Type.SET);
            return;
        }
        throw new IllegalArgumentException("Data must not be null");
    }

    public DataPacketExtension getDataPacketExtension() {
        return this.dataPacketExtension;
    }

    public String getChildElementXML() {
        return this.dataPacketExtension.toXML();
    }
}
