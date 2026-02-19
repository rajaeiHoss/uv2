package org.jivesoftware.smackx.workgroup.agent;

import java.util.Date;
import java.util.Set;
import org.jivesoftware.smackx.workgroup.agent.WorkgroupQueue;

public interface QueueUsersListener {
    void averageWaitTimeUpdated(WorkgroupQueue workgroupQueue, int i);

    void oldestEntryUpdated(WorkgroupQueue workgroupQueue, Date date);

    void statusUpdated(WorkgroupQueue workgroupQueue, WorkgroupQueue.Status status);

    void usersUpdated(WorkgroupQueue workgroupQueue, Set set);
}
