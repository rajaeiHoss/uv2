package org.jivesoftware.smackx.provider;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.packet.CapsExtension;
import org.xmlpull.v1.XmlPullParser;

public class CapsExtensionProvider implements PacketExtensionProvider {
    public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (!z) {
            if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equalsIgnoreCase(CapsExtension.NODE_NAME)) {
                str3 = xmlPullParser.getAttributeValue((String) null, "hash");
                str2 = xmlPullParser.getAttributeValue((String) null, "ver");
                str = xmlPullParser.getAttributeValue((String) null, "node");
            }
            if (xmlPullParser.getEventType() != 3 || !xmlPullParser.getName().equalsIgnoreCase(CapsExtension.NODE_NAME)) {
                xmlPullParser.next();
            } else {
                z = true;
            }
        }
        return new CapsExtension(str, str2, str3);
    }
}
