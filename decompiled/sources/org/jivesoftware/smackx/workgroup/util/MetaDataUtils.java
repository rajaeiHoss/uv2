package org.jivesoftware.smackx.workgroup.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.workgroup.MetaData;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MetaDataUtils {
    public static Map parseMetaData(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2 || !xmlPullParser.getName().equals(MetaData.ELEMENT_NAME) || !xmlPullParser.getNamespace().equals("http://jivesoftware.com/protocol/workgroup")) {
            return Collections.EMPTY_MAP;
        }
        Hashtable hashtable = new Hashtable();
        int nextTag = xmlPullParser.nextTag();
        while (true) {
            if (nextTag == 3 && xmlPullParser.getName().equals(MetaData.ELEMENT_NAME)) {
                return hashtable;
            }
            String attributeValue = xmlPullParser.getAttributeValue(0);
            String nextText = xmlPullParser.nextText();
            if (hashtable.containsKey(attributeValue)) {
                ((List) hashtable.get(attributeValue)).add(nextText);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(nextText);
                hashtable.put(attributeValue, arrayList);
            }
            nextTag = xmlPullParser.nextTag();
        }
    }

    public static String serializeMetaData(Map map) {
        StringBuilder sb = new StringBuilder();
        if (map != null && map.size() > 0) {
            sb.append("<metadata xmlns=\"http://jivesoftware.com/protocol/workgroup\">");
            for (Object next : map.keySet()) {
                Object obj = map.get(next);
                if (obj instanceof List) {
                    for (Object value : (List) obj) {
                        String escapeForXML = (String) value;
                        sb.append("<value name=\"");
                        sb.append(next);
                        sb.append("\">");
                        sb.append(StringUtils.escapeForXML(escapeForXML));
                        sb.append("</value>");
                    }
                } else if (obj instanceof String) {
                    sb.append("<value name=\"");
                    sb.append(next);
                    sb.append("\">");
                    sb.append(StringUtils.escapeForXML((String) obj));
                    sb.append("</value>");
                }
            }
            sb.append("</metadata>");
        }
        return sb.toString();
    }
}
