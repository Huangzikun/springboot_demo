package com.huangzikun.springboot_demo.Controller;

import com.huangzikun.springboot_demo.Example.Clone.CloneClass;
import com.huangzikun.springboot_demo.Example.Serialization.User1;
import com.huangzikun.springboot_demo.Example.Serialization.User2;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataStructureController {

    byte b;
    short s;
    int i;
    long l;
    float f;
    double d;
    char c;
    boolean bo;

    /**
     * @link https://www.cnblogs.com/LiaHon/p/11043238.html
     * 基础数据类型
     */
    @RequestMapping(value = "/baseDataType", method = RequestMethod.GET)
    @ResponseBody
    public Object baseDataType() {
        List<String> list = new ArrayList<>(){{
            add("byte: 最大值: " + Byte.MAX_VALUE);
            add("最小值: " + Byte.MIN_VALUE);
            add("默认值: " + b);

            add("sort: 最大值" + Short.MAX_VALUE);
            add("sort: 最小值" + Short.MIN_VALUE);
            add("sort: 默认值" + s);

            add("int: 范围：" + Integer.MIN_VALUE + "~" + Integer.MAX_VALUE + "默认值: " + i);
            add("long：范围：" + Long.MIN_VALUE + "~" + Long.MAX_VALUE + "默认值" + l);
            add("float: 范围：" + Float.MIN_VALUE + "~" + Float.MAX_VALUE + "默认值" + f);
            add("double: 范围：" + Double.MIN_VALUE + "~" + Double.MAX_VALUE + "默认值" + d);
            add("char: 范围：" + Character.MIN_VALUE + "~" + Character.MAX_VALUE + "默认值" + c);
            add("bool: 默认值" + bo);
        }};

        return list;
    }

    @RequestMapping(value = "/initClass", method = RequestMethod.GET)
    @ResponseBody
    public void initClass()
    {
        CloneClass cloneClass = new CloneClass();
        cloneClass.a = 1;

        System.out.println(cloneClass.a);

        try {
            CloneClass cloneClass1 = (CloneClass) cloneClass.clone();

//            cloneClass.a = 5;
//            cloneClass.aInteger = 10;

            System.out.println(cloneClass.aInteger.equals(cloneClass1.aInteger));
            System.out.println("a = " + cloneClass1.a);
            System.out.println("aInteger = " + cloneClass1.aInteger);

        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());

        }
    }

    @RequestMapping(value = "/serialization", method = RequestMethod.GET)
    @ResponseBody
    public void Serialization() {
        User1 user = new User1();
        user.setName("yaomy");
        user.setAge(23);
        System.out.println(user);
        //序列化对象到文件中
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("template"));
            oos.writeObject(user);
            oos.close();
            //反序列化
            File file = new File("template");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            User1 newUser = (User1)ois.readObject();
            System.out.println(newUser.toString());

        } catch (IOException ioException) {

        } catch (ClassNotFoundException classNotFoundException) {

        }

        //初始化对象
        User2 user2 = new User2();
        user2.setName("yaomy");
        user2.setAge(23);
        user2.setMoney(10000);
        System.out.println(user2);
        try {
            //序列化对象到文件中
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("template2"));
            oos.writeObject(user2);
            oos.close();
            //反序列化
            File file = new File("template2");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            User2 newUser = (User2)ois.readObject();
            System.out.println(newUser.toString());
            ois.close();
        } catch (IOException ioException) {
            System.out.println("io" + ioException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("class" + classNotFoundException.getMessage());

        }

    }


}
