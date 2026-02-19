package org.jivesoftware.smackx.bookmark;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.PrivateDataManager;
import org.jivesoftware.smackx.bookmark.Bookmarks;

public class BookmarkManager {
    private static final Map<Connection, BookmarkManager> bookmarkManagerMap = new HashMap();
    private final Object bookmarkLock = new Object();
    private Bookmarks bookmarks;
    private PrivateDataManager privateDataManager;

    static {
        PrivateDataManager.addPrivateDataProvider("storage", "storage:bookmarks", new Bookmarks.Provider());
    }

    public static synchronized BookmarkManager getBookmarkManager(Connection connection) throws XMPPException {
        BookmarkManager bookmarkManager;
        synchronized (BookmarkManager.class) {
            Map<Connection, BookmarkManager> map = bookmarkManagerMap;
            bookmarkManager = map.get(connection);
            if (bookmarkManager == null) {
                bookmarkManager = new BookmarkManager(connection);
                map.put(connection, bookmarkManager);
            }
        }
        return bookmarkManager;
    }

    private BookmarkManager(Connection connection) throws XMPPException {
        if (connection == null || !connection.isAuthenticated()) {
            throw new XMPPException("Invalid connection.");
        }
        this.privateDataManager = new PrivateDataManager(connection);
    }

    public Collection<BookmarkedConference> getBookmarkedConferences() throws XMPPException {
        retrieveBookmarks();
        return Collections.unmodifiableCollection(this.bookmarks.getBookmarkedConferences());
    }

    public void addBookmarkedConference(String str, String str2, boolean z, String str3, String str4) throws XMPPException {
        retrieveBookmarks();
        BookmarkedConference bookmarkedConference = new BookmarkedConference(str, str2, z, str3, str4);
        List<BookmarkedConference> bookmarkedConferences = this.bookmarks.getBookmarkedConferences();
        if (bookmarkedConferences.contains(bookmarkedConference)) {
            BookmarkedConference bookmarkedConference2 = bookmarkedConferences.get(bookmarkedConferences.indexOf(bookmarkedConference));
            if (!bookmarkedConference2.isShared()) {
                bookmarkedConference2.setAutoJoin(z);
                bookmarkedConference2.setName(str);
                bookmarkedConference2.setNickname(str3);
                bookmarkedConference2.setPassword(str4);
            } else {
                throw new IllegalArgumentException("Cannot modify shared bookmark");
            }
        } else {
            this.bookmarks.addBookmarkedConference(bookmarkedConference);
        }
        this.privateDataManager.setPrivateData(this.bookmarks);
    }

    public void removeBookmarkedConference(String str) throws XMPPException {
        retrieveBookmarks();
        Iterator<BookmarkedConference> it = this.bookmarks.getBookmarkedConferences().iterator();
        while (it.hasNext()) {
            BookmarkedConference next = it.next();
            if (next.getJid().equalsIgnoreCase(str)) {
                if (!next.isShared()) {
                    it.remove();
                    this.privateDataManager.setPrivateData(this.bookmarks);
                    return;
                }
                throw new IllegalArgumentException("Conference is shared and can't be removed");
            }
        }
    }

    public Collection<BookmarkedURL> getBookmarkedURLs() throws XMPPException {
        retrieveBookmarks();
        return Collections.unmodifiableCollection(this.bookmarks.getBookmarkedURLS());
    }

    public void addBookmarkedURL(String str, String str2, boolean z) throws XMPPException {
        retrieveBookmarks();
        BookmarkedURL bookmarkedURL = new BookmarkedURL(str, str2, z);
        List<BookmarkedURL> bookmarkedURLS = this.bookmarks.getBookmarkedURLS();
        if (bookmarkedURLS.contains(bookmarkedURL)) {
            BookmarkedURL bookmarkedURL2 = bookmarkedURLS.get(bookmarkedURLS.indexOf(bookmarkedURL));
            if (!bookmarkedURL2.isShared()) {
                bookmarkedURL2.setName(str2);
                bookmarkedURL2.setRss(z);
            } else {
                throw new IllegalArgumentException("Cannot modify shared bookmarks");
            }
        } else {
            this.bookmarks.addBookmarkedURL(bookmarkedURL);
        }
        this.privateDataManager.setPrivateData(this.bookmarks);
    }

    public void removeBookmarkedURL(String str) throws XMPPException {
        retrieveBookmarks();
        Iterator<BookmarkedURL> it = this.bookmarks.getBookmarkedURLS().iterator();
        while (it.hasNext()) {
            BookmarkedURL next = it.next();
            if (next.getURL().equalsIgnoreCase(str)) {
                if (!next.isShared()) {
                    it.remove();
                    this.privateDataManager.setPrivateData(this.bookmarks);
                    return;
                }
                throw new IllegalArgumentException("Cannot delete a shared bookmark.");
            }
        }
    }

    private Bookmarks retrieveBookmarks() throws XMPPException {
        Bookmarks bookmarks2;
        synchronized (this.bookmarkLock) {
            if (this.bookmarks == null) {
                this.bookmarks = (Bookmarks) this.privateDataManager.getPrivateData("storage", "storage:bookmarks");
            }
            bookmarks2 = this.bookmarks;
        }
        return bookmarks2;
    }
}
