package com.amo.demo.wheelview;

public class ArrayWheelAdapter<T> implements WheelAdapter {
    public static final int DEFAULT_LENGTH = -1;
    private T[] items;
    private int length;

    public ArrayWheelAdapter(T[] tArr, int i) {
        this.items = tArr;
        this.length = i;
    }

    public ArrayWheelAdapter(T[] tArr) {
        this(tArr, -1);
    }

    public String getItem(int i) {
        if (i < 0) {
            return null;
        }
        T[] tArr = this.items;
        if (i < tArr.length) {
            return tArr[i].toString();
        }
        return null;
    }

    public int getItemsCount() {
        return this.items.length;
    }

    public int getMaximumLength() {
        return this.length;
    }
}
