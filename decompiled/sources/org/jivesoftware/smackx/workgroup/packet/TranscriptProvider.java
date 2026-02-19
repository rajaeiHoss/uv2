package org.jivesoftware.smackx.workgroup.packet;

import com.streamax.client.CommonUtilities;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;

public class TranscriptProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "sessionID");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(CommonUtilities.EXTRA_MESSAGE)) {
                    arrayList.add(PacketParserUtils.parseMessage(xmlPullParser));
                } else if (xmlPullParser.getName().equals("presence")) {
                    arrayList.add(PacketParserUtils.parsePresence(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("transcript")) {
                z = true;
            }
        }
        return new Transcript(attributeValue, arrayList);
    }
}
