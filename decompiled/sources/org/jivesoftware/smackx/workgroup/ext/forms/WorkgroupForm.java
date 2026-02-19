package org.jivesoftware.smackx.workgroup.ext.forms;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;

public class WorkgroupForm extends IQ {
    public static final String ELEMENT_NAME = "workgroup-form";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";

    public String getChildElementXML() {
        return "<" + ELEMENT_NAME + " xmlns=\"" + "http://jivesoftware.com/protocol/workgroup" + "\">" + getExtensionsXML() + "</" + ELEMENT_NAME + "> ";
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            WorkgroupForm workgroupForm = new WorkgroupForm();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    workgroupForm.addExtension(PacketParserUtils.parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                } else if (next == 3 && xmlPullParser.getName().equals(WorkgroupForm.ELEMENT_NAME)) {
                    z = true;
                }
            }
            return workgroupForm;
        }
    }
}
