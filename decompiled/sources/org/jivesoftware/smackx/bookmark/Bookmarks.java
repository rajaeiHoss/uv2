package org.jivesoftware.smackx.bookmark;

import com.google.android.gms.plus.PlusShare;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.packet.Nick;
import org.jivesoftware.smackx.packet.PrivateData;
import org.jivesoftware.smackx.provider.PrivateDataProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Bookmarks implements PrivateData {
    private List<BookmarkedConference> bookmarkedConferences = new ArrayList();
    private List<BookmarkedURL> bookmarkedURLS = new ArrayList();

    public String getElementName() {
        return "storage";
    }

    public String getNamespace() {
        return "storage:bookmarks";
    }

    public void addBookmarkedURL(BookmarkedURL bookmarkedURL) {
        this.bookmarkedURLS.add(bookmarkedURL);
    }

    public void removeBookmarkedURL(BookmarkedURL bookmarkedURL) {
        this.bookmarkedURLS.remove(bookmarkedURL);
    }

    public void clearBookmarkedURLS() {
        this.bookmarkedURLS.clear();
    }

    public void addBookmarkedConference(BookmarkedConference bookmarkedConference) {
        this.bookmarkedConferences.add(bookmarkedConference);
    }

    public void removeBookmarkedConference(BookmarkedConference bookmarkedConference) {
        this.bookmarkedConferences.remove(bookmarkedConference);
    }

    public void clearBookmarkedConferences() {
        this.bookmarkedConferences.clear();
    }

    public List<BookmarkedURL> getBookmarkedURLS() {
        return this.bookmarkedURLS;
    }

    public List<BookmarkedConference> getBookmarkedConferences() {
        return this.bookmarkedConferences;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<storage xmlns=\"storage:bookmarks\">");
        for (BookmarkedURL next : getBookmarkedURLS()) {
            if (!next.isShared()) {
                sb.append("<url name=\"");
                sb.append(next.getName());
                sb.append("\" url=\"");
                sb.append(next.getURL());
                sb.append("\"");
                if (next.isRss()) {
                    sb.append(" rss=\"");
                    sb.append(true);
                    sb.append("\"");
                }
                sb.append(" />");
            }
        }
        for (BookmarkedConference next2 : getBookmarkedConferences()) {
            if (!next2.isShared()) {
                sb.append("<conference ");
                sb.append("name=\"");
                sb.append(next2.getName());
                sb.append("\" ");
                sb.append("autojoin=\"");
                sb.append(next2.isAutoJoin());
                sb.append("\" ");
                sb.append("jid=\"");
                sb.append(next2.getJid());
                sb.append("\" ");
                sb.append(">");
                if (next2.getNickname() != null) {
                    sb.append("<nick>");
                    sb.append(next2.getNickname());
                    sb.append("</nick>");
                }
                if (next2.getPassword() != null) {
                    sb.append("<password>");
                    sb.append(next2.getPassword());
                    sb.append("</password>");
                }
                sb.append("</conference>");
            }
        }
        sb.append("</storage>");
        return sb.toString();
    }

    public static class Provider implements PrivateDataProvider {
        public PrivateData parsePrivateData(XmlPullParser xmlPullParser) throws Exception {
            Bookmarks bookmarks = new Bookmarks();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2 && PlusShare.KEY_CALL_TO_ACTION_URL.equals(xmlPullParser.getName())) {
                    BookmarkedURL access$000 = Bookmarks.getURLStorage(xmlPullParser);
                    if (access$000 != null) {
                        bookmarks.addBookmarkedURL(access$000);
                    }
                } else if (next == 2 && "conference".equals(xmlPullParser.getName())) {
                    bookmarks.addBookmarkedConference(Bookmarks.getConferenceStorage(xmlPullParser));
                } else if (next == 3 && "storage".equals(xmlPullParser.getName())) {
                    z = true;
                }
            }
            return bookmarks;
        }
    }

    /* access modifiers changed from: private */
    public static BookmarkedURL getURLStorage(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String attributeValue = xmlPullParser.getAttributeValue("", "name");
        String attributeValue2 = xmlPullParser.getAttributeValue("", PlusShare.KEY_CALL_TO_ACTION_URL);
        String attributeValue3 = xmlPullParser.getAttributeValue("", "rss");
        boolean z = false;
        BookmarkedURL bookmarkedURL = new BookmarkedURL(attributeValue2, attributeValue, attributeValue3 != null && "true".equals(attributeValue3));
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2 && "shared_bookmark".equals(xmlPullParser.getName())) {
                bookmarkedURL.setShared(true);
            } else if (next == 3 && PlusShare.KEY_CALL_TO_ACTION_URL.equals(xmlPullParser.getName())) {
                z = true;
            }
        }
        return bookmarkedURL;
    }

    /* access modifiers changed from: private */
    public static BookmarkedConference getConferenceStorage(XmlPullParser xmlPullParser) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "name");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "autojoin");
        BookmarkedConference bookmarkedConference = new BookmarkedConference(xmlPullParser.getAttributeValue("", "jid"));
        bookmarkedConference.setName(attributeValue);
        bookmarkedConference.setAutoJoin(Boolean.valueOf(attributeValue2).booleanValue());
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2 && Nick.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                bookmarkedConference.setNickname(xmlPullParser.nextText());
            } else if (next == 2 && "password".equals(xmlPullParser.getName())) {
                bookmarkedConference.setPassword(xmlPullParser.nextText());
            } else if (next == 2 && "shared_bookmark".equals(xmlPullParser.getName())) {
                bookmarkedConference.setShared(true);
            } else if (next == 3 && "conference".equals(xmlPullParser.getName())) {
                z = true;
            }
        }
        return bookmarkedConference;
    }
}
