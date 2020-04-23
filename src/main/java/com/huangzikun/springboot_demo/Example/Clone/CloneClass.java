package com.huangzikun.springboot_demo.Example.Clone;

public class CloneClass implements Cloneable {

    public int a;
    public Integer aInteger;

    public CloneClass cloneClass;

    static {
        System.out.println("类静态方法");
    }

    {
        System.out.println("类初始化方法");
    }

    public CloneClass() {
        System.out.println("构造方法");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
