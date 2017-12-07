package com.example.cz10000_001.mytestapp.base;

import cn.bmob.v3.BmobObject;

/**
 * Created by cz10000_001 on 2017/10/27.
 */

public class Person extends BmobObject {
    private int id ;
    private String name ;

    public Person(){}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String tableName, int id, String name) {
        super(tableName);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
