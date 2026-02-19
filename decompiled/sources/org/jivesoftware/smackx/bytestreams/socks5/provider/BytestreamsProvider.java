package org.jivesoftware.smackx.bytestreams.socks5.provider;

import com.google.android.gms.actions.SearchIntents;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.xmlpull.v1.XmlPullParser;

public class BytestreamsProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        Bytestream bytestream = new Bytestream();
        String attributeValue = xmlPullParser.getAttributeValue("", "sid");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "mode");
        boolean z = false;
        while (true) {
            String str = null;
            String str2 = null;
            String str3 = null;
            while (!z) {
                int next = xmlPullParser.next();
                String name = xmlPullParser.getName();
                if (next == 2) {
                    if (name.equals(Bytestream.StreamHost.ELEMENTNAME)) {
                        str2 = xmlPullParser.getAttributeValue("", "jid");
                        str3 = xmlPullParser.getAttributeValue("", "host");
                        str = xmlPullParser.getAttributeValue("", "port");
                    } else if (name.equals(Bytestream.StreamHostUsed.ELEMENTNAME)) {
                        bytestream.setUsedHost(xmlPullParser.getAttributeValue("", "jid"));
                    } else if (name.equals(Bytestream.Activate.ELEMENTNAME)) {
                        bytestream.setToActivate(xmlPullParser.getAttributeValue("", "jid"));
                    }
                } else if (next != 3) {
                    continue;
                } else if (name.equals("streamhost")) {
                    if (str == null) {
                        bytestream.addStreamHost(str2, str3);
                    } else {
                        bytestream.addStreamHost(str2, str3, Integer.parseInt(str));
                    }
                } else if (name.equals(SearchIntents.EXTRA_QUERY)) {
                    z = true;
                }
            }
            bytestream.setMode(Bytestream.Mode.fromName(attributeValue2));
            bytestream.setSessionID(attributeValue);
            return bytestream;
        }
    }
}
