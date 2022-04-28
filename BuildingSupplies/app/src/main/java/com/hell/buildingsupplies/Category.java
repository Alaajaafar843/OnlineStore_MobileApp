package com.hell.buildingsupplies;

public class Category {

    private int cid;
    private String name;

    public Category(int cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "cid=" + cid + ", name='" + name;
    }
}
