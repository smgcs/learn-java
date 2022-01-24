package threadPool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                6,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        List<Future<ThreadPrint>> futureList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Future<ThreadPrint> f = threadPoolExecutor.submit(new ThreadPrint("第" + i + "个线程", i, "thread:" + 1));
            futureList.add(f);
        }
        for (Future<ThreadPrint> threadPrintFuture : futureList) {
            System.out.println("threadPrintFuture = " + threadPrintFuture.get());
        }
        threadPoolExecutor.shutdown();
//        test();

    }
    public static void test(){
        ExecutorService e = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 4; i++) {
            e.submit(new ThreadPrint("第" + i + "个线程", i, "thread:" + 1));
        }
    }
}
class ThreadPrint implements Callable<ThreadPrint> {
    String name;
    String tag;
    int age;
    public ThreadPrint(String name, int age, String tag) {
        this.name = name;
        this.age = age;
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ThreadPrint{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public ThreadPrint call() {
//        System.out.println(this);
        //do something
        System.out.println("Thread.currentThread() = " + Thread.currentThread());
        return this;
    }
}