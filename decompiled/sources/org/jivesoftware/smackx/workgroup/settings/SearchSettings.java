package org.jivesoftware.smackx.workgroup.settings;

import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.workgroup.util.ModelUtil;
import org.xmlpull.v1.XmlPullParser;

public class SearchSettings extends IQ {
    public static final String ELEMENT_NAME = "search-settings";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String forumsLocation;
    private String kbLocation;

    public boolean isSearchEnabled() {
        return ModelUtil.hasLength(getForumsLocation()) && ModelUtil.hasLength(getKbLocation());
    }

    public String getForumsLocation() {
        return this.forumsLocation;
    }

    public void setForumsLocation(String str) {
        this.forumsLocation = str;
    }

    public String getKbLocation() {
        return this.kbLocation;
    }

    public void setKbLocation(String str) {
        this.kbLocation = str;
    }

    public boolean hasKB() {
        return ModelUtil.hasLength(getKbLocation());
    }

    public boolean hasForums() {
        return ModelUtil.hasLength(getForumsLocation());
    }

    public String getChildElementXML() {
        return "<" + ELEMENT_NAME + " xmlns=" + Typography.quote + "http://jivesoftware.com/protocol/workgroup" + Typography.quote + "></" + ELEMENT_NAME + "> ";
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                SearchSettings searchSettings = new SearchSettings();
                boolean z = false;
                String str = null;
                String str2 = null;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "forums".equals(xmlPullParser.getName())) {
                        str = xmlPullParser.nextText();
                    } else if (next == 2 && "kb".equals(xmlPullParser.getName())) {
                        str2 = xmlPullParser.nextText();
                    } else if (next == 3 && SearchSettings.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                searchSettings.setForumsLocation(str);
                searchSettings.setKbLocation(str2);
                return searchSettings;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }
    }
}
