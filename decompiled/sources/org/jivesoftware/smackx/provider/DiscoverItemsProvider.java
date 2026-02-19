package org.jivesoftware.smackx.provider;

import com.google.android.gms.actions.SearchIntents;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.xmlpull.v1.XmlPullParser;

public class DiscoverItemsProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        DiscoverItems discoverItems = new DiscoverItems();
        discoverItems.setNode(xmlPullParser.getAttributeValue("", "node"));
        boolean z = false;
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2 && "item".equals(xmlPullParser.getName())) {
                str = xmlPullParser.getAttributeValue("", "jid");
                str2 = xmlPullParser.getAttributeValue("", "name");
                str3 = xmlPullParser.getAttributeValue("", "node");
                str4 = xmlPullParser.getAttributeValue("", "action");
            } else if (next == 3 && "item".equals(xmlPullParser.getName())) {
                DiscoverItems.Item item = new DiscoverItems.Item(str);
                item.setName(str2);
                item.setNode(str3);
                item.setAction(str4);
                discoverItems.addItem(item);
            } else if (next == 3 && SearchIntents.EXTRA_QUERY.equals(xmlPullParser.getName())) {
                z = true;
            }
        }
        return discoverItems;
    }
}
