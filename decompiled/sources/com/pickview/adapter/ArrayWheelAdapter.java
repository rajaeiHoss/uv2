package com.pickview.adapter;

import java.util.ArrayList;

public class ArrayWheelAdapter<T> implements WheelAdapter {
    public static final int DEFAULT_LENGTH = 4;
    private ArrayList<T> items;
    private int length;

    public ArrayWheelAdapter(ArrayList<T> arrayList, int i) {
        this.items = arrayList;
        this.length = i;
    }

    public ArrayWheelAdapter(ArrayList<T> arrayList) {
        this(arrayList, 4);
    }

    public Object getItem(int i) {
        return (i < 0 || i >= this.items.size()) ? "" : this.items.get(i);
    }

    public int getItemsCount() {
        return this.items.size();
    }

    public int indexOf(Object obj) {
        return this.items.indexOf(obj);
    }
}
