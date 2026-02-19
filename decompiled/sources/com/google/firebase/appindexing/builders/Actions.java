package com.google.firebase.appindexing.builders;

import com.google.firebase.appindexing.Action;

public final class Actions {
    public static Action newView(String str, String str2) {
        return new Action.Builder(Action.Builder.VIEW_ACTION).setObject(str, str2).build();
    }
}
