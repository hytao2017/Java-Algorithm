package Thread;

import java.util.concurrent.locks.ReentrantLock;

public class LTicket {

    private final ReentrantLock lock = new ReentrantLock();

    private int number;
    private int count = 0;

    public LTicket(int number) {
        this.number = number;
    }

    public void sell(){
        lock.lock();
        number = number - 1;
        count = count + 1;
       System.out.println(Thread.currentThread().getName() + ":卖出一张票，还剩" + number + "张票,count:" + count );
       lock.unlock();
    }

}
