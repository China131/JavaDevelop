package cn.javadevelop.jdt.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jianhao on 2017/11/30.
 */
public class ReenTrantLockTest {


    static class NumberWrapper{
        public int value = 1;
    }

    public static void main(String[] args){
        final Lock lock = new ReentrantLock();
        final Condition reachThreeCondition = lock.newCondition();

        final Condition reachSixCondition = lock.newCondition();

        final NumberWrapper num = new NumberWrapper();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("threadA start write");
                    while (num.value <= 3){
                        System.out.println(num.value);
                        num.value ++ ;
                    }
                    reachThreeCondition.signal();
                }finally {
                    lock.unlock();
                }
                lock.lock();
                try {
                    reachSixCondition.await();
                    System.out.println("threadA start worte");
                    while (num.value <= 9){
                        System.out.println(num.value);
                        num.value ++ ;
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
//                    while (num.value<=3){
                        //等待3输出完毕的信号
                        reachThreeCondition.await();
//                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

                try {
                    lock.lock();
                    //已经收到信号 开始输出456
                    System.out.println("threadB start write");
                    while (num.value <= 6){
                        System.out.println(num.value);
                        num.value ++ ;
                    }
                    reachSixCondition.signal();
                }finally {
                    lock.unlock();
                }

            }
        });

        threadB.start();
        threadA.start();


    }


}
