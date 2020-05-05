package com.huangzikun.springboot_demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class CollectionController {

    @RequestMapping(value = "/collection/index", method = RequestMethod.GET)
    @ResponseBody
    public void collection() {
        //引用类型
        ArrayList<Integer> arrayList = new ArrayList<>();

        //数组，基础类型
        char[] chars = new char[]{
                'a','b','c'
        };
        //数组，引用类型
        String[] strings = new String[]{
                "a", "b","c"
        };
    }

    @RequestMapping(value = "/collection/arrayList", method = RequestMethod.GET)
    @ResponseBody
    public void arrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);

        arrayList.addAll(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});


        Integer i = arrayList.get(0);
        System.out.println(i);

        /**
         * 会报错，数组越界
         * i = arrayList.get(10);
         */
        arrayList.remove(3);

        System.out.println(arrayList.toString());
    }

    @RequestMapping(value = "/collection/linkedList", method = RequestMethod.GET)
    @ResponseBody
    public void linkedList() {
        LinkedList<String> linkedList = new LinkedList<>() {{
            add("第一个");
            add("第二个");
            add("第三个");
        }};

        linkedList.addFirst("add first 第一个");
        linkedList.addFirst("add first 第二个");

        linkedList.addLast("add last 第一个");
        linkedList.addLast("add last 第二个");

        System.out.println(linkedList.toString());

        //队列用法
        System.out.println(linkedList.poll());

        System.out.println(linkedList.toString());

    }

    @RequestMapping(value = "/collection/vector", method = RequestMethod.GET)
    @ResponseBody
    public void vector() {

        class MyRunnable implements Runnable {

            final Vector<String> vector = new Vector<>();
            final ArrayList<String> arrayList = new ArrayList<>();

            public void run() {
                for (int i=0; i<10; i++) {
                    vector.add(Thread.currentThread().getName() + "add" + i);
                    arrayList.add(Thread.currentThread().getName() + "add" + i);
                }

                System.out.println("vector" + vector.size());
                System.out.println("arrayList" + arrayList.size());
            }
        };

        MyRunnable myRunnable = new MyRunnable();


        Thread thread1 = new Thread(myRunnable, "线程1");
        Thread thread2 = new Thread(myRunnable, "线程2");
        Thread thread3 = new Thread(myRunnable, "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    @RequestMapping(value = "/collection/treeSet", method = RequestMethod.GET)
    @ResponseBody
    public void TreeSet() {
        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("aaa");
        treeSet.add("aaa");

        class MyObj implements Comparable {
            int a;

            MyObj(int a) {
                this.a = a;
            }

            /**
             * 设置一个对比函数
             * 负数，o<this
             * 0, 0==this
             * 正数，o>this
             * @param o
             * @return
             */
            @Override
            public int compareTo(Object o) {

                MyObj m = (MyObj)o;

                return m.a - this.a;
            }

            @Override
            public String toString() {
                return "MyObj{" +
                        "a=" + a +
                        '}';
            }
        };

        TreeSet<MyObj> treeSet1 = new TreeSet<>();
        treeSet1.add(new MyObj(1));
        treeSet1.add(new MyObj(2));
        treeSet1.add(new MyObj(-1));
        treeSet1.add(new MyObj(1));

        System.out.println(treeSet.toString());
        System.out.println(treeSet1.toString());
    }

    @RequestMapping(value = "/collection/hashSet", method = RequestMethod.GET)
    @ResponseBody
    public void HashSet() {

        class MyObj {
            int a;

            MyObj(int a) {
                this.a = a;
            }

            @Override
            public String toString() {
                return "MyObj{" +
                        "a=" + a +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                return true;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        }
        //顺序不固定
        HashSet<String> hashSet = new HashSet<>(){{
            add("hashset1");
            add("hashset2");
            add(null);
            add(null);
            add("asdf");
        }};


        HashSet<MyObj> myObjHashSet = new HashSet<>(){{
            add(new MyObj(1));
            add(new MyObj(2));
        }};

        System.out.println(hashSet.toString());
        System.out.println(myObjHashSet.toString());
    }

    @RequestMapping(value = "/collection/linkedHashSet", method = RequestMethod.GET)
    @ResponseBody
    public void LinkedHashSet() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(){{
            add("aaa");
            add("bbb");
        }};

        System.out.println(linkedHashSet.toString());
    }

    @RequestMapping(value = "/collection/priorityQueue", method = RequestMethod.GET)
    @ResponseBody
    public void PriorityQueue() {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(){{
            add("aaa");
            add("bbb");
            add("a");
        }};

        System.out.println(priorityQueue.toString());


        for (String s : priorityQueue) {
            System.out.println(s);
        }

        System.out.println(priorityQueue.toString());


        class User implements Comparable {
            int score;
            User(int c) {
                score = c;
            }

            @Override
            public String toString() {
                return "User{" +
                        "score=" + score +
                        '}';
            }

            @Override
            public int compareTo(Object o) {
                User u = (User)o;

                return u.score - this.score;
            }
        }

        PriorityQueue<User> userPriorityQueue = new PriorityQueue<>(){{
            add(new User(1));
            add(new User(100));
            add(new User(50));
        }};


        User u = userPriorityQueue.poll();
        System.out.println(u.toString());

        userPriorityQueue.add(new User(80));

        u = userPriorityQueue.poll();
        System.out.println(u.toString());
    }
}
