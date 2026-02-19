package org.jivesoftware.smackx.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.packet.MUCOwner;
import org.jivesoftware.smackx.packet.Nick;
import org.xmlpull.v1.XmlPullParser;

public class MUCOwnerProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        MUCOwner mUCOwner = new MUCOwner();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    mUCOwner.addItem(parseItem(xmlPullParser));
                } else if (xmlPullParser.getName().equals("destroy")) {
                    mUCOwner.setDestroy(parseDestroy(xmlPullParser));
                } else {
                    mUCOwner.addExtension(PacketParserUtils.parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z = true;
            }
        }
        return mUCOwner;
    }

    private MUCOwner.Item parseItem(XmlPullParser xmlPullParser) throws Exception {
        MUCOwner.Item item = new MUCOwner.Item(xmlPullParser.getAttributeValue("", FirebaseAnalytics.Param.AFFILIATION));
        item.setNick(xmlPullParser.getAttributeValue("", Nick.ELEMENT_NAME));
        item.setRole(xmlPullParser.getAttributeValue("", "role"));
        item.setJid(xmlPullParser.getAttributeValue("", "jid"));
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("actor")) {
                    item.setActor(xmlPullParser.getAttributeValue("", "jid"));
                }
                if (xmlPullParser.getName().equals("reason")) {
                    item.setReason(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z = true;
            }
        }
        return item;
    }

    private MUCOwner.Destroy parseDestroy(XmlPullParser xmlPullParser) throws Exception {
        MUCOwner.Destroy destroy = new MUCOwner.Destroy();
        destroy.setJid(xmlPullParser.getAttributeValue("", "jid"));
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    destroy.setReason(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("destroy")) {
                z = true;
            }
        }
        return destroy;
    }
}
