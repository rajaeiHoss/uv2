package org.ksoap2;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.io.IOException;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class SoapFault extends IOException {
    private static final long serialVersionUID = 1011001;
    public Node detail;
    public String faultactor;
    public String faultcode;
    public String faultstring;
    public int version;

    public SoapFault() {
        this.version = 110;
    }

    public SoapFault(int i) {
        this.version = i;
    }

    public void parse(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, SoapEnvelope.ENV, "Fault");
        while (xmlPullParser.nextTag() == 2) {
            String name = xmlPullParser.getName();
            if (name.equals(ProductAction.ACTION_DETAIL)) {
                Node node = new Node();
                this.detail = node;
                node.parse(xmlPullParser);
                if (xmlPullParser.getNamespace().equals(SoapEnvelope.ENV) && xmlPullParser.getName().equals("Fault")) {
                    break;
                }
            } else {
                if (name.equals("faultcode")) {
                    this.faultcode = xmlPullParser.nextText();
                } else if (name.equals("faultstring")) {
                    this.faultstring = xmlPullParser.nextText();
                } else if (name.equals("faultactor")) {
                    this.faultactor = xmlPullParser.nextText();
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("unexpected tag:");
                    stringBuffer.append(name);
                    throw new RuntimeException(stringBuffer.toString());
                }
                xmlPullParser.require(3, (String) null, name);
            }
        }
        xmlPullParser.require(3, SoapEnvelope.ENV, "Fault");
        xmlPullParser.nextTag();
    }

    public void write(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(SoapEnvelope.ENV, "Fault");
        xmlSerializer.startTag((String) null, "faultcode");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("");
        stringBuffer.append(this.faultcode);
        xmlSerializer.text(stringBuffer.toString());
        xmlSerializer.endTag((String) null, "faultcode");
        xmlSerializer.startTag((String) null, "faultstring");
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("");
        stringBuffer2.append(this.faultstring);
        xmlSerializer.text(stringBuffer2.toString());
        xmlSerializer.endTag((String) null, "faultstring");
        xmlSerializer.startTag((String) null, ProductAction.ACTION_DETAIL);
        Node node = this.detail;
        if (node != null) {
            node.write(xmlSerializer);
        }
        xmlSerializer.endTag((String) null, ProductAction.ACTION_DETAIL);
        xmlSerializer.endTag(SoapEnvelope.ENV, "Fault");
    }

    public String getMessage() {
        return this.faultstring;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SoapFault - faultcode: '");
        stringBuffer.append(this.faultcode);
        stringBuffer.append("' faultstring: '");
        stringBuffer.append(this.faultstring);
        stringBuffer.append("' faultactor: '");
        stringBuffer.append(this.faultactor);
        stringBuffer.append("' detail: ");
        stringBuffer.append(this.detail);
        return stringBuffer.toString();
    }
}
