package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.zzbq;
import java.util.Date;

public final class MessageBuilder extends IndexableBuilder<MessageBuilder> {
    MessageBuilder() {
        super("Message");
    }

    MessageBuilder(String str) {
        super(str);
    }

    public final MessageBuilder setDateRead(Date date) {
        zzbq.checkNotNull(date);
        return (MessageBuilder) put("dateRead", date.getTime());
    }

    public final MessageBuilder setDateReceived(Date date) {
        zzbq.checkNotNull(date);
        return (MessageBuilder) put("dateReceived", date.getTime());
    }

    public final MessageBuilder setDateSent(Date date) {
        zzbq.checkNotNull(date);
        return (MessageBuilder) put("dateSent", date.getTime());
    }

    public final MessageBuilder setIsPartOf(ConversationBuilder... conversationBuilderArr) {
        return (MessageBuilder) put("isPartOf", conversationBuilderArr);
    }

    public final MessageBuilder setMessageAttachment(IndexableBuilder<?>... indexableBuilderArr) {
        return (MessageBuilder) put("messageAttachment", indexableBuilderArr);
    }

    public final MessageBuilder setRecipient(PersonBuilder... personBuilderArr) {
        return (MessageBuilder) put("recipient", personBuilderArr);
    }

    public final MessageBuilder setSender(PersonBuilder personBuilder) {
        return (MessageBuilder) put("sender", new PersonBuilder[]{personBuilder});
    }

    public final MessageBuilder setText(String str) {
        return (MessageBuilder) put("text", str);
    }
}
