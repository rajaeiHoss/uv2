package org.kobjects.rss;

import com.google.android.gms.plus.PlusShare;
import java.io.IOException;
import java.io.Reader;
import org.kobjects.xml.XmlReader;

public class RssReader {
    public static final int AUTHOR = 4;
    public static final int DATE = 3;
    public static final int DESCRIPTION = 2;
    public static final int LINK = 1;
    public static final int TITLE = 0;
    XmlReader xr;

    public RssReader(Reader reader) throws IOException {
        this.xr = new XmlReader(reader);
    }

    /* access modifiers changed from: package-private */
    public void readText(StringBuffer stringBuffer) throws IOException {
        while (this.xr.next() != 3) {
            int type = this.xr.getType();
            if (type == 2) {
                readText(stringBuffer);
            } else if (type == 4) {
                stringBuffer.append(this.xr.getText());
            }
        }
    }

    public String[] next() throws IOException {
        String[] strArr = new String[5];
        while (this.xr.next() != 1) {
            if (this.xr.getType() == 2) {
                String lowerCase = this.xr.getName().toLowerCase();
                if (lowerCase.equals("item") || lowerCase.endsWith(":item")) {
                    while (this.xr.next() != 3) {
                        if (this.xr.getType() == 2) {
                            String lowerCase2 = this.xr.getName().toLowerCase();
                            int indexOf = lowerCase2.indexOf(":");
                            if (indexOf != -1) {
                                lowerCase2 = lowerCase2.substring(indexOf + 1);
                            }
                            StringBuffer stringBuffer = new StringBuffer();
                            readText(stringBuffer);
                            String stringBuffer2 = stringBuffer.toString();
                            if (lowerCase2.equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                                strArr[0] = stringBuffer2;
                            } else if (lowerCase2.equals("link")) {
                                strArr[1] = stringBuffer2;
                            } else if (lowerCase2.equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)) {
                                strArr[2] = stringBuffer2;
                            } else if (lowerCase2.equals("date")) {
                                strArr[3] = stringBuffer2;
                            } else if (lowerCase2.equals("author")) {
                                strArr[4] = stringBuffer2;
                            }
                        }
                    }
                    return strArr;
                }
            }
        }
        return null;
    }
}
