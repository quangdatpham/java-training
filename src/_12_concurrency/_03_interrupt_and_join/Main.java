package _12_concurrency._03_interrupt_and_join;

import _12_concurrency._02_runnable.MyRunnable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from the main thread!");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("__Another_Thread__");
        anotherThread.start();

        // anotherThread.interrupt();

        System.out.println("Hello again from the main thread!");

        new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("Hello from the anonymous class's implementation of run()");
                try {
                    anotherThread.join(2000);
                    System.out.println("I am waiting for you to wake up or timeout (2000 millis)!");
                } catch (InterruptedException e) {
                    System.out.println("I was interrupted!");
                }
            }
        }).start();

        new Thread() {
            public void run() {
                System.out.println("Hello");
            }
        }.start();
    }
}
