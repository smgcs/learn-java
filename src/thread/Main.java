package thread;

import java.util.stream.IntStream;

class ThreadPrint extends java.lang.Thread {
    @Override
    public void run(){
        try {
            sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("自定义线程");
    }

}

class ThreadFor implements Runnable{
    @Override
    public void run(){
        IntStream.range(1,5).forEach(System.out::println) ;
    }
}


class ThreadYield extends Thread {
    public ThreadYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为3时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 3) {
                Thread.yield();
            }
        }

    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPrint t = new ThreadPrint();
        t.start();
        Thread ti = new Thread(new ThreadFor());
        ti.start();
        ThreadYield yt1 = new ThreadYield("张三");
        ThreadYield yt2 = new ThreadYield("李四");
        yt1.start();
        yt2.start();
        t.join();
        ti.join();
        yt1.join();
        yt2.join();
        System.out.println("main");
    }
}