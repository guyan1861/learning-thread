package org.guyan.thinking.in.thread.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestLambda {
    public static void main(String[] args) {
        // 比较器案例
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        System.out.println("排序前：" + list);

        // 比较器常规写法
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare (Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // Lambda表达式
        Collections.sort(list,(o1,o2)->o2-o1);
        System.out.println("排序后：" + list);

    }
}
