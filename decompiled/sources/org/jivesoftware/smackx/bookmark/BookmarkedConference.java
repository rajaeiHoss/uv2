package org.jivesoftware.smackx.bookmark;

public class BookmarkedConference implements SharedBookmark {
    private boolean autoJoin;
    private boolean isShared;
    private final String jid;
    private String name;
    private String nickname;
    private String password;

    protected BookmarkedConference(String str) {
        this.jid = str;
    }

    protected BookmarkedConference(String str, String str2, boolean z, String str3, String str4) {
        this.name = str;
        this.jid = str2;
        this.autoJoin = z;
        this.nickname = str3;
        this.password = str4;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public void setName(String str) {
        this.name = str;
    }

    public boolean isAutoJoin() {
        return this.autoJoin;
    }

    /* access modifiers changed from: protected */
    public void setAutoJoin(boolean z) {
        this.autoJoin = z;
    }

    public String getJid() {
        return this.jid;
    }

    public String getNickname() {
        return this.nickname;
    }

    /* access modifiers changed from: protected */
    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getPassword() {
        return this.password;
    }

    /* access modifiers changed from: protected */
    public void setPassword(String str) {
        this.password = str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BookmarkedConference)) {
            return false;
        }
        return ((BookmarkedConference) obj).getJid().equalsIgnoreCase(this.jid);
    }

    /* access modifiers changed from: protected */
    public void setShared(boolean z) {
        this.isShared = z;
    }

    public boolean isShared() {
        return this.isShared;
    }
}
