package com.ewq.zq.rv.cell;

public enum CellType {
    TYPE_SIMPLE(0), TYPE_SHOP(1), TYPE_SHOP_SIMPLE(2), TYPE_TASK(3),
    TYPE_TASK_QUICK(4), TYPE_TILE_QUICK(5), TYPE_ERROR(6), TYPE_EMPTY(7),
    TYPE_LOADING(8), TYPE_LOAD_MORE(9);

    private final int value;

    CellType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isFixedType(int type) {
        return type == TYPE_ERROR.value || type == TYPE_EMPTY.value
                || type == TYPE_LOADING.value || type == TYPE_LOAD_MORE.value;
    }
}
