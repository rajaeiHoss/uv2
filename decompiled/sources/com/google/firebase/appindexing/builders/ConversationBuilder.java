package com.google.firebase.appindexing.builders;

public final class ConversationBuilder extends IndexableBuilder<ConversationBuilder> {
    ConversationBuilder() {
        super("Conversation");
    }

    public final ConversationBuilder setId(String str) {
        return (ConversationBuilder) put("id", str);
    }
}
