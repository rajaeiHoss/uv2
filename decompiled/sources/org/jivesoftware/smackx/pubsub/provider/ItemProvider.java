package org.jivesoftware.smackx.pubsub.provider;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.xmlpull.v1.XmlPullParser;

public class ItemProvider implements PacketExtensionProvider {
    public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "id");
        String name = xmlPullParser.getName();
        int next = xmlPullParser.next();
        if (next == 3) {
            return new Item(attributeValue);
        }
        String name2 = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        if (ProviderManager.getInstance().getExtensionProvider(name2, namespace) != null) {
            return new PayloadItem(attributeValue, PacketParserUtils.parsePacketExtension(name2, namespace, xmlPullParser));
        }
        boolean z = false;
        StringBuilder sb = new StringBuilder();
        while (!z) {
            if (next == 3 && xmlPullParser.getName().equals(name)) {
                z = true;
            } else if (next != 2 || !xmlPullParser.isEmptyElementTag()) {
                sb.append(xmlPullParser.getText());
            }
            if (!z) {
                next = xmlPullParser.next();
            }
        }
        return new PayloadItem(attributeValue, new SimplePayload(name2, namespace, sb.toString()));
    }
}
