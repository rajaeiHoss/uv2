package org.jivesoftware.smackx.provider;

import com.google.android.gms.measurement.AppMeasurement;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.packet.MessageEvent;
import org.jivesoftware.smackx.packet.MultipleAddresses;
import org.xmlpull.v1.XmlPullParser;

public class MultipleAddressesProvider implements PacketExtensionProvider {
    public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        MultipleAddresses multipleAddresses = new MultipleAddresses();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("address")) {
                    multipleAddresses.addAddress(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE), xmlPullParser.getAttributeValue("", "jid"), xmlPullParser.getAttributeValue("", "node"), xmlPullParser.getAttributeValue("", "desc"), "true".equals(xmlPullParser.getAttributeValue("", MessageEvent.DELIVERED)), xmlPullParser.getAttributeValue("", "uri"));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(multipleAddresses.getElementName())) {
                z = true;
            }
        }
        return multipleAddresses;
    }
}
