package org.jivesoftware.smackx.workgroup.agent;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class WorkgroupQueue {
    private int averageWaitTime = -1;
    private int currentChats = 0;
    private int maxChats = 0;
    private String name;
    private Date oldestEntry = null;
    private Status status = Status.CLOSED;
    private Set users = Collections.EMPTY_SET;

    WorkgroupQueue(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public Status getStatus() {
        return this.status;
    }

    /* access modifiers changed from: package-private */
    public void setStatus(Status status2) {
        this.status = status2;
    }

    public int getUserCount() {
        Set set = this.users;
        if (set == null) {
            return 0;
        }
        return set.size();
    }

    public Iterator getUsers() {
        Set set = this.users;
        if (set == null) {
            return Collections.EMPTY_SET.iterator();
        }
        return Collections.unmodifiableSet(set).iterator();
    }

    /* access modifiers changed from: package-private */
    public void setUsers(Set set) {
        this.users = set;
    }

    public int getAverageWaitTime() {
        return this.averageWaitTime;
    }

    /* access modifiers changed from: package-private */
    public void setAverageWaitTime(int i) {
        this.averageWaitTime = i;
    }

    public Date getOldestEntry() {
        return this.oldestEntry;
    }

    /* access modifiers changed from: package-private */
    public void setOldestEntry(Date date) {
        this.oldestEntry = date;
    }

    public int getMaxChats() {
        return this.maxChats;
    }

    /* access modifiers changed from: package-private */
    public void setMaxChats(int i) {
        this.maxChats = i;
    }

    public int getCurrentChats() {
        return this.currentChats;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentChats(int i) {
        this.currentChats = i;
    }

    public static class Status {
        public static final Status ACTIVE = new Status("active");
        public static final Status CLOSED = new Status("closed");
        public static final Status OPEN = new Status("open");
        private String value;

        public static Status fromString(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            Status status = OPEN;
            if (status.toString().equals(lowerCase)) {
                return status;
            }
            Status status2 = ACTIVE;
            if (status2.toString().equals(lowerCase)) {
                return status2;
            }
            Status status3 = CLOSED;
            if (status3.toString().equals(lowerCase)) {
                return status3;
            }
            return null;
        }

        private Status(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }
}
