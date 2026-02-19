package org.jivesoftware.smack.util.collections;

import java.util.Iterator;

public class EmptyIterator<E> extends AbstractEmptyIterator<E> implements ResettableIterator<E> {
    public static final Iterator INSTANCE;
    public static final ResettableIterator RESETTABLE_INSTANCE;

    static {
        EmptyIterator emptyIterator = new EmptyIterator();
        RESETTABLE_INSTANCE = emptyIterator;
        INSTANCE = emptyIterator;
    }

    public static <T> Iterator<T> getInstance() {
        return INSTANCE;
    }

    protected EmptyIterator() {
    }
}
