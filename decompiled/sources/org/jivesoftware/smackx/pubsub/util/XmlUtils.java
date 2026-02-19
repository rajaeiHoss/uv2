package org.jivesoftware.smackx.pubsub.util;

public class XmlUtils {
    public static void appendAttribute(StringBuilder sb, String str, String str2) {
        sb.append(" ");
        sb.append(str);
        sb.append("='");
        sb.append(str2);
        sb.append("'");
    }
}
