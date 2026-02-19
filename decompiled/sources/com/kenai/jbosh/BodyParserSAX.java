package com.kenai.jbosh;

import com.streamax.config.constant.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

final class BodyParserSAX implements BodyParser {
    /* access modifiers changed from: private */
    public static final Logger LOG = Logger.getLogger(BodyParserSAX.class.getName());
    private static final ThreadLocal<SoftReference<SAXParser>> PARSER = new ThreadLocal<SoftReference<SAXParser>>() {
        /* access modifiers changed from: protected */
        public SoftReference<SAXParser> initialValue() {
            return new SoftReference<SAXParser>(null);
        }
    };
    private static final SAXParserFactory SAX_FACTORY;

    BodyParserSAX() {
    }

    static {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        SAX_FACTORY = newInstance;
        newInstance.setNamespaceAware(true);
        newInstance.setValidating(false);
    }

    private static final class Handler extends DefaultHandler {
        private String defaultNS;
        private final SAXParser parser;
        private final BodyParserResults result;

        private Handler(SAXParser sAXParser, BodyParserResults bodyParserResults) {
            this.defaultNS = null;
            this.parser = sAXParser;
            this.result = bodyParserResults;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if (BodyParserSAX.LOG.isLoggable(Level.FINEST)) {
                Logger access$000 = BodyParserSAX.LOG;
                access$000.finest("Start element: " + str3);
                Logger access$0002 = BodyParserSAX.LOG;
                access$0002.finest("    URI: " + str);
                Logger access$0003 = BodyParserSAX.LOG;
                access$0003.finest("    local: " + str2);
            }
            BodyQName bodyQName = AbstractBody.getBodyQName();
            if (!bodyQName.getNamespaceURI().equals(str) || !bodyQName.getLocalPart().equals(str2)) {
                throw new IllegalStateException("Root element was not '" + bodyQName.getLocalPart() + "' in the '" + bodyQName.getNamespaceURI() + "' namespace.  (Was '" + str2 + "' in '" + str + "')");
            }
            for (int i = 0; i < attributes.getLength(); i++) {
                String uri = attributes.getURI(i);
                if (uri.length() == 0) {
                    uri = this.defaultNS;
                }
                String localName = attributes.getLocalName(i);
                String value = attributes.getValue(i);
                if (BodyParserSAX.LOG.isLoggable(Level.FINEST)) {
                    Logger access$0004 = BodyParserSAX.LOG;
                    access$0004.finest("    Attribute: {" + uri + Constants.JsonSstringSuffix + localName + " = '" + value + "'");
                }
                this.result.addBodyAttributeValue(BodyQName.create(uri, localName), value);
            }
            this.parser.reset();
        }

        public void startPrefixMapping(String str, String str2) {
            if (str.length() == 0) {
                if (BodyParserSAX.LOG.isLoggable(Level.FINEST)) {
                    Logger access$000 = BodyParserSAX.LOG;
                    access$000.finest("Prefix mapping: <DEFAULT> => " + str2);
                }
                this.defaultNS = str2;
            } else if (BodyParserSAX.LOG.isLoggable(Level.FINEST)) {
                Logger access$0002 = BodyParserSAX.LOG;
                access$0002.info("Prefix mapping: " + str + " => " + str2);
            }
        }
    }

    public BodyParserResults parse(String str) throws BOSHException {
        BodyParserResults bodyParserResults = new BodyParserResults();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            SAXParser sAXParser = getSAXParser();
            sAXParser.parse(byteArrayInputStream, new Handler(sAXParser, bodyParserResults));
            return bodyParserResults;
        } catch (IOException | SAXException e) {
            throw new BOSHException("Could not parse body:\n" + str, e);
        }
    }

    private static SAXParser getSAXParser() {
        ThreadLocal<SoftReference<SAXParser>> threadLocal = PARSER;
        SAXParser sAXParser = (SAXParser) threadLocal.get().get();
        if (sAXParser == null) {
            try {
                SAXParser newSAXParser = SAX_FACTORY.newSAXParser();
                threadLocal.set(new SoftReference<>(newSAXParser));
                return newSAXParser;
            } catch (ParserConfigurationException | SAXException e) {
                throw new IllegalStateException("Could not create SAX parser", e);
            }
        } else {
            sAXParser.reset();
            return sAXParser;
        }
    }
}
