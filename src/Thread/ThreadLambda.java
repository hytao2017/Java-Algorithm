package Thread;

public class ThreadLambda {

    public static void main(String[] args) throws Exception {
        Ticket ticket = new Ticket(120);
        Share share = new Share();
        ConditionTest conditionTest = new ConditionTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    conditionTest.print5(i);
                }
            }
        },"aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    conditionTest.print10(i);
                }
            }
        },"bb").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    conditionTest.print15(i);
                }
            }
        },"cc").start();




    }
}
