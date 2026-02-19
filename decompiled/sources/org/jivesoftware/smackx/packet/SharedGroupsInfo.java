package org.jivesoftware.smackx.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class SharedGroupsInfo extends IQ {
    private List groups = new ArrayList();

    public List getGroups() {
        return this.groups;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<sharedgroup xmlns=\"http://www.jivesoftware.org/protocol/sharedgroup\">");
        for (Object append : this.groups) {
            sb.append("<group>");
            sb.append(append);
            sb.append("</group>");
        }
        sb.append("</sharedgroup>");
        return sb.toString();
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            SharedGroupsInfo sharedGroupsInfo = new SharedGroupsInfo();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2 && xmlPullParser.getName().equals("group")) {
                    sharedGroupsInfo.getGroups().add(xmlPullParser.nextText());
                } else if (next == 3 && xmlPullParser.getName().equals("sharedgroup")) {
                    z = true;
                }
            }
            return sharedGroupsInfo;
        }
    }
}
