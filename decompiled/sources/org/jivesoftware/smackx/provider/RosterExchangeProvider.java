package org.jivesoftware.smackx.provider;

import java.util.ArrayList;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.RemoteRosterEntry;
import org.jivesoftware.smackx.packet.RosterExchange;
import org.xmlpull.v1.XmlPullParser;

public class RosterExchangeProvider implements PacketExtensionProvider {
    public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        RosterExchange rosterExchange = new RosterExchange();
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String str = "";
        String str2 = str;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    arrayList = new ArrayList();
                    str = xmlPullParser.getAttributeValue("", "jid");
                    str2 = xmlPullParser.getAttributeValue("", "name");
                }
                if (xmlPullParser.getName().equals("group")) {
                    arrayList.add(xmlPullParser.nextText());
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("item")) {
                    rosterExchange.addRosterEntry(new RemoteRosterEntry(str, str2, (String[]) arrayList.toArray(new String[arrayList.size()])));
                }
                if (xmlPullParser.getName().equals(GroupChatInvitation.ELEMENT_NAME)) {
                    z = true;
                }
            }
        }
        return rosterExchange;
    }
}
