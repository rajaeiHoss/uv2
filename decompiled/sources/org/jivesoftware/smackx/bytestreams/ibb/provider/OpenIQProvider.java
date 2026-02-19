package org.jivesoftware.smackx.bytestreams.ibb.provider;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.xmlpull.v1.XmlPullParser;

public class OpenIQProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        InBandBytestreamManager.StanzaType stanzaType;
        String attributeValue = xmlPullParser.getAttributeValue("", "sid");
        int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue("", "block-size"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "stanza");
        if (attributeValue2 == null) {
            stanzaType = InBandBytestreamManager.StanzaType.IQ;
        } else {
            stanzaType = InBandBytestreamManager.StanzaType.valueOf(attributeValue2.toUpperCase());
        }
        return new Open(attributeValue, parseInt, stanzaType);
    }
}
