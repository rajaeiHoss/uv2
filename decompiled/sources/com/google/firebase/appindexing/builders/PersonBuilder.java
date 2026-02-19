package com.google.firebase.appindexing.builders;

public final class PersonBuilder extends IndexableBuilder<PersonBuilder> {
    PersonBuilder() {
        super("Person");
    }

    public final PersonBuilder setEmail(String str) {
        return (PersonBuilder) put("email", str);
    }

    public final PersonBuilder setIsSelf(boolean z) {
        return (PersonBuilder) put("isSelf", z);
    }

    public final PersonBuilder setTelephone(String str) {
        return (PersonBuilder) put("telephone", str);
    }
}
