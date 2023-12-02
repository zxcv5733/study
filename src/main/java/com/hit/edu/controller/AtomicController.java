package com.hit.edu.controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Li dong
 * @date: 2023/12/2 21:24
 * @description:
 */
public class AtomicController {
    public static void main(String[] args) {
        // AtomicStampedReference 注意：如果泛型是一个包装类，注意对象的引用问题
        AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<Integer>(1, 2);  // 当前引用为1，当前版本为2
        new Thread(() -> {
            int stamp = atomicReference.getStamp();//获得版本号为2
            System.out.println("A1->" + stamp);   // (1)  A1->2
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 当前引用等于预期引用，当前版本等于预期版本，满足条件，则将当前引用改为2，当前版本加1等于3
            System.out.println(atomicReference.compareAndSet(1, 2,
                    atomicReference.getStamp(), atomicReference.getStamp() + 1));  // (3) true
            System.out.println("A2->" + atomicReference.getStamp()); // (3) A2->3

            // 当前引用等于预期引用，当前版本等于预期版本，满足条件，则将当前引用改为1，当前版本加1等于4
            System.out.println(atomicReference.compareAndSet(2, 1,
                    atomicReference.getStamp(), atomicReference.getStamp() + 1));  // (4) true
            System.out.println("A3->" + atomicReference.getStamp()); // (4) A3 -> 4
        }, "A").start();


        new Thread(() -> {
            int stamp = atomicReference.getStamp();//获得版本号为2
            System.out.println("B1->" + stamp);  //(2)  B1->2
            new ReentrantLock(true);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 当前引用(1)等于预期引用(1)，但是当前版本(4)不等于预期版本(2)（我们的预期版本应该是B1刚开始拿的，为2），不满足条件，所以当前引用依然是1，当前版本依然是4，成功解决ABA问题
            System.out.println(atomicReference.compareAndSet(1, 6,   // (5) flase
                    stamp, stamp + 1));
            System.out.println("B2->" + atomicReference.getStamp());  // (5) B2->4
        }, "B").start();
    }
}
