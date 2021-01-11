package com.changjianghuyu.qixia.web.common;

import com.changjianghuyu.qixia.web.entity.HyUser;
import com.changjianghuyu.qixia.web.service.HyPunchClockService;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.*;

public class TestClass {

    private static  ThreadLocal<Long> threadLocal;

    public static void main(String[] args) throws ClassNotFoundException {
        HashMap<Object, String> stringStringHashMap = new HashMap<>();
        Collection<String> values = stringStringHashMap.values();
//        ArrayList<Integer> integers = new ArrayList<>(1000);
//        HashSet<String> strings = new HashSet<>();

        HyUser user = new HyUser();
        System.out.println(user.getClass().getClassLoader().getParent());
        Class<?> clazz = Class.forName("com.changjianghuyu.qixia.web.entity.HyUser");
        System.out.println(user.getClass().getClassLoader().getParent());


    }
    class MyThread extends Thread{
        @Resource
        private HyPunchClockService hyPunchClockService;

        @Override
        public void run() {
//            PageInfo hyPunchClockList = hyPunchClockService.getHyPunchClockList(new HashMap<>());

        }
    }

    static void  ThreadLocalTestMethond(Object o){

    }
//
//    class threadLocalTask

}
