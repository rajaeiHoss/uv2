package org.jivesoftware.smackx.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.AppMeasurement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.xmlpull.v1.XmlPullParser;

public class DiscoverInfoProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        DiscoverInfo discoverInfo = new DiscoverInfo();
        discoverInfo.setNode(xmlPullParser.getAttributeValue("", "node"));
        boolean z = false;
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("identity")) {
                    str = xmlPullParser.getAttributeValue("", "category");
                    str2 = xmlPullParser.getAttributeValue("", "name");
                    str3 = xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE);
                } else if (xmlPullParser.getName().equals("feature")) {
                    str4 = xmlPullParser.getAttributeValue("", "var");
                } else {
                    discoverInfo.addExtension(PacketParserUtils.parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("identity")) {
                    DiscoverInfo.Identity identity = new DiscoverInfo.Identity(str, str2);
                    identity.setType(str3);
                    discoverInfo.addIdentity(identity);
                }
                if (xmlPullParser.getName().equals("feature")) {
                    discoverInfo.addFeature(str4);
                }
                if (xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z = true;
                }
            }
        }
        return discoverInfo;
    }
}
