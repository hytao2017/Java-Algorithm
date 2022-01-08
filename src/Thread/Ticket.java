package Thread;

public class Ticket {

    private int number;
    private int count = 0;

    public Ticket(int number) {
        this.number = number;
    }

    public synchronized void sell(){
        number = number - 1;
        count = count + 1;
       System.out.println(Thread.currentThread().getName() + ":卖出一张票，还剩" + number + "张票,count:" + count );
    }
}
