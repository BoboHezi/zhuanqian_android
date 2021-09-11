package com.ewq.zq.rv;

public enum CellType {
    TYPE_SIMPLE(0), TYPE_SHOP(1), TYPE_SHOP_SIMPLE(2), TYPE_TASK(3),
    TYPE_TASK_QUICK(4), TYPE_TILE_QUICK(5);

    private final int value;

    CellType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
