package org.jivesoftware.smackx.provider;

import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

public class PEPProvider implements PacketExtensionProvider {
    Map<String, PacketExtensionProvider> nodeParsers = new HashMap();
    PacketExtension pepItem;

    public void registerPEPParserExtension(String str, PacketExtensionProvider packetExtensionProvider) {
        this.nodeParsers.put(str, packetExtensionProvider);
    }

    public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        PacketExtensionProvider packetExtensionProvider;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (!xmlPullParser.getName().equals("event") && xmlPullParser.getName().equals("items") && (packetExtensionProvider = this.nodeParsers.get(xmlPullParser.getAttributeValue("", "node"))) != null) {
                    this.pepItem = packetExtensionProvider.parseExtension(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("event")) {
                z = true;
            }
        }
        return this.pepItem;
    }
}
