package com.ewq.network.entity;

import java.util.List;

/**
 * Description: 反序列化带page的List数据
 */
@SuppressWarnings("unused")
public class ListData<T> extends PageMo {
    /**
     * list数据
     */
    private List<T> list;
    private List<T> multiple;
    private List<T> single;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getMultiple() {
        return multiple;
    }

    public void setMultiple(List<T> multiple) {
        this.multiple = multiple;
    }

    public List<T> getSingle() {
        return single;
    }

    public void setSingle(List<T> single) {
        this.single = single;
    }
}
