package Thread;

/*
*  三个方法，按序执行
* */

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(int loop){

        lock.lock();

        try {

            while (flag != 1){
                System.out.println("aa等待");
               c1.await();
            }

            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + ",轮次：" + loop);
            }

            flag = 2;
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void print10(int loop){

        lock.lock();

        try {

            while (flag != 2){
                System.out.println("bb等待");
                c2.await();
            }

            for (int i = 1; i <= 2; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + ",轮次：" + loop);
            }

            flag = 3;
            c3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void print15(int loop){

        lock.lock();

        try {

            while (flag != 3){
                System.out.println("cc等待");
                c3.await();
            }

            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + ",轮次：" + loop);
            }

            flag = 1;
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

}
